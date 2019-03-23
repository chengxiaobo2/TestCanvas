package com.example.test.testcanvas.drawText

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.test.testcanvas.dp2px
import java.text.DecimalFormat
import java.util.*

/**
 *  Textline
 *
 * @author chengxiaobo
 * @time 2019/3/14 21:47
 */
class TextLine : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var leftX = 0.0f
    var leftY = 0.0f

    var descent = 0.0f
    var ascent = 0.0f
    var metricsTop = 0.0f
    var metricsBottom = 0.0f

    var descents = "0.0f"
    var ascents = "0.0f"
    var metricsTops = "0.0f"
    var metricsBottoms = "0.0f"

    var rect = Rect()
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        paint.textSize = dp2px(60.0f)
        leftX = dp2px(10.0f)
        leftY = dp2px(70.0f)
        val metrics = paint.fontMetrics
        descents = DecimalFormat(".00").format(metrics.descent)
        ascents = DecimalFormat(".00").format(metrics.ascent)
        metricsTops = DecimalFormat(".00").format(metrics.top)
        metricsBottoms = DecimalFormat(".00").format(metrics.bottom)

        descent = metrics.descent
        ascent = metrics.ascent
        metricsTop = metrics.top
        metricsBottom = metrics.bottom
        Log.e(
            "TextLine",
            "metricsTop: " + metricsTop + " ascent: " + ascent + " descent: " + descent + " metricsBottom:" + metricsBottom
        )
        paint.getTextBounds("cheng", 0, "cheng".length, rect)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.textSize = dp2px(60.0f)
        paint.strokeWidth = dp2px(1.0f)

        canvas.drawText("cheng", leftX, leftY, paint)
        paint.color = Color.GRAY
        canvas.drawLine(leftX, leftY, width.toFloat(), leftY, paint)

        //top
        paint.color = Color.BLUE
        canvas.save();
        canvas.translate(0.0f, metricsTop)
        canvas.drawLine(leftX, leftY, width.toFloat(), leftY, paint)
        canvas.restore()
        //ascent
        paint.color = Color.GREEN
        canvas.save();
        canvas.translate(0.0f, ascent)
        canvas.drawLine(leftX, leftY, width.toFloat(), leftY, paint)
        canvas.restore()
        //desent
        paint.color = Color.parseColor("#FFC125")
        canvas.save();
        paint.strokeWidth = 1.0f
        canvas.translate(0.0f, descent)
        canvas.drawLine(leftX, leftY, width.toFloat(), leftY, paint)
        canvas.restore()
        //bottom
        paint.color = Color.RED
        canvas.save();
        paint.strokeWidth = 1.0f
        canvas.translate(0.0f, metricsBottom)
        canvas.drawLine(leftX, leftY, width.toFloat(), leftY, paint)
        canvas.restore()

        paint.textSize = dp2px(16.0f)
        paint.strokeWidth = dp2px(10.0f)
        //top value
        canvas.translate(leftX * 2, height / 2.0f)
        paint.color = Color.BLUE
        canvas.drawPoint(0.0f, 0.0f, paint)
        canvas.drawText(metricsTops, paint.strokeWidth, paint.strokeWidth / 2.0f, paint)

        //ascent value
        canvas.save()
        canvas.translate(width / 2.0f, 0.0f)
        paint.color = Color.GREEN
        canvas.drawPoint(0.0f, 0.0f, paint)
        canvas.drawText(ascents, paint.strokeWidth, paint.strokeWidth / 2.0f, paint)
        canvas.restore()

        //baseline value
        canvas.save()
        canvas.translate(0.0f, paint.strokeWidth * 2)
        paint.color = Color.GRAY
        canvas.drawPoint(0.0f, 0.0f, paint)
        canvas.drawText("0.00", paint.strokeWidth, paint.strokeWidth / 2.0f, paint)
        canvas.restore()

        //desent value
        canvas.save()
        canvas.translate(width / 2.0f, paint.strokeWidth * 2)
        paint.color = Color.parseColor("#FFC125")
        canvas.drawPoint(0.0f, 0.0f, paint)
        canvas.drawText(descents, paint.strokeWidth, paint.strokeWidth / 2.0f, paint)
        canvas.restore()

        //bottom value
        canvas.save()
        canvas.translate(0.0f, paint.strokeWidth * 4)
        paint.color = Color.RED
        canvas.drawPoint(0.0f, 0.0f, paint)
        canvas.drawText(metricsBottoms, paint.strokeWidth, paint.strokeWidth / 2.0f, paint)
        canvas.restore()

        canvas.save()
        canvas.translate(-dp2px(10.0f), paint.strokeWidth * 7)
        paint.color = Color.BLACK
        canvas.drawText(
            "bound=" + "l:" + rect.left + " r:" + rect.right + " t:" + rect.top + " b:" + rect.bottom,
            0.0f,
            paint.strokeWidth / 2.0f,
            paint
        )
        canvas.restore()

    }
}