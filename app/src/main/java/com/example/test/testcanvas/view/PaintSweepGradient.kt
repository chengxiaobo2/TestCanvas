package com.example.test.testcanvas.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.testcanvas.dp2px

/**
 * paint 设置SweepGradient的shader
 *
 * @author chengxiaobo
 * @version 1.0
 * @title
 * @description
 * @company 北京奔流网络信息技术有线公司
 * @created 2019/2/14
 * @changeRecord  <br/>
 */
class PaintSweepGradient : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var centerX = 0.0f
    private var centerY = 0.0f
    private val rect = RectF()
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        centerX = width / 2.0f
        centerY = height / 2.0f
        val radius = width / 3.0f
        rect.left = centerX - radius
        rect.right = centerX + radius
        rect.top = centerY - radius
        rect.bottom = centerY + radius

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = dp2px(4.0f)

        paint.strokeCap = Paint.Cap.ROUND
        paint.shader = SweepGradient(centerX, centerY, Color.GRAY, Color.WHITE)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //TODO 注意，其实的角度要靠下一点，要不就会导致小帽子变成白色的了，原因是0度以上的位子为白色的
        canvas.drawArc(rect, 10.0f, 270.0f, false, paint)
    }

}