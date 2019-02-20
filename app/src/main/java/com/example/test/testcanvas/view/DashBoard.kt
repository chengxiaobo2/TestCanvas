package com.example.test.testcanvas.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.testcanvas.dp2px
import kotlin.math.cos

/**
 *  仪表盘
 * 扔物线的代码 -- https://github.com/rengwuxian/HenCoderPlus/blob/master/06-drawing/src/main/java/com/hencoder/plus/view/Dashboard.java
 *
 * @author chengxiaobo
 * @version 1.0
 * @title
 * @description
 * @company 北京奔流网络信息技术有线公司
 * @created 2019/2/16
 * @changeRecord <br/>
 */
class DashBoard : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var centerX = 0.0f
    private var centerY = 0.0f
    private var radius = 0.0f
    private var rect = RectF()
    private val path = Path()
    private val dashPath = Path()

    private var pathDashPathEffect: PathDashPathEffect? = null
    private var startAngle = 0.0f
    private var sweepAngle = 0.0f
    private var pointerAngle = 0.0f
    private var pointerX = 0.0f
    private var pointerY = 0.0f

    private var dashPathWidth = dp2px(2.0f) //刻度的宽度
    private var dashPathHeight = dp2px(5.0f) //刻度的长度
    private var degreeCount = 20 //一共多少个刻度
    private var currentItem = 10 //指针指向第几个
    private val middleAngle = 60.0f //仪表盘中间空缺的角度

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        centerX = width / 2.0f
        centerY = width / 2.0f
        radius = width / 3.0f
        rect.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)

        //计算起始角度和扫描的角度
        startAngle = middleAngle + (180 - middleAngle) / 2.0f
        sweepAngle = 360.0f - middleAngle

        //计算指针的读数，以及指针的x,y值
        pointerAngle = startAngle + (currentItem - 1) * (360 - middleAngle) / degreeCount
        pointerX = Math.cos(Math.toRadians(pointerAngle.toDouble())).toFloat() * width / 4.0f
        pointerY = Math.sin(Math.toRadians(pointerAngle.toDouble())).toFloat() * width / 4.0f

        //用虚线画path
//        paint.setPathEffect(DashPathEffect(floatArrayOf(dp2px(4.0f), dp2px(2.0f)), 0.0f))

        //刻度的path
        path.addArc(rect, startAngle, sweepAngle)
        val pathMeasure = PathMeasure(path, false)

        //DashPathRect 长方形,长方形的path
        dashPath.addRect(RectF(0.0f, 0.0f, dashPathWidth, dashPathHeight), Path.Direction.CCW)

        pathDashPathEffect = PathDashPathEffect(
            dashPath,
            (pathMeasure.length - dashPathWidth) / degreeCount,
            0.0f,
            PathDashPathEffect.Style.ROTATE
        )


        paint.style = Paint.Style.STROKE
        paint.strokeWidth = dp2px(2.0f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //画线
        canvas.drawArc(rect, startAngle, sweepAngle, false, paint)

        //画刻度
        paint.pathEffect = pathDashPathEffect
        canvas.drawPath(path, paint)
        paint.pathEffect = null

        //画指针
        canvas.save()
        canvas.translate(centerX, centerY)
        canvas.drawLine(0.0f, 0.0f, pointerX, pointerY, paint)
        canvas.restore()


    }
}