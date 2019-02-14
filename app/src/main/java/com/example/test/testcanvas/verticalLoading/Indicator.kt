package com.example.test.testcanvas.verticalLoading

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF

/**
 * @author chengxiaobo
 * @version 1.0
 * @title
 * @description
 * @company 北京奔流网络信息技术有线公司
 * @created 2019/2/14
 * @changeRecord <br/>
 */
class Indicator {

    private val blocks = arrayListOf(Block(), Block(), Block(), Block())
    var padding = 0.0f
    var margin = 0.0f
    var blockWith = 0.0f
    var radio = 0.0f
    var indicatorHeight = 0
    var progress = 0.0f
    var color = Color.WHITE

    var baseLines = arrayListOf(0.0f, 0.0f, 0.0f, 0.0f) //基准线
    var amplitudes = arrayListOf(0.0f, 0.0f, 0.0f, 0.0f) //振幅
    private var initPercent = arrayListOf(0.0f, 0.5f, 0.0f, 0.5f) //初始位置

    fun layout() {

        with(blocks[0]) {
            left = padding
            right = left + blockWith
            top = getTopValue(baseLines[0], amplitudes[0], initPercent[0], 0.0f).toFloat()
            rect = RectF(left, top, right, bottom)
            loadingColor = color
        }

        with(blocks[1]) {
            left = padding + blockWith + margin
            right = left + blockWith
            top = getTopValue(baseLines[1], amplitudes[1], initPercent[1], 0.0f).toFloat()
            rect = RectF(left, top, right, bottom)
            loadingColor = color
        }

        with(blocks[2]) {
            left = padding + blockWith * 2 + margin * 2
            right = left + blockWith
            top = getTopValue(baseLines[2], amplitudes[2], initPercent[2], 0.0f).toFloat()
            rect = RectF(left, top, right, bottom)
            loadingColor = color
        }

        with(blocks[3]) {
            left = padding + blockWith * 3 + margin * 3
            right = left + blockWith
            top = getTopValue(baseLines[3], amplitudes[3], initPercent[3], 0.0f).toFloat()
            rect = RectF(left, top, right, bottom)
            loadingColor = color
        }

        for (i in 0 until blocks.size) {
            blocks[i].radio = radio
        }


    }

    private fun getTopValue(baseLine: Float, amplitude: Float, init: Float, percent: Float): Double {
        return -(Math.abs(Math.sin(((percent + init)) * Math.PI)) * amplitude + baseLine)
    }

    fun draw(canvas: Canvas) {
        for (i in 0 until blocks.size) {
            blocks[i].top = getTopValue(baseLines[i], amplitudes[i], initPercent[i], progress).toFloat()
            blocks[i].rect.top = blocks[i].top
        }
        canvas.save()
        canvas.translate(0.0f, indicatorHeight - padding)
        for (i in 0 until blocks.size) {
            blocks[i].draw(canvas)
        }
        canvas.restore()
    }
}