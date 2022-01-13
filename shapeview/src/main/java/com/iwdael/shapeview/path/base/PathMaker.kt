package com.iwdael.shapeview.path.base

import android.graphics.*
import android.view.MotionEvent
import android.view.View
import com.iwdael.shapeview.Attrs
import com.iwdael.shapeview.Pm
import com.iwdael.shapeview.ShapeStyle
import com.iwdael.shapeview.path.*

/**
 * author : Iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * version: 1.0
 */
abstract class PathMaker(protected val attr: Attrs) {
    private val sizeRectF = RectF()
    protected val contentRectF = RectF()
    fun reset(width: Int, height: Int) {
        sizeRectF.set(0f, 0f, width.toFloat(), height.toFloat())
        contentRectF.set(
            attr.leftShadow(),
            attr.topShadow(),
            width.toFloat() - attr.rightShadow(),
            height.toFloat() - attr.bottomShadow()
        )
    }

    abstract fun makeRect(path: Path)
    abstract fun makeBorder(path: Path)
    abstract fun makeProgress(path: Pm)

    companion object {

        private fun checkMaker(makerClass: Class<out PathMaker>, attr: Attrs): PathMaker {
            return makerClass.getConstructor(Attrs::class.java).newInstance(attr)
        }

        @JvmStatic
        fun obtainMaker(attr: Attrs): PathMaker {
            return when (attr.shapeStyle) {
                ShapeStyle.ROUND -> checkMaker(RoundPathMaker::class.java, attr)
                ShapeStyle.OVAL -> checkMaker(OvalPathMaker::class.java, attr)
                ShapeStyle.CIRCLE -> checkMaker(CirclePathMaker::class.java, attr)
                else -> checkMaker(RadiusPathMaker::class.java, attr)
            }
        }
    }


    abstract fun drawProgress(canvas: Canvas, path: Pm, paint: Paint)
    abstract fun drawBorder(canvas: Canvas, path: Path, paint: Paint)
    abstract fun drawRect(canvas: Canvas, path: Path, paint: Paint)
    abstract fun onTouchEvent(event: MotionEvent, view: View)
}