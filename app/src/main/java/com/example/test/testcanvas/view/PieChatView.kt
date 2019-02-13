package com.example.test.testcanvas.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 * 饼状图
 * 扔物线的代码 -- https://github.com/rengwuxian/HenCoderPlus/blob/36205dc58d69c18cecdbffb11e9e447b685c9c1e/06-drawing/src/main/java/com/hencoder/plus/view/PieChart.java
 *
 * @author chengxiaobo
 * @version 1.0
 * @title
 * @description
 * @company 北京奔流网络信息技术有线公司
 * @created 2019/2/13
 * @changeRecord <br/>
 */
class PieChatView : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var centerX = 0.0f
    private var centerY = 0.0f
    private var offsetDistance = 0.0f
    private var offsetX = 0.0
    private var offsetY = 0.0
    private val rect = RectF()

    private val startAngle = 20.0f //第一个扇形的起始度数
    private val colorArr = arrayOf("#48D1CC", "#FF1493", "#FFD700", "#76EE00")
    private val angleArr = arrayOf(60.0f, 100.0f, 130.0f, 70.0f) //第一个扇形60度 ...
    private val offsetPosition = 2


    override
    fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        centerX = width / 2.0f
        centerY = height / 2.0f
        offsetDistance = width / 20.0f
        val radius = width / 3.0f
        rect.left = centerX - radius
        rect.right = centerX + radius
        rect.top = centerY - radius
        rect.bottom = centerY + radius

        paint.style = Paint.Style.FILL

        // TODO 注意1：计算偏移X和偏移Y（最好不要在onDraw方法中计算，浪费cpu的资源）
        var currentAngle = startAngle
        for (i in 0 until angleArr.size) {
            if (i == offsetPosition) {
                currentAngle += angleArr[i] / 2.0f
                //TODO 注意2：Math.cos(弧度)
                offsetX = Math.cos(Math.toRadians(currentAngle.toDouble())) * offsetDistance
                offsetY = Math.sin(Math.toRadians(currentAngle.toDouble())) * offsetDistance
                break
            }
            currentAngle += angleArr[i]
        }

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var currentAngle = startAngle
        for (i in 0 until colorArr.size) {
            paint.color = Color.parseColor(colorArr[i])
            //canvas进行偏移以后，再绘制
            if (i == offsetPosition) {
                canvas.save()
                canvas.translate(offsetX.toFloat(), offsetY.toFloat())
                canvas.drawArc(rect, currentAngle, angleArr[i], true, paint)
                canvas.restore()
            } else {
                canvas.drawArc(rect, currentAngle, angleArr[i], true, paint)
            }
            currentAngle += angleArr[i]
        }

    }
}