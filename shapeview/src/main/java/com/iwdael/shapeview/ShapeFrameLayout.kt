package com.iwdael.shapeview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout

/**
 * author : Iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * version: 1.0
 */
class ShapeFrameLayout(cxt: Context, attrs: AttributeSet?, def: Int) : FrameLayout(cxt, attrs, def) {
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val rect: ShapeLayer

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.ShapeFrameLayout)
        val attr = Attrs(
            defaultBackgroundColor = ta.getColor(
                R.styleable.ShapeFrameLayout_defaultBackgroundColor,
                Color.TRANSPARENT
            ),
            pressedBackgroundColor = ta.getColor(
                R.styleable.ShapeFrameLayout_pressedBackgroundColor,
                Color.TRANSPARENT
            ),
            selectedBackgroundColor = ta.getColor(
                R.styleable.ShapeFrameLayout_selectedBackgroundColor,
                Color.TRANSPARENT
            ),
            focusedBackgroundColor = ta.getColor(
                R.styleable.ShapeFrameLayout_focusedBackgroundColor,
                Color.TRANSPARENT
            ),
            checkedBackgroundColor = ta.getColor(
                R.styleable.ShapeFrameLayout_checkedBackgroundColor,
                Color.TRANSPARENT
            ),
            touchedBackgroundColor = ta.getColor(
                R.styleable.ShapeFrameLayout_touchedBackgroundColor,
                Color.TRANSPARENT
            ),
            borderWidth = ta.getDimension(R.styleable.ShapeFrameLayout_borderWidth, 0f),
            defaultBorderColor = ta.getColor(
                R.styleable.ShapeFrameLayout_defaultBorderColor,
                Color.TRANSPARENT
            ),
            pressedBorderColor = ta.getColor(
                R.styleable.ShapeFrameLayout_pressedBorderColor,
                Color.TRANSPARENT
            ),
            selectedBorderColor = ta.getColor(
                R.styleable.ShapeFrameLayout_selectedBorderColor,
                Color.TRANSPARENT
            ),
            focusedBorderColor = ta.getColor(
                R.styleable.ShapeFrameLayout_focusedBorderColor,
                Color.TRANSPARENT
            ),
            checkedBorderColor = ta.getColor(
                R.styleable.ShapeFrameLayout_checkedBorderColor,
                Color.TRANSPARENT
            ),
            touchedBorderColor = ta.getColor(
                R.styleable.ShapeFrameLayout_touchedBorderColor,
                Color.TRANSPARENT
            ),

            defaultShadowColor = ta.getColor(
                R.styleable.ShapeFrameLayout_defaultShadowColor,
                Color.TRANSPARENT
            ),
            pressedShadowColor = ta.getColor(
                R.styleable.ShapeFrameLayout_pressedShadowColor,
                Color.TRANSPARENT
            ),
            selectedShadowColor = ta.getColor(
                R.styleable.ShapeFrameLayout_selectedShadowColor,
                Color.TRANSPARENT
            ),
            focusedShadowColor = ta.getColor(
                R.styleable.ShapeFrameLayout_focusedShadowColor,
                Color.TRANSPARENT
            ),
            checkedShadowColor = ta.getColor(
                R.styleable.ShapeFrameLayout_checkedShadowColor,
                Color.TRANSPARENT
            ),
            touchedShadowColor = ta.getColor(
                R.styleable.ShapeFrameLayout_touchedShadowColor,
                Color.TRANSPARENT
            ),

            shadowPadding = ta.getDimension(R.styleable.ShapeFrameLayout_shadowPadding, 1f),
            shadowRadius = ta.getFloat(R.styleable.ShapeFrameLayout_shadowRadius, 0.5f),
            leftShadowPadding = ta.getDimension(R.styleable.ShapeFrameLayout_leftShadowPadding, -1f),
            topShadowPadding = ta.getDimension(R.styleable.ShapeFrameLayout_topShadowPadding, -1f),
            rightShadowPadding = ta.getDimension(R.styleable.ShapeFrameLayout_rightShadowPadding, -1f),
            bottomShadowPadding = ta.getDimension(R.styleable.ShapeFrameLayout_bottomShadowPadding, -1f),
            shadowDx = ta.getDimension(R.styleable.ShapeFrameLayout_shadowDx, 0f),
            shadowDy = ta.getDimension(R.styleable.ShapeFrameLayout_shadowDy, 0f),
            radius = try {
                ta.getDimension(R.styleable.ShapeFrameLayout_radius, 0f)
            } catch (e: Exception) {
                0f
            },
            shapeStyle = try {
                ta.getInt(R.styleable.ShapeFrameLayout_radius, -1)
            } catch (e: Exception) {
                0
            }.toShapeStyle(),
            ltRadius = ta.getDimension(R.styleable.ShapeFrameLayout_ltRadius, -1f),
            lbRadius = ta.getDimension(R.styleable.ShapeFrameLayout_lbRadius, -1f),
            rtRadius = ta.getDimension(R.styleable.ShapeFrameLayout_rtRadius, -1f),
            rbRadius = ta.getDimension(R.styleable.ShapeFrameLayout_rbRadius, -1f),
            progressStyle = ta.getInt(R.styleable.ShapeFrameLayout_progressStyle, 0).toProgressStyle(),
            defaultProgressStrokeColor = ta.getColor(
                R.styleable.ShapeFrameLayout_defaultProgressStrokeColor,
                Color.TRANSPARENT
            ),
            pressedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeFrameLayout_pressedProgressStrokeColor,
                Color.TRANSPARENT
            ),
            selectedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeFrameLayout_selectedProgressStrokeColor,
                Color.TRANSPARENT
            ),
            focusedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeFrameLayout_focusedProgressStrokeColor,
                Color.TRANSPARENT
            ),
            checkedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeFrameLayout_checkedProgressStrokeColor,
                Color.TRANSPARENT
            ),
            touchedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeFrameLayout_touchedProgressStrokeColor,
                Color.TRANSPARENT
            ),

            progressStrokeWidth = ta.getDimension(R.styleable.ShapeFrameLayout_progressStrokeWidth, 0f),
            defaultProgressReachColor = ta.getColor(
                R.styleable.ShapeFrameLayout_defaultProgressReachColor,
                Color.TRANSPARENT
            ),
            pressedProgressReachColor = ta.getColor(
                R.styleable.ShapeFrameLayout_pressedProgressReachColor,
                Color.TRANSPARENT
            ),
            selectedProgressReachColor = ta.getColor(
                R.styleable.ShapeFrameLayout_selectedProgressReachColor,
                Color.TRANSPARENT
            ),
            focusedProgressReachColor = ta.getColor(
                R.styleable.ShapeFrameLayout_focusedProgressReachColor,
                Color.TRANSPARENT
            ),
            checkedProgressReachColor = ta.getColor(
                R.styleable.ShapeFrameLayout_checkedProgressReachColor,
                Color.TRANSPARENT
            ),
            touchedProgressReachColor = ta.getColor(
                R.styleable.ShapeFrameLayout_touchedProgressReachColor,
                Color.TRANSPARENT
            ),
            defaultProgressUnReachColor = ta.getColor(
                R.styleable.ShapeFrameLayout_defaultProgressUnReachColor,
                Color.TRANSPARENT
            ),
            pressedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeFrameLayout_pressedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            selectedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeFrameLayout_selectedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            focusedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeFrameLayout_focusedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            checkedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeFrameLayout_checkedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            touchedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeFrameLayout_touchedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            progressSolidWidth = ta.getDimension(R.styleable.ShapeFrameLayout_progressSolidWidth, 0f),
            progress = ta.getFloat(R.styleable.ShapeFrameLayout_progress, 0f),
            progressMax = ta.getFloat(R.styleable.ShapeFrameLayout_progressMax, 0f),
            enableDragProgress = ta.getBoolean(R.styleable.ShapeFrameLayout_enableDragProgress, false),
        )
        ta.recycle()
        rect = ShapeLayer(this, attr)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (rect.attr.validProgress() && rect.attr.enableDragProgress) {
            rect.onTouchEvent(event)
            super.onTouchEvent(event)
            true
        } else {
            super.onTouchEvent(event)
        }
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        rect.sizeChange(w, h)
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas) {
        rect.draw(canvas)
        super.onDraw(canvas)
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        rect.attr.refreshStateColor(State.SELECTED, selected)
        invalidate()
    }

    override fun setPressed(pressed: Boolean) {
        super.setPressed(pressed)
        rect.attr.refreshStateColor(State.PRESSED, pressed)
        invalidate()
    }

}