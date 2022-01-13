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
class ShapeRelativeLayout(cxt: Context, attrs: AttributeSet?, def: Int) :
    RelativeLayout(cxt, attrs, def) {
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val rect: ShapeLayer

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.ShapeRelativeLayout)
        val attr = Attrs(
            defaultBackgroundColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_defaultBackgroundColor,
                Color.TRANSPARENT
            ),
            borderWidth = ta.getDimension(R.styleable.ShapeRelativeLayout_borderWidth, 0f),
            defaultBorderColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_defaultBorderColor,
                Color.TRANSPARENT
            ),
            defaultShadowColor = ta.getColor(
                R.styleable.ShapeRelativeLayout_defaultShadowColor,
                Color.TRANSPARENT
            ),
            shadow = ta.getDimension(R.styleable.ShapeRelativeLayout_shadow, 1f),
            shadowRadius = ta.getFloat(R.styleable.ShapeRelativeLayout_shadowRadius, 0.5f),
            leftShadow = ta.getDimension(R.styleable.ShapeRelativeLayout_lShadow, -1f),
            topShadow = ta.getDimension(R.styleable.ShapeRelativeLayout_tShadow, -1f),
            rightShadow = ta.getDimension(R.styleable.ShapeRelativeLayout_rShadow, -1f),
            bottomShadow = ta.getDimension(R.styleable.ShapeRelativeLayout_bShadow, -1f),
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
            defaultProgressStrokeColor = ta.getColor(R.styleable.ShapeRelativeLayout_defaultProgressStrokeColor, Color.TRANSPARENT),
            progressStrokeWidth= ta.getDimension(R.styleable.ShapeRelativeLayout_progressStrokeWidth,0f),
            defaultProgressReachColor = ta.getColor(R.styleable.ShapeRelativeLayout_defaultProgressReachColor, Color.TRANSPARENT),
            defaultProgressUnReachColor = ta.getColor(R.styleable.ShapeRelativeLayout_defaultProgressUnReachColor, Color.TRANSPARENT),
            progressSolidWidth = ta.getDimension(R.styleable.ShapeRelativeLayout_progressSolidWidth, 0f),
            progress = ta.getFloat(R.styleable.ShapeRelativeLayout_progress , 0f),
            progressMax = ta.getFloat(R.styleable.ShapeRelativeLayout_progressMax, 0f),
            enableDragProgress = ta.getBoolean(R.styleable.ShapeRelativeLayout_enableDragProgress, false),
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