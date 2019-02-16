package com.example.test.testcanvas.circleBitmap

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.testcanvas.R
import com.example.test.testcanvas.dp2px
import com.example.test.testcanvas.getBitmapFromResource

/**
 * bitmapShader实现圆形图片
 *
 * @author chengxiaobo
 * @version 1.0
 * @title
 * @description
 * @company 北京奔流网络信息技术有线公司
 * @created 2019/2/15
 * @changeRecord [修改记录] <br/>
 */
class CircleBitmapShader : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var bitmap: Bitmap? = null
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var centerX = 0.0f
    private var centerY = 0.0f
    private var radius = 0.0f
    private var bitmapShader: BitmapShader? = null

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = getBitmapFromResource(R.drawable.pic_2, width, context)
        radius = width / 2.0f
        centerX = width / 2.0f
        centerY = height / 2.0f

        bitmap?.let {
//            bitmapShader = BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
            bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        }
        paint.textSize = dp2px(14.0f)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.shader = bitmapShader
        canvas.drawCircle(centerX, centerY, radius, paint)
        //用来演示tileMode
//        canvas.drawRect(0.0f, 0.0f, height.toFloat(), height.toFloat(), paint)
        // TODO 记得设置为空，再drawText,否则text的颜色为图片的颜色
        paint.shader = null
        paint.color = Color.BLACK
        canvas.drawText("bitmapShader", 0.0f, dp2px(14.0f), paint)
    }
}