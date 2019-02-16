package com.example.test.testcanvas.circleBitmap

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.testcanvas.R
import com.example.test.testcanvas.dp2px
import com.example.test.testcanvas.getBitmapFromResource

/**
 * xfermode实现圆形图片
 *
 * @author chengxiaobo
 * @version 1.0
 * @title
 * @description
 * @company 北京奔流网络信息技术有线公司
 * @created 2019/2/15
 * @changeRecord  <br/>
 */
class CircleBitmapXfermode : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var bitmap: Bitmap? = null
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectDestination = RectF()
    private val rectSource = Rect()
    private val Xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    private var centerX = 0.0f
    private var centerY = 0.0f
    private var radius = 0.0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = getBitmapFromResource(R.drawable.pic_3, width, context)
        rectDestination.set(0.0f, 0.0f, width.toFloat(), width.toFloat())
        radius = width / 2.0f
        centerX = width / 2.0f
        centerY = height / 2.0f

        bitmap?.let {
            val bitmapRadius = Math.min(it.width / 2, it.height / 2)
            val bitmapCenterX = it.width / 2
            val bitmapCenterY = it.height / 2
            rectSource.set(
                bitmapCenterX - bitmapRadius,
                bitmapCenterY - bitmapRadius,
                bitmapCenterX + bitmapRadius,
                bitmapCenterY + bitmapRadius
            )
        }
        paint.textSize = dp2px(14.0f)
        paint.color = Color.BLACK
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG)
        //TODO 注意1 目标图像
        canvas.drawCircle(centerX, centerY, radius, paint)
        paint.xfermode = Xfermode
        //TODO 注意2 原图像
        canvas.drawBitmap(bitmap, rectSource, rectDestination, paint)
        canvas.restoreToCount(saved)

        paint.xfermode = null
        canvas.drawText("xfermode", 0.0f, dp2px(14.0f), paint)
    }
}