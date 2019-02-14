package com.example.test.testcanvas.indicatorLoading

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF
import kotlin.math.min

/**
 *
 *
 * @author chengxiaobo
 * @version 1.0
 * @title
 * @description
 * @company 北京奔流网络信息技术有线公司
 * @created 2019/2/14
 * @changeRecord <br/>
 */
class Indicator {
    // 设置progress控制显示状态
    var progress: Double = 0.0

    var left: Float = 0.0f
    var right: Float = 0.0f
    var top: Float = 0.0f
    var bottom: Float = 0.0f

    private val circle = Circle()
    private val blocks = arrayOf(Block(), Block(), Block(), Block())

    private val circleTranslator = LoopTranslator(1.0, 0.0) // 一秒一个周期，初始位置位0。如果需要逆时针旋转，可将alpha设为负数
    private val blockTranslators = arrayOf(
            PhaseTranslator(1.0, 0.0),
            PhaseTranslator(2.0, 0.25), // 一秒两个周期，初始相位为二分之π(0.25 * 2π)，
            PhaseTranslator(0.5, 0.5), // 两秒一个周期，初始相位为π
            PhaseTranslator(1.0, 0.75)
    )

    // 设置渐变
    init {
        with(circle) {
            color0 = Color.WHITE
            color1 = Color.GRAY
        }

        with(blocks[0]) {
            color0 = Color.WHITE
            color1 = Color.GRAY
        }

        with(blocks[1]) {
            color0 = Color.WHITE
            color1 = Color.GRAY
        }

        with(blocks[2]) {
            color0 = Color.WHITE
            color1 = Color.GRAY
        }

        with(blocks[3]) {
            color0 = Color.WHITE
            color1 = Color.GRAY
        }
    }

    // 修改位置后必须调用layout
    fun layout() {
        val width = right - left
        val height = bottom - top
        val cx = left + width * 0.5f
        val cy = top + height * 0.5f

        val size = min(width, height)

        val blockSize = size * 0.25f
        val blockSpacing = size * 0.02f
        val blockCornerSize = size * 0.05f

        val circleRadius = size * 0.45f
        val circleWidth = size * 0.025f

        with(circle) {
            this.cx = cx
            this.cy = cy
            r = circleRadius
            strokeWidth = circleWidth

            updateGradient()
        }

        with(blocks[0]) {
            right = cx - blockSpacing
            bottom = cy - blockSpacing
            left = right - blockSize
            top = bottom - blockSize
            rect = RectF(left, top, right, bottom)
        }

        with(blocks[1]) {
            left = cx + blockSpacing
            bottom = cy - blockSpacing
            right = left + blockSize
            top = bottom - blockSize
            rect = RectF(left, top, right, bottom)
        }

        with(blocks[2]) {
            right = cx - blockSpacing
            top = cy + blockSpacing
            left = right - blockSize
            bottom = top + blockSize
            rect = RectF(left, top, right, bottom)
        }

        with(blocks[3]) {
            left = cx + blockSpacing
            top = cy + blockSpacing
            right = left + blockSize
            bottom = top + blockSize
            rect = RectF(left, top, right, bottom)
        }

        for (i in blocks) {
            i.cornerRadius = blockCornerSize
        }
    }

    fun draw(canvas: Canvas?) {
        circle.alpha = circleTranslator.translate(progress).toFloat()

        for (i in 0..3) {
            blocks[i].alpha = blockTranslators[i].translate(progress).toFloat()
        }

        circle.draw(canvas)
        for (i in blocks) {
            i.draw(canvas)
        }
    }

}
