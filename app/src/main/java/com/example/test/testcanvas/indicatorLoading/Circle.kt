package com.example.test.testcanvas.indicatorLoading

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.SweepGradient

/**
 * 基本形状 - 圆形loading
 *
 * @author chengxiaobo
 * @version 1.0
 * @title
 * @description
 * @company 北京奔流网络信息技术有线公司
 * @created 2019/2/14
 * @changeRecord <br/>
 */
class Circle {
    // 通过设置alpha值控制状态。alpha值需要在0.0f~1.0f之间。
    var alpha = 0.0f

    var cx: Float = 0.0f
    var cy: Float = 0.0f
    var r: Float = 0.0f
    var strokeWidth: Float // 圆圈的宽度
        get() = paint.strokeWidth
        set(value) {
            paint.strokeWidth = value
        }
    // 通过color0和color1设置颜色渐变
    var color0: Int = 0
    var color1: Int = 0

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        paint.style = Paint.Style.STROKE
    }

    fun updateGradient() {
        paint.shader = SweepGradient(cx, cy, color0, color1)
    }

    fun draw(canvas: Canvas?) {
        canvas?.save()
        canvas?.rotate(360.0f * alpha, cx, cy)
        canvas?.drawCircle(cx, cy, r, paint)
        canvas?.restore()
    }
}