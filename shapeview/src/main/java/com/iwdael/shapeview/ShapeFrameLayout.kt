package com.iwdael.shapeview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout

/**
 * author : Iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * version: 1.0
 */
class ShapeFrameLayout(cxt: Context, attrs: AttributeSet?, def: Int) :
    FrameLayout(cxt, attrs, def) {
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
            borderWidth = ta.getDimension(R.styleable.ShapeFrameLayout_borderWidth, 0f),
            borderColor = ta.getColor(
                R.styleable.ShapeFrameLayout_borderColor,
                Color.TRANSPARENT
            ),
            shadowColor = ta.getColor(
                R.styleable.ShapeFrameLayout_shadowColor,
                Color.TRANSPARENT
            ),
            shadow = ta.getDimension(R.styleable.ShapeFrameLayout_shadow, 1f),
            shadowRadius = ta.getFloat(R.styleable.ShapeFrameLayout_shadowRadius, 0.5f),
            leftShadow = ta.getDimension(R.styleable.ShapeFrameLayout_lShadow, -1f),
            topShadow = ta.getDimension(R.styleable.ShapeFrameLayout_tShadow, -1f),
            rightShadow = ta.getDimension(R.styleable.ShapeFrameLayout_rShadow, -1f),
            bottomShadow = ta.getDimension(R.styleable.ShapeFrameLayout_bShadow, -1f),
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
            progressStyle = ta.getInt(R.styleable.ShapeFrameLayout_progressStyle, 0)
                .toProgressStyle(),
            progressStrokeColor = ta.getColor(
                R.styleable.ShapeFrameLayout_progressStrokeColor,
                Color.TRANSPARENT
            ),
            progressStrokeWidth = ta.getDimension(
                R.styleable.ShapeFrameLayout_progressStrokeWidth,
                0f
            ),
            progressReachColor = ta.getColor(
                R.styleable.ShapeFrameLayout_progressReachColor,
                Color.TRANSPARENT
            ),
            progressUnReachColor = ta.getColor(
                R.styleable.ShapeFrameLayout_progressUnReachColor,
                Color.TRANSPARENT
            ),
            progressSolidWidth = ta.getDimension(
                R.styleable.ShapeFrameLayout_progressSolidWidth,
                0f
            ),
            progress = ta.getFloat(R.styleable.ShapeFrameLayout_progress, 0f),
            progressMax = ta.getFloat(R.styleable.ShapeFrameLayout_progressMax, 0f),
            enableDragProgress = ta.getBoolean(
                R.styleable.ShapeFrameLayout_enableDragProgress,
                false
            ),
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
}