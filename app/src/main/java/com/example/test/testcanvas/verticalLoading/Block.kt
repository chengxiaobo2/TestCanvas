package com.example.test.testcanvas.verticalLoading

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

/**
 * 柱状view
 *
 * @author chengxiaobo
 * @version 1.0
 * @title
 * @description
 * @company 北京奔流网络信息技术有线公司
 * @created 2019/2/14
 * @changeRecord <br/>
 */
class Block {

    var left = 0.0f
    var top = 0.0f
    var right = 0.0f
    var bottom = 0.0f
    var radio = 0.0f
    var rect = RectF()
    var loadingColor = Color.WHITE
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    fun draw(canvas: Canvas) {
        paint.color = loadingColor
        canvas.drawRoundRect(rect, radio, radio, paint)
    }
}