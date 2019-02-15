package com.example.test.testcanvas.circleBitmap

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.testcanvas.R
import com.example.test.testcanvas.getBitmapFromResource

/**
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
    private val rectDestination = Rect()
    private val rectSource = Rect()
    private var centerX = 0
    private var centerY = 0
    private var radius = 0

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = getBitmapFromResource(R.drawable.pic_2, width, context)
        rectDestination.set(0, 0, width, width)
        bitmap?.let {
            val bitmapWidth = it.width
            val bitmapHeight = it.height
            radius = if (bitmapHeight > bitmapWidth) bitmapWidth / 2 else height / 2
            centerX = width / 2
            centerY = height / 2
            val bitmapShader = BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
            paint.shader = bitmapShader
            rectSource.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), radius.toFloat(), paint)
    }
}