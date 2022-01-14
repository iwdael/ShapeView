package com.iwdael.shapeview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * author : Iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * version: 1.0
 */
class ShapeView(cxt: Context, attrs: AttributeSet?, def: Int) : View(cxt, attrs, def) {
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val shapeLayer: ShapeLayer

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.ShapeView)
        val attr = Attrs(
            defaultBackgroundColor = ta.getColor(
                R.styleable.ShapeView_defaultBackgroundColor,
                Color.TRANSPARENT
            ),
            pressedBackgroundColor = ta.getColor(
                R.styleable.ShapeView_pressedBackgroundColor,
                Color.TRANSPARENT
            ),
            selectedBackgroundColor = ta.getColor(
                R.styleable.ShapeView_selectedBackgroundColor,
                Color.TRANSPARENT
            ),
            focusedBackgroundColor = ta.getColor(
                R.styleable.ShapeView_focusedBackgroundColor,
                Color.TRANSPARENT
            ),
            checkedBackgroundColor = ta.getColor(
                R.styleable.ShapeView_checkedBackgroundColor,
                Color.TRANSPARENT
            ),
            touchedBackgroundColor = ta.getColor(
                R.styleable.ShapeView_touchedBackgroundColor,
                Color.TRANSPARENT
            ),
            borderWidth = ta.getDimension(R.styleable.ShapeView_borderWidth, 0f),
            defaultBorderColor = ta.getColor(
                R.styleable.ShapeView_defaultBorderColor,
                Color.TRANSPARENT
            ),
            pressedBorderColor = ta.getColor(
                R.styleable.ShapeView_pressedBorderColor,
                Color.TRANSPARENT
            ),
            selectedBorderColor = ta.getColor(
                R.styleable.ShapeView_selectedBorderColor,
                Color.TRANSPARENT
            ),
            focusedBorderColor = ta.getColor(
                R.styleable.ShapeView_focusedBorderColor,
                Color.TRANSPARENT
            ),
            checkedBorderColor = ta.getColor(
                R.styleable.ShapeView_checkedBorderColor,
                Color.TRANSPARENT
            ),
            touchedBorderColor = ta.getColor(
                R.styleable.ShapeView_touchedBorderColor,
                Color.TRANSPARENT
            ),

            defaultShadowColor = ta.getColor(
                R.styleable.ShapeView_defaultShadowColor,
                Color.TRANSPARENT
            ),
            pressedShadowColor = ta.getColor(
                R.styleable.ShapeView_pressedShadowColor,
                Color.TRANSPARENT
            ),
            selectedShadowColor = ta.getColor(
                R.styleable.ShapeView_selectedShadowColor,
                Color.TRANSPARENT
            ),
            focusedShadowColor = ta.getColor(
                R.styleable.ShapeView_focusedShadowColor,
                Color.TRANSPARENT
            ),
            checkedShadowColor = ta.getColor(
                R.styleable.ShapeView_checkedShadowColor,
                Color.TRANSPARENT
            ),
            touchedShadowColor = ta.getColor(
                R.styleable.ShapeView_touchedShadowColor,
                Color.TRANSPARENT
            ),

            shadowPadding = ta.getDimension(R.styleable.ShapeView_shadowPadding, 1f),
            shadowRadius = ta.getFloat(R.styleable.ShapeView_shadowRadius, 0.5f),
            leftShadowPadding = ta.getDimension(R.styleable.ShapeView_leftShadowPadding, -1f),
            topShadowPadding = ta.getDimension(R.styleable.ShapeView_topShadowPadding, -1f),
            rightShadowPadding = ta.getDimension(R.styleable.ShapeView_rightShadowPadding, -1f),
            bottomShadowPadding = ta.getDimension(R.styleable.ShapeView_bottomShadowPadding, -1f),
            shadowDx = ta.getDimension(R.styleable.ShapeView_shadowDx, 0f),
            shadowDy = ta.getDimension(R.styleable.ShapeView_shadowDy, 0f),
            radius = try {
                ta.getDimension(R.styleable.ShapeView_radius, 0f)
            } catch (e: Exception) {
                0f
            },
            shapeStyle = try {
                ta.getInt(R.styleable.ShapeView_radius, -1)
            } catch (e: Exception) {
                0
            }.toShapeStyle(),
            ltRadius = ta.getDimension(R.styleable.ShapeView_ltRadius, -1f),
            lbRadius = ta.getDimension(R.styleable.ShapeView_lbRadius, -1f),
            rtRadius = ta.getDimension(R.styleable.ShapeView_rtRadius, -1f),
            rbRadius = ta.getDimension(R.styleable.ShapeView_rbRadius, -1f),
            progressStyle = ta.getInt(R.styleable.ShapeView_progressStyle, 0).toProgressStyle(),
            defaultProgressStrokeColor = ta.getColor(
                R.styleable.ShapeView_defaultProgressStrokeColor,
                Color.TRANSPARENT
            ),
            pressedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeView_pressedProgressStrokeColor,
                Color.TRANSPARENT
            ),
            selectedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeView_selectedProgressStrokeColor,
                Color.TRANSPARENT
            ),
            focusedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeView_focusedProgressStrokeColor,
                Color.TRANSPARENT
            ),
            checkedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeView_checkedProgressStrokeColor,
                Color.TRANSPARENT
            ),
            touchedProgressStrokeColor = ta.getColor(
                R.styleable.ShapeView_touchedProgressStrokeColor,
                Color.TRANSPARENT
            ),

            progressStrokeWidth = ta.getDimension(R.styleable.ShapeView_progressStrokeWidth, 0f),
            defaultProgressReachColor = ta.getColor(
                R.styleable.ShapeView_defaultProgressReachColor,
                Color.TRANSPARENT
            ),
            pressedProgressReachColor = ta.getColor(
                R.styleable.ShapeView_pressedProgressReachColor,
                Color.TRANSPARENT
            ),
            selectedProgressReachColor = ta.getColor(
                R.styleable.ShapeView_selectedProgressReachColor,
                Color.TRANSPARENT
            ),
            focusedProgressReachColor = ta.getColor(
                R.styleable.ShapeView_focusedProgressReachColor,
                Color.TRANSPARENT
            ),
            checkedProgressReachColor = ta.getColor(
                R.styleable.ShapeView_checkedProgressReachColor,
                Color.TRANSPARENT
            ),
            touchedProgressReachColor = ta.getColor(
                R.styleable.ShapeView_touchedProgressReachColor,
                Color.TRANSPARENT
            ),
            defaultProgressUnReachColor = ta.getColor(
                R.styleable.ShapeView_defaultProgressUnReachColor,
                Color.TRANSPARENT
            ),
            pressedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeView_pressedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            selectedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeView_selectedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            focusedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeView_focusedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            checkedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeView_checkedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            touchedProgressUnReachColor = ta.getColor(
                R.styleable.ShapeView_touchedProgressUnReachColor,
                Color.TRANSPARENT
            ),
            progressSolidWidth = ta.getDimension(R.styleable.ShapeView_progressSolidWidth, 0f),
            progress = ta.getFloat(R.styleable.ShapeView_progress, 0f),
            progressMax = ta.getFloat(R.styleable.ShapeView_progressMax, 0f),
            enableDragProgress = ta.getBoolean(R.styleable.ShapeView_enableDragProgress, false),
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

    fun setProgress(progress: Float) {
        shapeLayer.setProgress(progress)
    }

    fun setProgress(progress: Float, duration: Long) {
        shapeLayer.setProgress(progress, duration)
    }
}