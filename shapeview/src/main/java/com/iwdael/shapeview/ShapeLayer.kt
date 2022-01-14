package com.iwdael.shapeview

import android.animation.ValueAnimator
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import com.iwdael.shapeview.path.base.PathMaker
import kotlin.math.abs

/**
 * author : Iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * version: 1.0
 */
class ShapeLayer(private val view: View, val attr: Attrs) {


    private val pmRect = Pms(1)
    private val pmBorder = Pms(1)
    private val pmProgress = Pms(3) //进度

    private val pathMaker = PathMaker.obtainMaker(attr, pmRect, pmBorder, pmProgress)
    private var anim: ValueAnimator? = null

    init {
        view.setBackgroundColor(Color.TRANSPARENT)
        attr.renderRectPaint(view, pmRect)
        attr.renderBorderPaint(view, pmBorder)
        attr.renderProgressPaint(view, pmProgress)
    }

    fun setProgress(progress: Float) {
        val willProgress = when {
            progress < 0f -> 0f
            progress > attr.progressMax -> attr.progressMax
            else -> progress
        }
        if (attr.progress == willProgress) return
        attr.progress = willProgress
        view.invalidate()
    }

    fun setProgress(progress: Float, duration: Long) {
        val willProgress = when {
            progress < 0f -> 0f
            progress > attr.progressMax -> attr.progressMax
            else -> progress
        }
        if (attr.progress == willProgress) return
        anim?.removeAllUpdateListeners()
        anim?.cancel()
        anim = ValueAnimator.ofFloat(attr.progress, willProgress)
        anim?.duration = ((abs(attr.progress - willProgress) / attr.progressMax) * duration).toLong()
        anim?.interpolator = AccelerateDecelerateInterpolator()
        anim?.addUpdateListener {
            val value = it.animatedValue as Float
            attr.progress = value
            view.invalidate()
        }
        anim?.start()
    }

    fun sizeChange(width: Int, height: Int) {
        pathMaker.reset(width, height)
        pathMaker.makeRect()
        pathMaker.makeBorder()
        pathMaker.makeProgress()
    }

    fun draw(canvas: Canvas) {
        if (attr.validRect()) pathMaker.drawRect(canvas)
        if (attr.validBorder()) pathMaker.drawBorder(canvas)
        if (attr.validProgress()) pathMaker.drawProgress(canvas)
    }

    fun onTouchEvent(event: MotionEvent) = pathMaker.onTouchEvent(event, view)

}