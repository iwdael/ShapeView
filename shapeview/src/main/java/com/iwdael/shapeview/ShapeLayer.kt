package com.iwdael.shapeview

import android.graphics.*
import android.view.MotionEvent
import android.view.View
import com.iwdael.shapeview.path.base.PathMaker

/**
 * author : Iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * version: 1.0
 */
class ShapeLayer(private val view: View,   val attr: Attrs) {

    private val rectPaint = Paint()
    private val borderPaint = Paint()
    private val progressPaint = Paint()

    private val rectPath = Path()
    private val borderPath = Path()
    private val progressPath = Pm(Path() to PathMeasure(), Path() to PathMeasure(), Path() to PathMeasure())

    private val pathMaker = PathMaker.obtainMaker(attr)

    init {
        view.setBackgroundColor(Color.TRANSPARENT)
        attr.renderRectPaint(view, rectPaint)
        attr.renderBorderPaint(view, borderPaint)
        attr.renderProgressPaint(view, progressPaint)
    }


    fun sizeChange(width: Int, height: Int) {
        pathMaker.reset(width, height)
        pathMaker.makeRect(rectPath)
        pathMaker.makeBorder(borderPath)
        pathMaker.makeProgress(progressPath)
    }

    fun draw(canvas: Canvas) {
        if (attr.validRect()) pathMaker.drawRect(canvas, rectPath, rectPaint)
        if (attr.validBorder()) pathMaker.drawBorder(canvas, borderPath, borderPaint)
        if (attr.validProgress()) pathMaker.drawProgress(canvas, progressPath, progressPaint)
    }

    fun onTouchEvent(event: MotionEvent) = pathMaker.onTouchEvent(event, view)

}