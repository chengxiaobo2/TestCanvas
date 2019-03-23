package com.example.test.testcanvas.camera

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.testcanvas.R
import com.example.test.testcanvas.dp2px
import com.example.test.testcanvas.getBitmapFromResource
import kotlin.math.roundToLong

/**
 *
 * @author chengxiaobo
 * @time 2019/3/17 14:19
 */
class Camera1 : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val camera = Camera()
    val bitmapSource = Rect()
    var bitmap: Bitmap? = null

    val imageDestination = RectF()
    var centerX = 0.0f
    var centerY = 0.0f
    var imageWidth = 0.0f
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        imageWidth = width / 2.0f
        bitmap = getBitmapFromResource(R.drawable.pic_c, imageWidth.toInt(), context)
        with(bitmapSource) {
            top = 0
            bottom = imageWidth.toInt()
            left = 0
            right = imageWidth.toInt()
        }
        centerX = width / 2.0f
        centerY = height / 2.0f
        with(imageDestination) {
            top = centerY - imageWidth / 2
            bottom = centerY + imageWidth / 2
            left = centerX - imageWidth / 2
            right = centerX + imageWidth / 2
        }
        camera.setLocation(0.0f, 0.0f, dp2px(10.0f))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save()
        camera.save()
        camera.rotateX(-30.0f)
        canvas.translate(centerX, centerY)
        camera.applyToCanvas(canvas)
        canvas.translate(-centerX, -centerY)
        bitmap?.let {
            canvas?.drawBitmap(it, bitmapSource, imageDestination, paint)
        }
        camera.restore()
        canvas.restore()
    }
}