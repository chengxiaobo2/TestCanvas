package com.example.test.testcanvas.drawText

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.example.test.testcanvas.dp2px

/**
 * TestStaticLayout
 * @author chengxiaobo
 * @time 2019/3/16 17:03
 */
class TestStaticLayout : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    val paint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    val s =
        "Android是一种基于Linux的自由及开放源代码的操作系统。主要使用于移动设备，如智能手机和平板电脑，由Google（谷歌）公司和开放手机联盟领导及开发。"
    val s2 =
        "hello word 1 ! \nhello word 2 ! \nhello word 3 ! \n"
    var staticLayout: StaticLayout? = null
    var staticLayout2: StaticLayout? = null


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        paint.textSize = dp2px(16.0f)
        staticLayout = StaticLayout(s, paint, width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true)
        staticLayout2 = StaticLayout(s2, paint, width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        staticLayout?.draw(canvas)


        staticLayout?.let {
            canvas.translate(0.0f, it.height.toFloat() + dp2px(10.0f))
        }


        staticLayout2?.draw(canvas)
    }
}