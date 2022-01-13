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
class RoundPathMaker(attr: Attrs) : PathMaker(attr) {
    override fun makeRect(path: Path) {
        if (!attr.validRect()) return
        path.reset()
        val maxRadius = min(contentRectF.width() / 2f, contentRectF.height() / 2f)
        path.addRoundRect(
            contentRectF,
            floatArrayOf(
                maxRadius, maxRadius,
                maxRadius, maxRadius,
                maxRadius, maxRadius,
                maxRadius, maxRadius,
            ),
            Path.Direction.CW
        )
    }

    override fun makeBorder(path: Path) {
        if (!attr.validBorder()) return
        path.reset()
        val target = RectF(
            contentRectF.left + attr.borderWidth / 2f,
            contentRectF.top + attr.borderWidth / 2f,
            contentRectF.right - attr.borderWidth / 2f,
            contentRectF.bottom - attr.borderWidth / 2f
        )
        val maxRadius = min(target.width() / 2f, target.height() / 2f)
        path.addRoundRect(
            target,
            floatArrayOf(
                maxRadius, maxRadius,
                maxRadius, maxRadius,
                maxRadius, maxRadius,
                maxRadius, maxRadius,
            ),
            Path.Direction.CW
        )
    }

    override fun makeProgress(path: Pm) {
        if (!attr.validProgress()) return
        path[0].first.reset()
        path[1].first.reset()
        path[2].first.reset()

        when (attr.progressStyle) {
            ProgressStyle.BORDER -> makeProgressBorder(path)
            ProgressStyle.SECTOR -> makeProgressSector(path)
            ProgressStyle.LINE -> makeProgressLine(path)
        }

        path[0].second.setPath(path[0].first, false)
        path[1].second.setPath(path[1].first, false)
        path[2].second.setPath(path[2].first, false)
    }

    private fun makeProgressLine(path: Pm) {
        val outRectF = contentRectF.contentShrink(attr.progressStrokeWidth / 2f)
        val outRadius = min(outRectF.width() / 2f, outRectF.height() / 2f)
        val outRadii = createRadii(outRadius)
        path[0].first.addRoundRect(outRectF, outRadii, Path.Direction.CW)

        val middleRectF = contentRectF.contentShrink(attr.progressStrokeWidth)
        val middleRadius = min(middleRectF.width() / 2f, middleRectF.height() / 2f)
        val middleRadii = createRadii(middleRadius)
        path[1].first.addRoundRect(middleRectF, middleRadii, Path.Direction.CW)
    }

    private fun makeProgressSector(path: Pm) {
        val maxRadius = min(contentRectF.width() / 2f, contentRectF.height() / 2f)
        path[1].first.addRoundRect(
            contentRectF,
            floatArrayOf(
                maxRadius, maxRadius,
                maxRadius, maxRadius,
                maxRadius, maxRadius,
                maxRadius, maxRadius,
            ),
            Path.Direction.CW
        )
    }

    private fun makeProgressBorder(path: Pm) {

        val maxRadius = min(contentRectF.width() / 2f, contentRectF.height() / 2f)
        val outRadius = maxRadius - attr.progressStrokeWidth / 2f
        val middleRadius = maxRadius - attr.progressStrokeWidth - attr.progressSolidWidth / 2f
        val innerRadius =
            maxRadius - attr.progressStrokeWidth - attr.progressSolidWidth - attr.progressStrokeWidth / 2f

        val outRectF = contentRectF.contentShrink(attr.progressStrokeWidth / 2f)
        path[0].first.addRoundRect(outRectF, createRadii(outRadius), Path.Direction.CW)
        path[0].first.close()

        val middleRectF =
            contentRectF.contentShrink(attr.progressStrokeWidth + attr.progressSolidWidth / 2f)
        path[1].first.addRoundRect(middleRectF, createRadii(middleRadius), Path.Direction.CW)
        path[1].first.close()

        val innerRectF =
            contentRectF.contentShrink(attr.progressStrokeWidth + attr.progressSolidWidth + attr.progressStrokeWidth / 2f)
        path[2].first.addRoundRect(innerRectF, createRadii(innerRadius), Path.Direction.CW)
        path[2].first.close()

    }

    override fun drawProgress(canvas: Canvas, path: Pm, paint: Paint) {
        when (attr.progressStyle) {
            ProgressStyle.BORDER -> drawProgressBorder(canvas, path, paint)
            ProgressStyle.SECTOR -> drawProgressSector(canvas, path, paint)
            ProgressStyle.LINE -> drawProgressLine(canvas, path, paint)
        }
    }

    private fun drawProgressLine(canvas: Canvas, path: Pm, paint: Paint) {
        paint.style = Paint.Style.FILL
        paint.shader = LinearGradient(
            contentRectF.left,
            contentRectF.centerY(),
            contentRectF.right,
            contentRectF.centerY(),
            intArrayOf(
                attr.progressReachColor,
                attr.progressReachColor,
                attr.progressUnReachColor,
                attr.progressUnReachColor
            ),
            floatArrayOf(
                0f,
                attr.progress / attr.progressMax,
                attr.progress / attr.progressMax,
                1f
            ),
            Shader.TileMode.CLAMP
        )
        canvas.drawPath(path[1].first, paint)


        paint.style = Paint.Style.STROKE
        paint.shader = null
        paint.strokeWidth = attr.progressStrokeWidth
        paint.color = attr.progressStrokeColor
        canvas.drawPath(path[0].first, paint)


    }

    private fun drawProgressSector(canvas: Canvas, path: Pm, paint: Paint) {
        paint.style = Paint.Style.FILL
        paint.shader = SweepGradient(
            contentRectF.centerX(),
            contentRectF.centerY(),
            intArrayOf(
                attr.progressReachColor,
                attr.progressReachColor,
                attr.progressUnReachColor,
                attr.progressUnReachColor
            ),
            floatArrayOf(0f, attr.progress / attr.progressMax, attr.progress / attr.progressMax, 1f)
        )
        canvas.drawPath(path[1].first, paint)
    }

    private fun drawProgressBorder(canvas: Canvas, path: Pm, paint: Paint) {
        if (attr.progressStrokeWidth != 0f) {
            paint.color = attr.progressStrokeColor
            paint.strokeWidth = attr.progressStrokeWidth
            paint.style = Paint.Style.STROKE
            canvas.drawPath(path[0].first, paint)
        }

        val solidPath = path[1].first
        val solidMeasure = path[1].second
        val solidLength = solidMeasure.length * (attr.progress / attr.progressMax)


        solidPath.reset()
        solidMeasure.getSegment(0f, solidMeasure.length, solidPath, true)
        paint.color = attr.progressUnReachColor
        paint.strokeWidth = attr.progressSolidWidth
        paint.style = Paint.Style.STROKE
        canvas.drawPath(solidPath, paint)

        solidPath.reset()
        solidMeasure.getSegment(0f, solidLength, solidPath, true)
        paint.color = attr.progressReachColor
        paint.strokeWidth = attr.progressSolidWidth
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.BUTT
        canvas.drawPath(solidPath, paint)


        if (attr.progressStrokeWidth != 0f) {
            paint.color = attr.progressStrokeColor
            paint.strokeWidth = attr.progressStrokeWidth
            paint.style = Paint.Style.STROKE
            canvas.drawPath(path[2].first, paint)
        }
    }

    override fun drawBorder(canvas: Canvas, path: Path, paint: Paint) {
        canvas.drawPath(path, paint)
    }

    override fun drawRect(canvas: Canvas, path: Path, paint: Paint) {
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent, view: View) {
        var x = event.x
        if (x <= contentRectF.left) x = contentRectF.left
        else if (x >= contentRectF.right) x = contentRectF.right
        attr.progress = (abs(x - contentRectF.left) / contentRectF.width()) * attr.progressMax
        view.invalidate()
    }
}