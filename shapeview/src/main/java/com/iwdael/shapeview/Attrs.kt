package com.iwdael.shapeview

import android.graphics.Color
import android.graphics.Paint
import android.view.View
import kotlin.math.max

/**
 * author : Iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * version: 1.0
 */

/**
 *<attr name="defaultBackgroundColor" format="color" />
<attr name="borderWidth" format="dimension" />
<attr name="borderColor" format="color" />
<attr name="shadowColor" format="color" />
<attr name="shadowDx" format="dimension" />
<attr name="shadowDy" format="dimension" />
<attr name="radius" format="dimension" />
<attr name="ltRadius" format="dimension" />
<attr name="lbRadius" format="dimension" />
<attr name="rtRadius" format="dimension" />
<attr name="rbRadius" format="dimension" />
 */
class Attrs(
    private val defaultBackgroundColor: Int,
    val borderWidth: Float,
    private val borderColor: Int,
    private val shadowColor: Int,
    private val shadow: Float,
    private val shadowRadius: Float,
    private val leftShadow: Float,
    private val rightShadow: Float,
    private val topShadow: Float,
    private val bottomShadow: Float,
    private val shadowDx: Float,
    private val shadowDy: Float,
    private val radius: Float,
    val shapeStyle: ShapeStyle,
    private val ltRadius: Float,
    private val lbRadius: Float,
    private val rtRadius: Float,
    private val rbRadius: Float,
    val progressStyle: ProgressStyle,
    val progressReachColor: Int,
    val progressUnReachColor: Int,
    val progressStrokeWidth: Float,
    val progressStrokeColor: Int,
    val progressSolidWidth: Float,
    var progress: Float,
    val progressMax: Float,
    val enableDragProgress: Boolean,
) {
    fun ltRadius() = if (ltRadius != -1f) ltRadius else radius
    fun lbRadius() = if (lbRadius != -1f) lbRadius else radius
    fun rtRadius() = if (rtRadius != -1f) rtRadius else radius
    fun rbRadius() = if (rbRadius != -1f) rbRadius else radius


    fun leftShadow() = if (leftShadow != -1f) leftShadow else shadow
    fun rightShadow() = if (rightShadow != -1f) rightShadow else shadow
    fun topShadow() = if (topShadow != -1f) topShadow else shadow
    fun bottomShadow() = if (bottomShadow != -1f) bottomShadow else shadow


    fun renderRectPaint(view: View, pms: Pms) {
        if (!validRect()) return
        val paint = pms[0].paint
        paint.isAntiAlias = true
        if (shadowColor != Color.TRANSPARENT) {
            paint.setShadowLayer(
                max(
                    max(
                        max(leftShadow(), topShadow()),
                        rightShadow()
                    ),
                    bottomShadow()
                ) * shadowRadius,
                shadowDx,
                shadowDy,
                shadowColor
            )
        }
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.color = defaultBackgroundColor
    }

    fun renderBorderPaint(view: View, pms: Pms) {
        if (!validBorder()) return
        val paint = pms[0].paint
        paint.strokeWidth = borderWidth
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.color = borderColor
    }

    fun renderProgressPaint(view: View, pms: Pms) {
        if (!validProgress()) return
        pms[0].paint.isAntiAlias = true
        pms[1].paint.isAntiAlias = true
        pms[2].paint.isAntiAlias = true
        when (progressStyle) {
            ProgressStyle.BORDER -> {
                pms[0].paint.style = Paint.Style.STROKE
                pms[0].paint.color = progressStrokeColor
                pms[0].paint.strokeWidth = progressStrokeWidth

                pms[1].paint.style = Paint.Style.STROKE
                pms[1].paint.color = progressUnReachColor
                pms[1].paint.strokeWidth = progressSolidWidth

                pms[2].paint.style = Paint.Style.STROKE
                pms[2].paint.color = progressStrokeColor
                pms[2].paint.strokeWidth = progressStrokeWidth
            }
            ProgressStyle.LINE -> {
                pms[0].paint.style = Paint.Style.STROKE
                pms[0].paint.color = progressStrokeColor
                pms[0].paint.strokeWidth = progressStrokeWidth

                pms[1].paint.style = Paint.Style.FILL

                pms[2].paint.style = Paint.Style.STROKE
                pms[2].paint.color = progressStrokeColor
                pms[2].paint.strokeWidth = progressStrokeWidth
            }
            ProgressStyle.SECTOR -> {
                pms[0].paint.style = Paint.Style.STROKE
                pms[0].paint.color = progressStrokeColor
                pms[0].paint.strokeWidth = progressStrokeWidth

                pms[1].paint.style = Paint.Style.FILL
                pms[1].paint.strokeWidth = progressSolidWidth

                pms[2].paint.style = Paint.Style.STROKE
                pms[2].paint.color = progressStrokeColor
                pms[2].paint.strokeWidth = progressStrokeWidth
            }
        }
    }

    fun validRect(): Boolean {
        return defaultBackgroundColor != Color.TRANSPARENT
    }

    fun validBorder(): Boolean {
        return borderColor != Color.TRANSPARENT && borderWidth > 0f
    }

    fun validProgress(): Boolean {
        return progressStyle != ProgressStyle.UNKNOWN
    }


}