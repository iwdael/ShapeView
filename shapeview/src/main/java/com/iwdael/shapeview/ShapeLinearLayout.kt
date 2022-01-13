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
    private val rect: ShapeLayer

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.ShapeLinearLayout)
        val attr = Attrs(
            defaultBackgroundColor = ta.getColor(
                R.styleable.ShapeLinearLayout_defaultBackgroundColor,
                Color.TRANSPARENT
            ),
            borderWidth = ta.getDimension(R.styleable.ShapeLinearLayout_borderWidth, 0f),
            defaultBorderColor = ta.getColor(
                R.styleable.ShapeLinearLayout_defaultBorderColor,
                Color.TRANSPARENT
            ),
            defaultShadowColor = ta.getColor(
                R.styleable.ShapeLinearLayout_defaultShadowColor,
                Color.TRANSPARENT
            ),
            shadow = ta.getDimension(R.styleable.ShapeLinearLayout_shadow, 1f),
            shadowRadius = ta.getFloat(R.styleable.ShapeLinearLayout_shadowRadius, 0.5f),
            leftShadow = ta.getDimension(R.styleable.ShapeLinearLayout_lShadow, -1f),
            topShadow = ta.getDimension(R.styleable.ShapeLinearLayout_tShadow, -1f),
            rightShadow = ta.getDimension(R.styleable.ShapeLinearLayout_rShadow, -1f),
            bottomShadow = ta.getDimension(R.styleable.ShapeLinearLayout_bShadow, -1f),
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
            defaultProgressStrokeColor = ta.getColor(R.styleable.ShapeLinearLayout_defaultProgressStrokeColor, Color.TRANSPARENT),
            progressStrokeWidth= ta.getDimension(R.styleable.ShapeLinearLayout_progressStrokeWidth,0f),
            defaultProgressReachColor = ta.getColor(R.styleable.ShapeLinearLayout_defaultProgressReachColor, Color.TRANSPARENT),
            defaultProgressUnReachColor = ta.getColor(R.styleable.ShapeLinearLayout_defaultProgressUnReachColor, Color.TRANSPARENT),
            progressSolidWidth = ta.getDimension(R.styleable.ShapeLinearLayout_progressSolidWidth, 0f),
            progress = ta.getFloat(R.styleable.ShapeLinearLayout_progress , 0f),
            progressMax = ta.getFloat(R.styleable.ShapeLinearLayout_progressMax, 0f),
            enableDragProgress = ta.getBoolean(R.styleable.ShapeLinearLayout_enableDragProgress, false),
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