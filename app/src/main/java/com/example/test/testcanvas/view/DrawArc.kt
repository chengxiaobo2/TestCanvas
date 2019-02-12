package com.example.test.testcanvas.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 *  画扇形
 *
 * @author chengxiaobo
 * @version 1.0
 * @title
 * @description
 * @company 北京奔流网络信息技术有线公司
 * @created 2019/2/12
 * @changeRecord [修改记录] <br/>
 */
class DrawArc : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var centerX = 0.0f
    var centerY = 0.0f
    var rect = RectF()
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        centerX = width / 2.0f
        centerY = height / 2.0f
        val radius = width / 3.0f
        rect.left = centerX - radius
        rect.right = centerX + radius
        rect.top = centerY - radius
        rect.bottom = centerY + radius

        paint.color = Color.parseColor("#ff00ff")
        paint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawArc(rect, 0.0f, 120.0f, true, paint)
    }
}