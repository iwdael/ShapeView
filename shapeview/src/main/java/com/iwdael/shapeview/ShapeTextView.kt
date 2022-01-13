package com.iwdael.shapeview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.TextView

/**
 * author : Iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * version: 1.0
 */
class ShapeTextView(cxt: Context, attrs: AttributeSet?, def: Int) : androidx.appcompat.widget.AppCompatTextView(cxt, attrs, def) {
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val rect: ShapeLayer

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.ShapeTextView)
        val attr = Attrs(
            defaultBackgroundColor = ta.getColor(
                R.styleable.ShapeTextView_defaultBackgroundColor,
                Color.TRANSPARENT
            ),
            pressedBackgroundColor = ta.getColor(
                R.styleable.ShapeTextView_pressedBackgroundColor,
                Color.TRANSPARENT
            ),
            selectedBackgroundColor = ta.getColor(
                R.styleable.ShapeTextView_selectedBackgroundColor,
                Color.TRANSPARENT
            ),
            focusedBackgroundColor = ta.getColor(
                R.styleable.ShapeTextView_focusedBackgroundColor,
                Color.TRANSPARENT
            ),
            checkedBackgroundColor = ta.getColor(
                R.styleable.ShapeTextView_checkedBackgroundColor,
                Color.TRANSPARENT
            ),
            touchedBackgroundColor = ta.getColor(
                R.styleable.ShapeTextView_touchedBackgroundColor,
                Color.TRANSPARENT
            ),
            borderWidth = ta.getDimension(R.styleable.ShapeTextView_borderWidth, 0f),
            defaultBorderColor = ta.getColor(
                R.styleable.ShapeTextView_defaultBorderColor,
                Color.TRANSPARENT
            ),
            pressedBorderColor = ta.getColor(
                R.styleable.ShapeTextView_pressedBorderColor,
                Color.TRANSPARENT
            ),
            selectedBorderColor = ta.getColor(
                R.styleable.ShapeTextView_selectedBorderColor,
                Color.TRANSPARENT
            ),
            focusedBorderColor = ta.getColor(
                R.styleable.ShapeTextView_focusedBorderColor,
                Color.TRANSPARENT
            ),
            checkedBorderColor = ta.getColor(
                R.styleable.ShapeTextView_checkedBorderColor,
                Color.TRANSPARENT
            ),
            touchedBorderColor = ta.getColor(
                R.styleable.ShapeTextView_touchedBorderColor,
                Color.TRANSPARENT
            ),

            defaultShadowColor = ta.getColor(
                R.styleable.ShapeTextView_defaultShadowColor,
                Color.TRANSPARENT
            ),
            pressedShadowColor = ta.getColor(
                R.styleable.ShapeTextView_pressedShadowColor,
                Color.TRANSPARENT
            ),
            selectedShadowColor = ta.getColor(
                R.styleable.ShapeTextView_selectedShadowColor,
                Color.TRANSPARENT
            ),
            focusedShadowColor = ta.getColor(
                R.styleable.ShapeTextView_focusedShadowColor,
                Color.TRANSPARENT
            ),
            checkedShadowColor = ta.getColor(
                R.styleable.ShapeTextView_checkedShadowColor,
                Color.TRANSPARENT
            ),
            touchedShadowColor = ta.getColor(
                R.styleable.ShapeTextView_touchedShadowColor,
                Color.TRANSPARENT
            ),

            shadow = ta.getDimension(R.styleable.ShapeTextView_shadow, 1f),
            shadowRadius = ta.getFloat(R.styleable.ShapeTextView_shadowRadius, 0.5f),
            leftShadow = ta.getDimension(R.styleable.ShapeTextView_lShadow, -1f),
            topShadow = ta.getDimension(R.styleable.ShapeTextView_tShadow, -1f),
            rightShadow = ta.getDimension(R.styleable.ShapeTextView_rShadow, -1f),
            bottomShadow = ta.getDimension(R.styleable.ShapeTextView_bShadow, -1f),
            shadowDx = ta.getDimension(R.styleable.ShapeTextView_shadowDx, 0f),
            shadowDy = ta.getDimension(R.styleable.ShapeTextView_shadowDy, 0f),
            radius = try {
                ta.getDimension(R.styleable.ShapeTextView_radius, 0f)
            } catch (e: Exception) {
                0f
            },
            shapeStyle = try {
                ta.getInt(R.styleable.ShapeTextView_radius, -1)
            } catch (e: Exception) {
                0
            }.toShapeStyle(),
            ltRadius = ta.getDimension(R.styleable.ShapeTextView_ltRadius, -1f),
            lbRadius = ta.getDimension(R.styleable.ShapeTextView_lbRadius, -1f),
            rtRadius = ta.getDimension(R.styleable.ShapeTextView_rtRadius, -1f),
            rbRadius = ta.getDimension(R.styleable.ShapeTextView_rbRadius, -1f),
            progressStyle = ta.getInt(R.styleable.ShapeTextView_progressStyle, 0).toProgressStyle(),
            defaultProgressStrokeColor = ta.getColor(
                R.styleable.ShapeTextView_defaultProgressStrokeColor,
                Color.TRANSPARENT
            ),
            pressedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeTextView_pressedProgressStrokeColor,
                Color.TRANSPARENT
            ),
            selectedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeTextView_selectedProgressStrokeColor,
                Color.TRANSPARENT
            ),
            focusedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeTextView_focusedProgressStrokeColor,
                Color.TRANSPARENT
            ),
            checkedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeTextView_checkedProgressStrokeColor,
                Color.TRANSPARENT
            ),
            touchedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeTextView_touchedProgressStrokeColor,
                Color.TRANSPARENT
            ),

            progressStrokeWidth = ta.getDimension(R.styleable.ShapeTextView_progressStrokeWidth, 0f),
            defaultProgressReachColor = ta.getColor(
                R.styleable.ShapeTextView_defaultProgressReachColor,
                Color.TRANSPARENT
            ),
            pressedProgressReachColor = ta.getColor(
                R.styleable.ShapeTextView_pressedProgressReachColor,
                Color.TRANSPARENT
            ),
            selectedProgressReachColor = ta.getColor(
                R.styleable.ShapeTextView_selectedProgressReachColor,
                Color.TRANSPARENT
            ),
            focusedProgressReachColor = ta.getColor(
                R.styleable.ShapeTextView_focusedProgressReachColor,
                Color.TRANSPARENT
            ),
            checkedProgressReachColor = ta.getColor(
                R.styleable.ShapeTextView_checkedProgressReachColor,
                Color.TRANSPARENT
            ),
            touchedProgressReachColor = ta.getColor(
                R.styleable.ShapeTextView_touchedProgressReachColor,
                Color.TRANSPARENT
            ),
            defaultProgressUnReachColor = ta.getColor(
                R.styleable.ShapeTextView_defaultProgressUnReachColor,
                Color.TRANSPARENT
            ),
            pressedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeTextView_pressedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            selectedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeTextView_selectedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            focusedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeTextView_focusedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            checkedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeTextView_checkedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            touchedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeTextView_touchedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            progressSolidWidth = ta.getDimension(R.styleable.ShapeTextView_progressSolidWidth, 0f),
            progress = ta.getFloat(R.styleable.ShapeTextView_progress, 0f),
            progressMax = ta.getFloat(R.styleable.ShapeTextView_progressMax, 0f),
            enableDragProgress = ta.getBoolean(R.styleable.ShapeTextView_enableDragProgress, false),
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