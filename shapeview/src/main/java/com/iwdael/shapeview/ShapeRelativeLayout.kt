package com.iwdael.shapeview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.RelativeLayout

/**
 * author : Iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * version: 1.0
 */
class ShapeRelativeLayout(cxt: Context, attrs: AttributeSet?, def: Int) : RelativeLayout(cxt, attrs, def) {
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val shapeLayer: ShapeLayer

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.ShapeRelativeLayout)
        val attr = Attrs(
            defaultBackgroundColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_defaultBackgroundColor,
                Color.TRANSPARENT
            ),
            pressedBackgroundColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_pressedBackgroundColor,
                Color.TRANSPARENT
            ),
            selectedBackgroundColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_selectedBackgroundColor,
                Color.TRANSPARENT
            ),
            focusedBackgroundColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_focusedBackgroundColor,
                Color.TRANSPARENT
            ),
            checkedBackgroundColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_checkedBackgroundColor,
                Color.TRANSPARENT
            ),
            touchedBackgroundColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_touchedBackgroundColor,
                Color.TRANSPARENT
            ),
            borderWidth = ta.getDimension(R.styleable.ShapeRelativeLayout_borderWidth, 0f),
            defaultBorderColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_defaultBorderColor,
                Color.TRANSPARENT
            ),
            pressedBorderColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_pressedBorderColor,
                Color.TRANSPARENT
            ),
            selectedBorderColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_selectedBorderColor,
                Color.TRANSPARENT
            ),
            focusedBorderColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_focusedBorderColor,
                Color.TRANSPARENT
            ),
            checkedBorderColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_checkedBorderColor,
                Color.TRANSPARENT
            ),
            touchedBorderColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_touchedBorderColor,
                Color.TRANSPARENT
            ),

            defaultShadowColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_defaultShadowColor,
                Color.TRANSPARENT
            ),
            pressedShadowColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_pressedShadowColor,
                Color.TRANSPARENT
            ),
            selectedShadowColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_selectedShadowColor,
                Color.TRANSPARENT
            ),
            focusedShadowColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_focusedShadowColor,
                Color.TRANSPARENT
            ),
            checkedShadowColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_checkedShadowColor,
                Color.TRANSPARENT
            ),
            touchedShadowColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_touchedShadowColor,
                Color.TRANSPARENT
            ),

            shadowPadding = ta.getDimension(R.styleable.ShapeRelativeLayout_shadowPadding, 1f),
            shadowRadius = ta.getFloat(R.styleable.ShapeRelativeLayout_shadowRadius, 0.5f),
            leftShadowPadding = ta.getDimension(R.styleable.ShapeRelativeLayout_leftShadowPadding, -1f),
            topShadowPadding = ta.getDimension(R.styleable.ShapeRelativeLayout_topShadowPadding, -1f),
            rightShadowPadding = ta.getDimension(R.styleable.ShapeRelativeLayout_rightShadowPadding, -1f),
            bottomShadowPadding = ta.getDimension(R.styleable.ShapeRelativeLayout_bottomShadowPadding, -1f),
            shadowDx = ta.getDimension(R.styleable.ShapeRelativeLayout_shadowDx, 0f),
            shadowDy = ta.getDimension(R.styleable.ShapeRelativeLayout_shadowDy, 0f),
            radius = try {
                ta.getDimension(R.styleable.ShapeRelativeLayout_radius, 0f)
            } catch (e: Exception) {
                0f
            },
            shapeStyle = try {
                ta.getInt(R.styleable.ShapeRelativeLayout_radius, -1)
            } catch (e: Exception) {
                0
            }.toShapeStyle(),
            ltRadius = ta.getDimension(R.styleable.ShapeRelativeLayout_ltRadius, -1f),
            lbRadius = ta.getDimension(R.styleable.ShapeRelativeLayout_lbRadius, -1f),
            rtRadius = ta.getDimension(R.styleable.ShapeRelativeLayout_rtRadius, -1f),
            rbRadius = ta.getDimension(R.styleable.ShapeRelativeLayout_rbRadius, -1f),
            progressStyle = ta.getInt(R.styleable.ShapeRelativeLayout_progressStyle, 0).toProgressStyle(),
            defaultProgressStrokeColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_defaultProgressStrokeColor,
                Color.TRANSPARENT
            ),
            pressedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_pressedProgressStrokeColor,
                Color.TRANSPARENT
            ),
            selectedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_selectedProgressStrokeColor,
                Color.TRANSPARENT
            ),
            focusedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_focusedProgressStrokeColor,
                Color.TRANSPARENT
            ),
            checkedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_checkedProgressStrokeColor,
                Color.TRANSPARENT
            ),
            touchedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_touchedProgressStrokeColor,
                Color.TRANSPARENT
            ),

            progressStrokeWidth = ta.getDimension(R.styleable.ShapeRelativeLayout_progressStrokeWidth, 0f),
            defaultProgressReachColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_defaultProgressReachColor,
                Color.TRANSPARENT
            ),
            pressedProgressReachColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_pressedProgressReachColor,
                Color.TRANSPARENT
            ),
            selectedProgressReachColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_selectedProgressReachColor,
                Color.TRANSPARENT
            ),
            focusedProgressReachColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_focusedProgressReachColor,
                Color.TRANSPARENT
            ),
            checkedProgressReachColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_checkedProgressReachColor,
                Color.TRANSPARENT
            ),
            touchedProgressReachColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_touchedProgressReachColor,
                Color.TRANSPARENT
            ),
            defaultProgressUnReachColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_defaultProgressUnReachColor,
                Color.TRANSPARENT
            ),
            pressedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_pressedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            selectedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_selectedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            focusedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_focusedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            checkedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_checkedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            touchedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_touchedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            progressSolidWidth = ta.getDimension(R.styleable.ShapeRelativeLayout_progressSolidWidth, 0f),
            progress = ta.getFloat(R.styleable.ShapeRelativeLayout_progress, 0f),
            progressMax = ta.getFloat(R.styleable.ShapeRelativeLayout_progressMax, 0f),
            enableDragProgress = ta.getBoolean(R.styleable.ShapeRelativeLayout_enableDragProgress, false),
            progressSheetRadius = ta.getDimension(
                R.styleable.ShapeRelativeLayout_progressSheetRadius,
                0f
            ),
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