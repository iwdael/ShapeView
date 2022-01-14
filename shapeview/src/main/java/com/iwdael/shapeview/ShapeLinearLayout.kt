package com.iwdael.shapeview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout

/**
 * author : Iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * version: 1.0
 */
class ShapeLinearLayout(cxt: Context, attrs: AttributeSet?, def: Int) : LinearLayout(cxt, attrs, def) {
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val shapeLayer: ShapeLayer

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.ShapeLinearLayout)
        val attr = Attrs(
            defaultBackgroundColor = ta.getColor(
                R.styleable.ShapeLinearLayout_defaultBackgroundColor,
                Color.TRANSPARENT
            ),
            pressedBackgroundColor = ta.getColor(
                R.styleable.ShapeLinearLayout_pressedBackgroundColor,
                Color.TRANSPARENT
            ),
            selectedBackgroundColor = ta.getColor(
                R.styleable.ShapeLinearLayout_selectedBackgroundColor,
                Color.TRANSPARENT
            ),
            focusedBackgroundColor = ta.getColor(
                R.styleable.ShapeLinearLayout_focusedBackgroundColor,
                Color.TRANSPARENT
            ),
            checkedBackgroundColor = ta.getColor(
                R.styleable.ShapeLinearLayout_checkedBackgroundColor,
                Color.TRANSPARENT
            ),
            touchedBackgroundColor = ta.getColor(
                R.styleable.ShapeLinearLayout_touchedBackgroundColor,
                Color.TRANSPARENT
            ),
            borderWidth = ta.getDimension(R.styleable.ShapeLinearLayout_borderWidth, 0f),
            defaultBorderColor = ta.getColor(
                R.styleable.ShapeLinearLayout_defaultBorderColor,
                Color.TRANSPARENT
            ),
            pressedBorderColor = ta.getColor(
                R.styleable.ShapeLinearLayout_pressedBorderColor,
                Color.TRANSPARENT
            ),
            selectedBorderColor = ta.getColor(
                R.styleable.ShapeLinearLayout_selectedBorderColor,
                Color.TRANSPARENT
            ),
            focusedBorderColor = ta.getColor(
                R.styleable.ShapeLinearLayout_focusedBorderColor,
                Color.TRANSPARENT
            ),
            checkedBorderColor = ta.getColor(
                R.styleable.ShapeLinearLayout_checkedBorderColor,
                Color.TRANSPARENT
            ),
            touchedBorderColor = ta.getColor(
                R.styleable.ShapeLinearLayout_touchedBorderColor,
                Color.TRANSPARENT
            ),

            defaultShadowColor = ta.getColor(
                R.styleable.ShapeLinearLayout_defaultShadowColor,
                Color.TRANSPARENT
            ),
            pressedShadowColor = ta.getColor(
                R.styleable.ShapeLinearLayout_pressedShadowColor,
                Color.TRANSPARENT
            ),
            selectedShadowColor = ta.getColor(
                R.styleable.ShapeLinearLayout_selectedShadowColor,
                Color.TRANSPARENT
            ),
            focusedShadowColor = ta.getColor(
                R.styleable.ShapeLinearLayout_focusedShadowColor,
                Color.TRANSPARENT
            ),
            checkedShadowColor = ta.getColor(
                R.styleable.ShapeLinearLayout_checkedShadowColor,
                Color.TRANSPARENT
            ),
            touchedShadowColor = ta.getColor(
                R.styleable.ShapeLinearLayout_touchedShadowColor,
                Color.TRANSPARENT
            ),

            shadowPadding = ta.getDimension(R.styleable.ShapeLinearLayout_shadowPadding, 1f),
            shadowRadius = ta.getFloat(R.styleable.ShapeLinearLayout_shadowRadius, 0.5f),
            leftShadowPadding = ta.getDimension(R.styleable.ShapeLinearLayout_leftShadowPadding, -1f),
            topShadowPadding = ta.getDimension(R.styleable.ShapeLinearLayout_topShadowPadding, -1f),
            rightShadowPadding = ta.getDimension(R.styleable.ShapeLinearLayout_rightShadowPadding, -1f),
            bottomShadowPadding = ta.getDimension(R.styleable.ShapeLinearLayout_bottomShadowPadding, -1f),
            shadowDx = ta.getDimension(R.styleable.ShapeLinearLayout_shadowDx, 0f),
            shadowDy = ta.getDimension(R.styleable.ShapeLinearLayout_shadowDy, 0f),
            radius = try {
                ta.getDimension(R.styleable.ShapeLinearLayout_radius, 0f)
            } catch (e: Exception) {
                0f
            },
            shapeStyle = try {
                ta.getInt(R.styleable.ShapeLinearLayout_radius, -1)
            } catch (e: Exception) {
                0
            }.toShapeStyle(),
            ltRadius = ta.getDimension(R.styleable.ShapeLinearLayout_ltRadius, -1f),
            lbRadius = ta.getDimension(R.styleable.ShapeLinearLayout_lbRadius, -1f),
            rtRadius = ta.getDimension(R.styleable.ShapeLinearLayout_rtRadius, -1f),
            rbRadius = ta.getDimension(R.styleable.ShapeLinearLayout_rbRadius, -1f),
            progressStyle = ta.getInt(R.styleable.ShapeLinearLayout_progressStyle, 0).toProgressStyle(),
            defaultProgressStrokeColor = ta.getColor(
                R.styleable.ShapeLinearLayout_defaultProgressStrokeColor,
                Color.TRANSPARENT
            ),
            pressedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeLinearLayout_pressedProgressStrokeColor,
                Color.TRANSPARENT
            ),
            selectedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeLinearLayout_selectedProgressStrokeColor,
                Color.TRANSPARENT
            ),
            focusedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeLinearLayout_focusedProgressStrokeColor,
                Color.TRANSPARENT
            ),
            checkedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeLinearLayout_checkedProgressStrokeColor,
                Color.TRANSPARENT
            ),
            touchedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeLinearLayout_touchedProgressStrokeColor,
                Color.TRANSPARENT
            ),

            progressStrokeWidth = ta.getDimension(R.styleable.ShapeLinearLayout_progressStrokeWidth, 0f),
            defaultProgressReachColor = ta.getColor(
                R.styleable.ShapeLinearLayout_defaultProgressReachColor,
                Color.TRANSPARENT
            ),
            pressedProgressReachColor = ta.getColor(
                R.styleable.ShapeLinearLayout_pressedProgressReachColor,
                Color.TRANSPARENT
            ),
            selectedProgressReachColor = ta.getColor(
                R.styleable.ShapeLinearLayout_selectedProgressReachColor,
                Color.TRANSPARENT
            ),
            focusedProgressReachColor = ta.getColor(
                R.styleable.ShapeLinearLayout_focusedProgressReachColor,
                Color.TRANSPARENT
            ),
            checkedProgressReachColor = ta.getColor(
                R.styleable.ShapeLinearLayout_checkedProgressReachColor,
                Color.TRANSPARENT
            ),
            touchedProgressReachColor = ta.getColor(
                R.styleable.ShapeLinearLayout_touchedProgressReachColor,
                Color.TRANSPARENT
            ),
            defaultProgressUnReachColor = ta.getColor(
                R.styleable.ShapeLinearLayout_defaultProgressUnReachColor,
                Color.TRANSPARENT
            ),
            pressedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeLinearLayout_pressedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            selectedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeLinearLayout_selectedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            focusedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeLinearLayout_focusedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            checkedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeLinearLayout_checkedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            touchedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeLinearLayout_touchedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            progressSolidWidth = ta.getDimension(R.styleable.ShapeLinearLayout_progressSolidWidth, 0f),
            progress = ta.getFloat(R.styleable.ShapeLinearLayout_progress, 0f),
            progressMax = ta.getFloat(R.styleable.ShapeLinearLayout_progressMax, 0f),
            enableDragProgress = ta.getBoolean(R.styleable.ShapeLinearLayout_enableDragProgress, false),
        )
        ta.recycle()
        shapeLayer = ShapeLayer(this, attr)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (shapeLayer.attr.validProgress() && shapeLayer.attr.enableDragProgress) {
            shapeLayer.onTouchEvent(event)
            super.onTouchEvent(event)
            true
        } else {
            super.onTouchEvent(event)
        }
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        shapeLayer.sizeChange(w, h)
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas) {
        shapeLayer.draw(canvas)
        super.onDraw(canvas)
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        shapeLayer.attr.refreshStateColor(State.SELECTED, selected)
        invalidate()
    }

    override fun setPressed(pressed: Boolean) {
        super.setPressed(pressed)
        shapeLayer.attr.refreshStateColor(State.PRESSED, pressed)
        invalidate()
    }

}