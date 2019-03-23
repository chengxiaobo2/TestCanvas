package com.example.test.testcanvas.camera

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.testcanvas.R
import com.example.test.testcanvas.dp2px
import com.example.test.testcanvas.getBitmapFromResource

/**
 *
 * @author chengxiaobo
 * @time 2019/3/17 14:19
 */
class Camera4 : View {

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

    val rectRight = RectF()
    val rectLeft = RectF()
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
        camera.setLocation(0.0f, 0.0f, dp2px(6.0f))

        with(rectLeft) {
            top = -imageWidth
            bottom = imageWidth
            left = -imageWidth * 2
            right = 0.0f
        }
        with(rectRight) {
            top = -imageWidth
            bottom = imageWidth
            left = 0.0f
            right = imageWidth
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //左侧
        canvas.save()
        canvas.translate(centerX, centerY)
        canvas.rotate(-10.0f)
        canvas.clipRect(rectLeft)
        canvas.rotate(10.0f)
        canvas.translate(-centerX, -centerY)
        //画
        bitmap?.let {
            canvas?.drawBitmap(it, bitmapSource, imageDestination, paint)
        }
        canvas.restore()

        //右侧
        camera.save()
        camera.rotateY(30.0f)
        canvas.save()
        canvas.translate(centerX, centerY)
        canvas.rotate(-10.0f)
        camera.applyToCanvas(canvas)
        canvas.clipRect(rectRight)
        canvas.rotate(10.0f)
        canvas.translate(-centerX, -centerY)
        //画
        bitmap?.let {
            canvas?.drawBitmap(it, bitmapSource, imageDestination, paint)
        }
        camera.restore()
        canvas.restore()
    }
}