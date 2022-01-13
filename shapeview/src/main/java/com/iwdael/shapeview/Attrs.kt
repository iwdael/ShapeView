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

class Attrs(
    private val defaultBackgroundColor: Int,
    private val pressedBackgroundColor: Int,
    private val selectedBackgroundColor: Int,
    private val focusedBackgroundColor: Int,
    private val checkedBackgroundColor: Int,
    private val touchedBackgroundColor: Int,
    val borderWidth: Float,
    private val defaultBorderColor: Int,
    private val pressedBorderColor: Int,
    private val selectedBorderColor: Int,
    private val focusedBorderColor: Int,
    private val checkedBorderColor: Int,
    private val touchedBorderColor: Int,
    private val defaultShadowColor: Int,
    private val pressedShadowColor: Int,
    private val selectedShadowColor: Int,
    private val focusedShadowColor: Int,
    private val checkedShadowColor: Int,
    private val touchedShadowColor: Int,
    private val shadowPadding: Float,
    private val shadowRadius: Float,
    private val leftShadowPadding: Float,
    private val rightShadowPadding: Float,
    private val topShadowPadding: Float,
    private val bottomShadowPadding: Float,
    private val shadowDx: Float,
    private val shadowDy: Float,
    private val radius: Float,
    val shapeStyle: ShapeStyle,
    private val ltRadius: Float,
    private val lbRadius: Float,
    private val rtRadius: Float,
    private val rbRadius: Float,
    val progressStyle: ProgressStyle,
    private val defaultProgressReachColor: Int,
    private val pressedProgressReachColor: Int,
    private val selectedProgressReachColor: Int,
    private val focusedProgressReachColor: Int,
    private val checkedProgressReachColor: Int,
    private val touchedProgressReachColor: Int,

    private val defaultProgressUnReachColor: Int,
    private val pressedProgressUnReachColor: Int,
    private val selectedProgressUnReachColor: Int,
    private val focusedProgressUnReachColor: Int,
    private val checkedProgressUnReachColor: Int,
    private val touchedProgressUnReachColor: Int,

    val progressStrokeWidth: Float,
    private val defaultProgressStrokeColor: Int,
    private val pressedProgressStrokeColor: Int,
    private val selectedProgressStrokeColor: Int,
    private val focusedProgressStrokeColor: Int,
    private val checkedProgressStrokeColor: Int,
    private val touchedProgressStrokeColor: Int,
    val progressSolidWidth: Float,
    var progress: Float,
    val progressMax: Float,
    val enableDragProgress: Boolean,
) {
    private val states = mutableMapOf<String, State>()

    fun refreshStateColor(state: State, attach: Boolean) {
        if (attach) {
            if (!states.containsKey(state.name)) states[state.name] = state
        } else {
            states.remove(state.name)
        }
    }

    private fun topState(): State {
        return states.values.lastOrNull() ?: State.DEFAULT
    }

    private fun backgroundColor(): Int {
        val color = when (topState()) {
            State.SELECTED -> selectedBackgroundColor
            State.CHECKED -> checkedBackgroundColor
            State.FOCUSED -> focusedBackgroundColor
            State.PRESSED -> pressedBackgroundColor
            State.TOUCHED -> touchedBackgroundColor
            State.DEFAULT -> defaultBackgroundColor
        }
        return if (color != Color.TRANSPARENT) color
        else defaultBackgroundColor
    }

    private fun borderColor(): Int {
        val color = when (topState()) {
            State.SELECTED -> selectedBorderColor
            State.CHECKED -> checkedBorderColor
            State.FOCUSED -> focusedBorderColor
            State.PRESSED -> pressedBorderColor
            State.TOUCHED -> touchedBorderColor
            State.DEFAULT -> defaultBorderColor
        }
        return if (color != Color.TRANSPARENT) color
        else defaultBorderColor
    }

    private fun shadowColor(): Int {
        val color = when (topState()) {
            State.SELECTED -> selectedShadowColor
            State.CHECKED -> checkedShadowColor
            State.FOCUSED -> focusedShadowColor
            State.PRESSED -> pressedShadowColor
            State.TOUCHED -> touchedShadowColor
            State.DEFAULT -> defaultShadowColor
        }
        return if (color != Color.TRANSPARENT) color
        else defaultShadowColor
    }

    private fun progressStrokeColor(): Int {
        val color = when (topState()) {
            State.SELECTED -> selectedProgressStrokeColor
            State.CHECKED -> checkedProgressStrokeColor
            State.FOCUSED -> focusedProgressStrokeColor
            State.PRESSED -> pressedProgressStrokeColor
            State.TOUCHED -> touchedProgressStrokeColor
            State.DEFAULT -> defaultProgressStrokeColor
        }
        return if (color != Color.TRANSPARENT) color
        else defaultProgressStrokeColor
    }

    fun progressReachColor(): Int {
        val color = when (topState()) {
            State.SELECTED -> selectedProgressReachColor
            State.CHECKED -> checkedProgressReachColor
            State.FOCUSED -> focusedProgressReachColor
            State.PRESSED -> pressedProgressReachColor
            State.TOUCHED -> touchedProgressReachColor
            State.DEFAULT -> defaultProgressReachColor
        }
        return if (color != Color.TRANSPARENT) color
        else defaultProgressReachColor
    }

    fun progressUnReachColor(): Int {
        val color = when (topState()) {
            State.SELECTED -> selectedProgressUnReachColor
            State.CHECKED -> checkedProgressUnReachColor
            State.FOCUSED -> focusedProgressUnReachColor
            State.PRESSED -> pressedProgressUnReachColor
            State.TOUCHED -> touchedProgressUnReachColor
            State.DEFAULT -> defaultProgressUnReachColor
        }
        return if (color != Color.TRANSPARENT) color
        else defaultProgressUnReachColor
    }


    fun ltRadius() = if (ltRadius != -1f) ltRadius else radius
    fun lbRadius() = if (lbRadius != -1f) lbRadius else radius
    fun rtRadius() = if (rtRadius != -1f) rtRadius else radius
    fun rbRadius() = if (rbRadius != -1f) rbRadius else radius


    fun leftShadowPadding() = if (leftShadowPadding != -1f) leftShadowPadding else shadowPadding
    fun rightShadowPadding() = if (rightShadowPadding != -1f) rightShadowPadding else shadowPadding
    fun topShadowPadding() = if (topShadowPadding != -1f) topShadowPadding else shadowPadding
    fun bottomShadowPadding() = if (bottomShadowPadding != -1f) bottomShadowPadding else shadowPadding


    fun renderRectPaint(view: View, pms: Pms) {
        if (!validRect()) return
        val paint = pms[0].paint
        paint.isAntiAlias = true
        if (shadowColor() != Color.TRANSPARENT) {
            paint.setShadowLayer(
                max(
                    max(
                        max(leftShadowPadding(), topShadowPadding()),
                        rightShadowPadding()
                    ),
                    bottomShadowPadding()
                ) * shadowRadius,
                shadowDx,
                shadowDy,
                shadowColor()
            )
        }
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.color = backgroundColor()
    }

    fun renderBorderPaint(view: View, pms: Pms) {
        if (!validBorder()) return
        val paint = pms[0].paint
        paint.strokeWidth = borderWidth
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.color = borderColor()
    }

    fun renderProgressPaint(view: View, pms: Pms) {
        if (!validProgress()) return
        pms[0].paint.isAntiAlias = true
        pms[1].paint.isAntiAlias = true
        pms[2].paint.isAntiAlias = true
        when (progressStyle) {
            ProgressStyle.BORDER -> {
                pms[0].paint.style = Paint.Style.STROKE
                pms[0].paint.color = progressStrokeColor()
                pms[0].paint.strokeWidth = progressStrokeWidth

                pms[1].paint.style = Paint.Style.STROKE
                pms[1].paint.color = defaultProgressUnReachColor
                pms[1].paint.strokeWidth = progressSolidWidth

                pms[2].paint.style = Paint.Style.STROKE
                pms[2].paint.color = progressStrokeColor()
                pms[2].paint.strokeWidth = progressStrokeWidth
            }
            ProgressStyle.LINE -> {
                pms[0].paint.style = Paint.Style.STROKE
                pms[0].paint.color = progressStrokeColor()
                pms[0].paint.strokeWidth = progressStrokeWidth

                pms[1].paint.style = Paint.Style.FILL

                pms[2].paint.style = Paint.Style.STROKE
                pms[2].paint.color = progressStrokeColor()
                pms[2].paint.strokeWidth = progressStrokeWidth
            }
            ProgressStyle.SECTOR -> {
                pms[0].paint.style = Paint.Style.STROKE
                pms[0].paint.color = progressStrokeColor()
                pms[0].paint.strokeWidth = progressStrokeWidth

                pms[1].paint.style = Paint.Style.FILL
                pms[1].paint.strokeWidth = progressSolidWidth

                pms[2].paint.style = Paint.Style.STROKE
                pms[2].paint.color = progressStrokeColor()
                pms[2].paint.strokeWidth = progressStrokeWidth
            }
        }
    }

    fun validRect(): Boolean {
        return backgroundColor() != Color.TRANSPARENT
    }

    fun validBorder(): Boolean {
        return borderColor() != Color.TRANSPARENT && borderWidth > 0f
    }

    fun validProgress(): Boolean {
        return progressStyle != ProgressStyle.UNKNOWN
    }


}