package com.iwdael.shapeview

import android.graphics.RectF
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