package com.example.test.testcanvas.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.example.test.testcanvas.R
import com.example.test.testcanvas.getBitmapFromResource

/**
 * drawBitmap demo
 *
 * @author chengxiaobo
 * @version 1.0
 * @title
 * @description
 * @company 北京奔流网络信息技术有线公司
 * @created 2019/2/13
 * @changeRecord <br/>
 */
class DrawBitmap : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var bitmap: Bitmap? = null
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectDestination = Rect()
    private val rectSource = Rect()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = getBitmapFromResource(R.drawable.pic, width, context)
        //canvas上drawBitmap的范围
        rectDestination.set(0, 0, width, width)

        bitmap?.let {
            val radius = Math.min(it.width / 2, it.height / 2)
            val centerX = it.width / 2
            val centerY = it.height / 2
            //从bitmap上取的范围
            rectSource.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint)
        //TODO 注意1：变形的bitmap
//        canvas.drawBitmap(bitmap, null, rectDestination, paint)
        //crop以后的bitmap
        canvas.drawBitmap(bitmap, rectSource, rectDestination, paint)
    }
}