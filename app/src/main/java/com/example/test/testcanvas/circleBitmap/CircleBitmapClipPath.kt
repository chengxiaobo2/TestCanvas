package com.example.test.testcanvas.circleBitmap

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.testcanvas.R
import com.example.test.testcanvas.dp2px
import com.example.test.testcanvas.getBitmapFromResource

/**
 * clipPath实现圆形图片
 *
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
        path.addCircle(width / 2.0f, width / 2.0f, width / 2.0f, Path.Direction.CCW)
        bitmap?.let {
            val radius = Math.min(it.width / 2, it.height / 2)
            val bitmapCenterX = it.width / 2
            val bitmapCenterY = it.height / 2
            rectSource.set(
                bitmapCenterX - radius,
                bitmapCenterY - radius,
                bitmapCenterX + radius,
                bitmapCenterY + radius
            )
        }
        paint.textSize = dp2px(14.0f)
        paint.color = Color.BLACK
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save()
        canvas.clipPath(path)
        canvas.drawBitmap(bitmap, rectSource, rectDestination, paint)
        canvas.restore()
        canvas.drawText("clipPath", 0.0f, dp2px(14.0f), paint)
    }
}