package com.example.test.testcanvas.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.testcanvas.R
import com.example.test.testcanvas.dp2px
import com.example.test.testcanvas.getBitmapFromResource

/**
 * TestShaderActivity
 *
 * @author chengxiaobo
 * @version 1.0
 * @title
 * @description
 * @company 北京奔流网络信息技术有线公司
 * @created 2019/2/13
 * @changeRecord <br/>
 */
class TestShader : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var bitmap: Bitmap? = null
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var centerX = 0.0f
    private var centerY = 0.0f
    private var radius = 0.0f
    private var bitmapShader: BitmapShader? = null
    private var linearGradient: LinearGradient? = null

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = getBitmapFromResource(R.drawable.pic_2, width / 4, context)
        radius = width / 2.0f
        centerX = width / 2.0f
        centerY = height / 2.0f

        bitmap?.let {
            //            bitmapShader = BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
            bitmapShader = BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.CLAMP)
        }
        linearGradient =
                LinearGradient(0.0f, 0.0f, width / 3.0f, width / 3.0f, Color.RED, Color.GREEN, Shader.TileMode.MIRROR)
        paint.textSize = dp2px(14.0f)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        paint.shader = bitmapShader
        paint.shader = linearGradient
        canvas.drawRect(0.0f, 0.0f, width.toFloat(), height.toFloat(), paint)
        paint.shader = null

        paint.style = Paint.Style.STROKE
        canvas.drawRect(0.0f, 0.0f, width / 3.0f, width / 3.0f, paint)
    }
}