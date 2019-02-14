package com.example.test.testcanvas.indicatorLoading

import android.animation.ArgbEvaluator
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

/**
 * 基本形状 - 块
 * @author chengxiaobo
 * @version 1.0
 * @title
 * @description
 * @company 北京奔流网络信息技术有线公司
 * @created 2019/2/14
 * @changeRecord <br/>
 */
class Block {
    // 通过设置alpha值控制状态。alpha值需要在0.0f~1.0f之间。
    var alpha = 0.0f

    var left: Float = 0.0f
    var top: Float = 0.0f
    var right: Float = 0.0f
    var bottom: Float = 0.0f
    var cornerRadius: Float = 0.0f
    // 通过color0和color1设置颜色渐变
    var color0: Int = 0
    var color1: Int = 0
    lateinit var rect: RectF

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val evaluator = ArgbEvaluator()

    fun draw(canvas: Canvas?) {
        paint.color = evaluator.evaluate(alpha, color0, color1) as Int
        canvas?.drawRoundRect(rect, cornerRadius, cornerRadius, paint)
    }
}