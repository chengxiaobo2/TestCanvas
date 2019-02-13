package com.example.test.testcanvas.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * DrawPath demo
 *
 * @author chengxiaobo
 * @version 1.0
 * @title
 * @description
 * @company 北京奔流网络信息技术有线公司
 * @created 2019/2/13
 * @changeRecord <br/>
 */
class DrawPath : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var centerX = 0.0f
    private var centerY = 0.0f
    private var centerX2 = 0.0f
    private var centerY2 = 0.0f
    private var radius = 0.0f
    private val path = Path()
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        centerX = width / 3.0f
        centerY = width / 2.0f
        centerX2 = width / 3.0f * 2
        centerY2 = width / 2.0f
        radius = width / 4.0f

        paint.style = Paint.Style.FILL

        path.addCircle(centerX, centerY, radius, Path.Direction.CW)
        path.addCircle(centerX2, centerY2, radius, Path.Direction.CW)
        path.fillType = Path.FillType.EVEN_ODD
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

}