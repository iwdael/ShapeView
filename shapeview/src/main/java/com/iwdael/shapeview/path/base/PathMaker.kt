package com.iwdael.shapeview.path.base

import android.graphics.*
import android.view.MotionEvent
import android.view.View
import com.iwdael.shapeview.Attrs
import com.iwdael.shapeview.Pms
import com.iwdael.shapeview.ShapeStyle
import com.iwdael.shapeview.path.*

/**
 * author : Iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * version: 1.0
 */
abstract class PathMaker(
    protected val attr: Attrs,
    protected val pmRect: Pms,
    protected val pmBorder: Pms,
    protected val pmProgress: Pms
) {
    private val sizeRectF = RectF()
    protected val contentRectF = RectF()
    protected var startAngle = 0f
    fun reset(width: Int, height: Int) {
        sizeRectF.set(0f, 0f, width.toFloat(), height.toFloat())
        contentRectF.set(
            attr.leftShadow(),
            attr.topShadow(),
            width.toFloat() - attr.rightShadow(),
            height.toFloat() - attr.bottomShadow()
        )
    }

    abstract fun makeRect()
    abstract fun makeBorder()
    abstract fun makeProgress()

    companion object {

        private fun checkMaker(
            makerClass: Class<out PathMaker>,
            attr: Attrs,
            pmRect: Pms,
            pmBorder: Pms,
            pmProgress: Pms
        ): PathMaker {
            return makerClass.getConstructor(
                Attrs::class.java,
                Pms::class.java,
                Pms::class.java,
                Pms::class.java
            ).newInstance(attr, pmRect, pmBorder, pmProgress)
        }

        @JvmStatic
        fun obtainMaker(attr: Attrs, pmRect: Pms, pmBorder: Pms, pmProgress: Pms): PathMaker {
            return when (attr.shapeStyle) {
                ShapeStyle.ROUND -> checkMaker(
                    RoundPathMaker::class.java,
                    attr,
                    pmRect,
                    pmBorder,
                    pmProgress
                )
                ShapeStyle.OVAL -> checkMaker(
                    OvalPathMaker::class.java,
                    attr,
                    pmRect,
                    pmBorder,
                    pmProgress
                )
                ShapeStyle.CIRCLE -> checkMaker(
                    CirclePathMaker::class.java,
                    attr,
                    pmRect,
                    pmBorder,
                    pmProgress
                )
                else -> checkMaker(RadiusPathMaker::class.java, attr, pmRect, pmBorder, pmProgress)
            }
        }
    }


    abstract fun drawProgress(canvas: Canvas)
    abstract fun drawBorder(canvas: Canvas)
    abstract fun drawRect(canvas: Canvas)
    abstract fun onTouchEvent(event: MotionEvent, view: View)
}