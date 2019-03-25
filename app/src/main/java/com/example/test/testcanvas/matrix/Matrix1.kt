package com.example.test.testcanvas.matrix

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.testcanvas.dp2Px
import com.example.test.testcanvas.dp2px

/**
 * 矩阵
 *
 * @author chengxiaobo
 * @time 2019/3/25 21:20
 */
class Matrix1 : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    var centerX = 0.0f
    var centerY = 0.0f

    val rect: RectF = RectF()
    var translation = 0.0f
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val myMatrix = Matrix()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        centerX = width / 2.0f
        centerY = height / 2.0f

        val rectWidth = width / 3.0f
        translation = dp2Px(context, 50.0f).toFloat()

        with(rect) {
            left = centerX - rectWidth / 2
            right = centerX + rectWidth / 2
            top = centerY - rectWidth / 2
            bottom = centerY + rectWidth / 2
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = Color.RED
        canvas.drawLine(0.0f, height / 2.0f, width.toFloat(), height / 2.0f, paint)
        canvas.drawLine(width / 2.0f, 0.0f, width / 2.0f, height.toFloat(), paint)

        //方案一实现
        canvas.save()
        canvas.translate(translation, translation)
        canvas.translate(centerX, centerY)
        canvas.scale(2.0f, 2.0f)
        canvas.translate(-centerX, -centerY)
        canvas.drawRect(rect, paint)
        canvas.restore()

        //方案二 canvas的变换相当于用matrix这样实现
        canvas.save()
        myMatrix.reset()
        myMatrix.preTranslate(translation, translation)
        myMatrix.preTranslate(centerX, centerY)
        myMatrix.preScale(2.0f, 2.0f)
        myMatrix.preTranslate(-centerX, -centerY)
        canvas.matrix=myMatrix
        canvas.drawRect(rect,paint)
        canvas.restore()

        //方案三 用post变换去实现
        canvas.save()
        myMatrix.reset()
        myMatrix.postTranslate(-centerX, -centerY)
        myMatrix.postScale(2.0f, 2.0f)
        myMatrix.postTranslate(centerX, centerY)
        myMatrix.postTranslate(translation, translation)
        canvas.matrix=myMatrix
        canvas.drawRect(rect,paint)
        canvas.restore()
    }
}