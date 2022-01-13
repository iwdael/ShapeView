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
class ShapeLayer(private val view: View, val attr: Attrs) {


    private val pmRect = Pms(1)
    private val pmBorder = Pms(1)
    private val pmProgress = Pms(3) //进度

    private val pathMaker = PathMaker.obtainMaker(attr, pmRect, pmBorder, pmProgress)

    init {
        view.setBackgroundColor(Color.TRANSPARENT)
        attr.renderRectPaint(view, pmRect)
        attr.renderBorderPaint(view, pmBorder)
        attr.renderProgressPaint(view, pmProgress)
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