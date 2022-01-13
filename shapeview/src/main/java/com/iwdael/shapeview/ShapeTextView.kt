package com.iwdael.shapeview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView

/**
 * author : Iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * version: 1.0
 */
class ShapeTextView(cxt: Context, attrs: AttributeSet?, def: Int) : AppCompatTextView(cxt, attrs, def) {
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
            borderWidth = ta.getDimension(R.styleable.ShapeTextView_borderWidth, 0f),
            borderColor = ta.getColor(
                R.styleable.ShapeTextView_borderColor,
                Color.TRANSPARENT
            ),
            shadowColor = ta.getColor(
                R.styleable.ShapeTextView_shadowColor,
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
            progressStrokeColor = ta.getColor(R.styleable.ShapeTextView_progressStrokeColor, Color.TRANSPARENT),
            progressStrokeWidth= ta.getDimension(R.styleable.ShapeTextView_progressStrokeWidth,0f),
            progressReachColor = ta.getColor(R.styleable.ShapeTextView_progressReachColor, Color.TRANSPARENT),
            progressUnReachColor = ta.getColor(R.styleable.ShapeTextView_progressUnReachColor, Color.TRANSPARENT),
            progressSolidWidth = ta.getDimension(R.styleable.ShapeTextView_progressSolidWidth, 0f),
            progress = ta.getFloat(R.styleable.ShapeTextView_progress , 0f),
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
}