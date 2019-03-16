package com.example.test.testcanvas.drawText

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.testcanvas.R
import com.example.test.testcanvas.dp2px
import com.example.test.testcanvas.getBitmapFromResource

/**
 * TestBreakText-实现图文混杂
 * @author chengxiaobo
 * @time 2019/3/16 18:38
 */
class TestBreakText : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    val s =
        "Android是一种基于Linux的自由及开放源代码的操作系统。主要使用于移动设备，如智能手机和平板电脑，由Google（谷歌）公司和开放手机联盟领导及开发。尚未有统一中文名称，中国大陆地区较多人使用“安卓”或“安致”。Android操作系统最初由Andy Rubin开发，主要支持手机。2005年8月由Google收购注资。2007年11月，Google与84家硬件制造商、软件开发商及电信营运商组建开放手机联盟共同研发改良Android系统。随后Google以Apache开源许可证的授权方式，发布了Android的源代码。第一部Android智能手机发布于2008年10月。Android逐渐扩展到平板电脑及其他领域上，如电视、数码相机、游戏机、智能手表等。2011年第一季度，Android在全球的市场份额首次超过塞班系统，跃居全球第一。 2013年的第四季度，Android平台手机的全球市场份额已经达到78.1%。 [1]  2013年09月24日谷歌开发的操作系统Android在迎来了5岁生日，全世界采用这款系统的设备数量已经达到10亿台。"
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var imageTopMargin = 0.0f //图片距离顶部距离
    private var imageWidth = 0.0f
    private val bitmapSourceRect = Rect()
    private val desImageRect = RectF()
    private var lineSpacing = 0.0f
    private var bitmap: Bitmap? = null
    private var floatArray = FloatArray(2)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        paint.textSize = dp2px(16.0f)
        lineSpacing = paint.fontSpacing

        imageWidth = width / 3.0f
        imageTopMargin = dp2px(50.0f)

        //bitmap
        bitmap = getBitmapFromResource(R.drawable.pic_1, imageWidth.toInt(), context)
        bitmap?.let {
            var bitmapSourceWidth = Math.min(it.width, it.height)
            with(bitmapSourceRect) {
                top = it.height / 2 - bitmapSourceWidth / 2
                bottom = it.height / 2 + bitmapSourceWidth / 2
                left = it.width / 2 - bitmapSourceWidth / 2
                right = it.width / 2 + bitmapSourceWidth / 2
            }
        }

        with(desImageRect) {
            top = imageTopMargin
            bottom = imageTopMargin + imageWidth
            left = width - imageWidth
            right = width.toFloat()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        bitmap.let {
            canvas.drawBitmap(bitmap, bitmapSourceRect, desImageRect, paint)
        }

        var count = 0
        var line = 1
        while (count < s.length) {
            val top = (line - 1) * lineSpacing
            val bottom = line * lineSpacing
            val textWidth =
                if ((top < desImageRect.top && bottom < desImageRect.top) || (top > desImageRect.bottom && bottom > desImageRect.bottom)) {
                    width.toFloat()
                } else {
                    width.toFloat() - imageWidth
                }

            var count1 = paint.breakText(s, count, s.length, true, textWidth, floatArray)
            canvas.drawText(s, count, count + count1, 0.0f, -paint.fontMetrics.top + lineSpacing * (line - 1), paint)
            count += count1
            line++
        }
    }
}