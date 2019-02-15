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
 * @changeRecord <br/>
 */
class CircleBitmapClipPath : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var bitmap: Bitmap? = null
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectDestination = Rect()
    private val rectSource = Rect()
    private val path = Path()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = getBitmapFromResource(R.drawable.pic_1, width, context)
        rectDestination.set(0, 0, width, width)
        bitmap?.let {
            val bitmapWidth = it.width
            val bitmapHeight = it.height
            val radius = if (bitmapHeight > bitmapWidth) bitmapWidth / 2 else height / 2
            val centerX = width / 2
            val centerY = height / 2
            rectSource.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
            path.addCircle(centerX.toFloat(), centerY.toFloat(), radius.toFloat(), Path.Direction.CCW)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.clipPath(path)
        canvas.drawBitmap(bitmap, rectSource, rectDestination, paint)
    }
}