package com.example.test.testcanvas.drawText

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.testcanvas.dp2px

/**
 * 文字居中
 * @author chengxiaobo
 * @time 2019/3/14 22:57
 */
class DrawTextCenter : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var centerX = 0.0f
    var centerY = 0.0f
    var radius = 0.0f
    var rect = RectF()
    var centerY1 = 0.0f
    var centerY2 = 0.0f
    val s = "cheng"

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = width / 2.0f
        centerY = height / 2.0f
        radius = width / 3.0f
        rect.left = centerX - radius
        rect.top = centerY - radius
        rect.right = centerX + radius
        rect.bottom = centerY + radius
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = dp2px(8.0f)
        paint.color = Color.RED
        paint.strokeCap = Paint.Cap.ROUND
        paint.textSize = dp2px(35.0f)

        centerY1 = centerY - (paint.fontMetrics.ascent + paint.fontMetrics.descent) / 2.0f
        val rect2 = Rect()
        paint.getTextBounds(s, 0, s.length, rect2)
        centerY2 = centerY - (rect2.bottom + rect2.top) / 2.0f
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawArc(rect, 200.0f, 360f, false, paint)
        paint.textAlign = Paint.Align.CENTER //文字最中间的地方在X处
        paint.color = Color.parseColor("#FFC125")
        //TODO drawText时，记得将Style设置成Fill 否则结果很意外哦
        paint.style = Paint.Style.FILL
        canvas.drawText(s, centerX, centerY, paint)
//        canvas.drawText(s, centerX, centerY1, paint)
//        paint.color = Color.RED
//        canvas.drawText(s, centerX, centerY2, paint)

        paint.color = Color.GRAY
        paint.strokeWidth = dp2px(1.0f)
        canvas.drawLine(0.0f, centerY, width.toFloat(), centerY, paint)
    }
}