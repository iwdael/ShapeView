package com.iwdael.shapeview.path

import android.graphics.*
import android.view.MotionEvent
import android.view.View
import com.iwdael.shapeview.*
import com.iwdael.shapeview.path.base.PathMaker
import kotlin.math.abs
import kotlin.math.min

/**
 * author : Iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * version: 1.0
 */
class CirclePathMaker(attr: Attrs, pmRect: Pms, pmBorder: Pms, pmProgress: Pms) :
    PathMaker(attr, pmRect, pmBorder, pmProgress) {
    override fun makeRect() {
        if (!attr.validRect()) return
        pmRect[0].path.reset()
        val maxRadius = min(contentRectF.width() / 2f, contentRectF.height() / 2f)
        val circle = RectF(
            contentRectF.left + (contentRectF.width() / 2f - maxRadius),
            contentRectF.top + (contentRectF.height() / 2f - maxRadius),
            contentRectF.right - (contentRectF.width() / 2f - maxRadius),
            contentRectF.bottom - (contentRectF.height() / 2f - maxRadius)
        )
        pmRect[0].path.addRoundRect(circle, createRadii(maxRadius), Path.Direction.CW)
    }

    override fun makeBorder() {
        if (!attr.validBorder()) return
        pmBorder[0].path.reset()
        val target = contentRectF.contentShrink(attr.borderWidth / 2f)
        val maxRadius = min(target.width() / 2f, target.height() / 2f)
        val circle = RectF(
            target.centerX() - maxRadius,
            target.centerY() - maxRadius,
            target.centerX() + maxRadius,
            target.centerY() + maxRadius
        )
        pmBorder[0].path.addRoundRect(circle, createRadii(maxRadius), Path.Direction.CW)
    }

    override fun makeProgress() {
        if (!attr.validProgress()) return
        pmProgress[0].path.reset()
        pmProgress[1].path.reset()
        pmProgress[2].path.reset()

        when (attr.progressStyle) {
            ProgressStyle.BORDER -> makeProgressBorder()
            ProgressStyle.SECTOR -> makeProgressSector()
            ProgressStyle.LINE -> makeProgressLine()
        }
        pmProgress[0].measure.setPath(pmProgress[0].path, false)
        pmProgress[1].measure.setPath(pmProgress[1].path, false)
        pmProgress[2].measure.setPath(pmProgress[2].path, false)

        val pos = floatArrayOf(0f, 0f)
        pmProgress[1].measure.getPosTan(0.01f, pos, null)
        startAngle = getAngle(pos[0] - contentRectF.centerX(), pos[1] - contentRectF.centerY())
    }

    private val middleRectF = RectF()
    private fun makeProgressLine() {
        val target = contentRectF.contentShrink(attr.borderWidth / 2f)
        val outRadius = min(target.width() / 2f, target.height() / 2f)
        val outRectF = RectF(
            target.centerX() - outRadius,
            target.centerY() - outRadius,
            target.centerX() + outRadius,
            target.centerY() + outRadius
        )
        pmProgress[0].path.addRoundRect(outRectF, createRadii(outRadius), Path.Direction.CW)
        middleRectF.set(outRectF.contentShrink(attr.progressStrokeWidth / 2f))
        val middleRadius = outRadius - attr.progressStrokeWidth / 2f
        val middleRadii = createRadii(middleRadius)
        pmProgress[1].path.addRoundRect(middleRectF, middleRadii, Path.Direction.CW)
    }

    private fun makeProgressSector() {
        val maxRadius = min(contentRectF.width() / 2f, contentRectF.height() / 2f)
        val circle = RectF(
            contentRectF.left + (contentRectF.width() / 2f - maxRadius),
            contentRectF.top + (contentRectF.height() / 2f - maxRadius),
            contentRectF.right - (contentRectF.width() / 2f - maxRadius),
            contentRectF.bottom - (contentRectF.height() / 2f - maxRadius)
        )
        pmProgress[1].path.addRoundRect(circle, createRadii(maxRadius), Path.Direction.CW)
    }

    private fun makeProgressBorder() {
        val ringRadius = min(contentRectF.width() / 2f, contentRectF.height() / 2f)
        val outRadius = ringRadius - attr.progressStrokeWidth / 2f
        val middleRadius = ringRadius - attr.progressStrokeWidth - attr.progressSolidWidth / 2f
        val innerRadius =
            ringRadius - attr.progressStrokeWidth - attr.progressSolidWidth - attr.progressStrokeWidth / 2f


        val outCircle = RectF(
            contentRectF.centerX() - outRadius,
            contentRectF.centerY() - outRadius,
            contentRectF.centerX() + outRadius,
            contentRectF.centerY() + outRadius
        )
        pmProgress[0].path.addRoundRect(
            outCircle,
            floatArrayOf(
                outRadius, outRadius,
                outRadius, outRadius,
                outRadius, outRadius,
                outRadius, outRadius,
            ),
            Path.Direction.CW
        )
        pmProgress[0].path.close()


        val middleCircle = RectF(
            contentRectF.centerX() - middleRadius,
            contentRectF.centerY() - middleRadius,
            contentRectF.centerX() + middleRadius,
            contentRectF.centerY() + middleRadius
        )
        pmProgress[1].path.addRoundRect(
            middleCircle,
            floatArrayOf(
                middleRadius, middleRadius,
                middleRadius, middleRadius,
                middleRadius, middleRadius,
                middleRadius, middleRadius,
            ),
            Path.Direction.CW
        )
        pmProgress[1].path.close()


        val innerCircle = RectF(
            contentRectF.centerX() - innerRadius,
            contentRectF.centerY() - innerRadius,
            contentRectF.centerX() + innerRadius,
            contentRectF.centerY() + innerRadius
        )
        pmProgress[2].path.addRoundRect(
            innerCircle,
            floatArrayOf(
                innerRadius, innerRadius,
                innerRadius, innerRadius,
                innerRadius, innerRadius,
                innerRadius, innerRadius,
            ),
            Path.Direction.CW
        )
        pmProgress[2].path.close()

    }

    override fun drawProgress(canvas: Canvas) {
        when (attr.progressStyle) {
            ProgressStyle.BORDER -> drawProgressBorder(canvas)
            ProgressStyle.SECTOR -> drawProgressSector(canvas)
            ProgressStyle.LINE -> drawProgressLine(canvas)
        }
    }

    private fun drawProgressLine(canvas: Canvas) {

        pmProgress[1].paint.shader = LinearGradient(
            middleRectF.left,
            middleRectF.centerY(),
            middleRectF.right,
            middleRectF.centerY(),
            intArrayOf(
                attr.progressReachColor(),
                attr.progressReachColor(),
                attr.progressUnReachColor(),
                attr.progressUnReachColor()
            ),
            floatArrayOf(
                0f,
                attr.progress / attr.progressMax,
                attr.progress / attr.progressMax,
                1f
            ),
            Shader.TileMode.CLAMP
        )
        canvas.drawPath(pmProgress[1].path, pmProgress[1].paint)


        canvas.drawPath(pmProgress[0].path, pmProgress[0].paint)


    }

    private fun drawProgressSector(canvas: Canvas) {
        pmProgress[1].paint.shader = SweepGradient(
            contentRectF.centerX(),
            contentRectF.centerY(),
            intArrayOf(
                attr.progressReachColor(),
                attr.progressReachColor(),
                attr.progressUnReachColor(),
                attr.progressUnReachColor()
            ),
            floatArrayOf(0f, attr.progress / attr.progressMax, attr.progress / attr.progressMax, 1f)
        )
        canvas.drawPath(pmProgress[1].path, pmProgress[1].paint)
    }

    private fun drawProgressBorder(canvas: Canvas) {
        if (attr.progressStrokeWidth != 0f) {
            canvas.drawPath(pmProgress[0].path, pmProgress[0].paint)
        }

        val solidPath = pmProgress[1].path
        val solidMeasure = pmProgress[1].measure
        val solidLength = solidMeasure.length * (attr.progress / attr.progressMax)


        solidPath.reset()
        solidMeasure.getSegment(0f, solidMeasure.length, solidPath, true)
        pmProgress[1].paint.color = attr.progressUnReachColor()
        canvas.drawPath(solidPath, pmProgress[1].paint)

        solidPath.reset()
        solidMeasure.getSegment(0f, solidLength, solidPath, true)
        pmProgress[1].paint.color = attr.progressReachColor()

        canvas.drawPath(solidPath, pmProgress[1].paint)


        if (attr.progressStrokeWidth != 0f) {
            canvas.drawPath(pmProgress[2].path, pmProgress[2].paint)
        }
    }

    override fun drawBorder(canvas: Canvas) {
        canvas.drawPath(pmBorder[0].path, pmBorder[0].paint)
    }

    override fun drawRect(canvas: Canvas) {
        canvas.drawPath(pmRect[0].path, pmRect[0].paint)
    }

    override fun onTouchEvent(event: MotionEvent, view: View) {
        if (event.action == MotionEvent.ACTION_DOWN) {
            attr.refreshStateColor(State.TOUCHED, true)
        } else if (event.action == MotionEvent.ACTION_UP || event.action == MotionEvent.ACTION_CANCEL) {
            attr.refreshStateColor(State.TOUCHED, false)
        }
        when (attr.progressStyle) {
            ProgressStyle.LINE -> onTouchEventLine(event, view)
            ProgressStyle.SECTOR -> onTouchEventSector(event, view)
            ProgressStyle.BORDER -> onTouchEventBorder(event, view)
        }
    }

    private fun onTouchEventBorder(event: MotionEvent, view: View) {
        val offsetX = event.x - contentRectF.centerX()
        val offsetY = event.y - contentRectF.centerY()
        attr.progress =
            (((getAngle(offsetX, offsetY) + (360f - startAngle)) % 360f) / 360f) * attr.progressMax
        view.invalidate()
    }

    private fun onTouchEventSector(event: MotionEvent, view: View) {
        val offsetX = event.x - contentRectF.centerX()
        val offsetY = event.y - contentRectF.centerY()
        attr.progress =
            (((getAngle(offsetX, offsetY) + 270f) % 360f) / 360f) * attr.progressMax
        view.invalidate()
    }

    private fun onTouchEventLine(event: MotionEvent, view: View) {
        var x = event.x
        if (x <= middleRectF.left) x = middleRectF.left
        else if (x >= middleRectF.right) x = middleRectF.right
        attr.progress = (abs(x - middleRectF.left) / middleRectF.width()) * attr.progressMax
        view.invalidate()
    }

}