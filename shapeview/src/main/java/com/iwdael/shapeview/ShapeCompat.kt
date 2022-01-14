package com.iwdael.shapeview

import android.graphics.*
import kotlin.math.PI
import kotlin.math.asin
import kotlin.math.sqrt

/**
 * author : Iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * version: 1.0
 */
fun Int.toShapeStyle(): ShapeStyle {
    return when (this) {
        1 -> ShapeStyle.ROUND
        2 -> ShapeStyle.OVAL
        3 -> ShapeStyle.CIRCLE
        else -> ShapeStyle.UNKNOWN
    }
}


fun Int.toProgressStyle(): ProgressStyle {
    return when (this) {
        0 -> ProgressStyle.UNKNOWN
        1 -> ProgressStyle.BORDER
        2 -> ProgressStyle.SECTOR
        3 -> ProgressStyle.LINE
        else -> ProgressStyle.UNKNOWN
    }
}


fun getAngle(sideX: Float, sideY: Float): Float {
    val hypotenuse = sqrt(sideX * sideX + sideY * sideY)
    val sin = sideY / hypotenuse
    val angle = ((asin(sin) / (PI / 2)) * 90f).toFloat()
    return if (sideX > 0 && sideY < 0)
        90f + angle
    else if (sideX >= 0 && sideY >= 0)
        90f + angle
    else if (sideX < 0 && sideY > 0)
        270f - angle
    else 270f - angle
}

fun RectF.contentShrink(size: Float): RectF {
    return RectF(this.left + size, this.top + size, this.right - size, this.bottom - size)
}

fun createRadii(rad: Float): FloatArray {
    return floatArrayOf(
        rad, rad,
        rad, rad,
        rad, rad,
        rad, rad,
    )
}

fun makeLinearGradient(
    cur: Float, max: Float, rad: Float, unReach: Int, reach: Int, start: PointF, end: PointF
): LinearGradient {
    val step = 1 / max
    val colorArr = mutableListOf<Int>()
    val positionArr = mutableListOf<Float>()
    val range = floatArrayOf(0f, 0f, 0f, 0f)
    val sheet = step * rad
    val offset = ((1f - rad) / 2f) * step
    for (index in 0 until max.toInt()) {
        range[0] = step * index
        range[1] = range[0] + offset
        range[2] = range[1] + sheet
        range[3] = range[2] + offset
        if (index < cur) {
            if (index == cur.toInt()) {
                colorArr.add(Color.TRANSPARENT)
                colorArr.add(Color.TRANSPARENT)
                colorArr.add(reach)
                colorArr.add(reach)
                colorArr.add(unReach)
                colorArr.add(unReach)
                colorArr.add(Color.TRANSPARENT)
                colorArr.add(Color.TRANSPARENT)
                positionArr.add(range[0])
                positionArr.add(range[1])
                positionArr.add(range[1])
                positionArr.add(range[1] + step * rad * (cur - cur.toInt().toFloat()))
                positionArr.add(range[1] + step * rad * (cur - cur.toInt().toFloat()))
                positionArr.add(range[2])
                positionArr.add(range[2])
                positionArr.add(range[3])
            } else {
                colorArr.add(Color.TRANSPARENT)
                colorArr.add(Color.TRANSPARENT)
                colorArr.add(reach)
                colorArr.add(reach)
                colorArr.add(Color.TRANSPARENT)
                colorArr.add(Color.TRANSPARENT)
                positionArr.add(range[0])
                positionArr.add(range[1])
                positionArr.add(range[1])
                positionArr.add(range[2])
                positionArr.add(range[2])
                positionArr.add(range[3])
            }
        } else {
            colorArr.add(Color.TRANSPARENT)
            colorArr.add(Color.TRANSPARENT)
            colorArr.add(unReach)
            colorArr.add(unReach)
            colorArr.add(Color.TRANSPARENT)
            colorArr.add(Color.TRANSPARENT)
            positionArr.add(range[0])
            positionArr.add(range[1])
            positionArr.add(range[1])
            positionArr.add(range[2])
            positionArr.add(range[2])
            positionArr.add(range[3])
        }
    }
    return LinearGradient(
        start.x, start.y, end.x, end.y,
        colorArr.toIntArray(), positionArr.toFloatArray(), Shader.TileMode.CLAMP
    )
}