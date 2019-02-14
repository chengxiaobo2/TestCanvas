package com.example.test.testcanvas.indicatorLoading

import android.animation.TimeAnimator
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

/**
 * 四个方块，一个圆形的view
 *
 * @author chengxiaobo
 * @version 1.0
 * @title
 * @description
 * @company 北京奔流网络信息技术有线公司
 * @created 2019/2/14
 * @changeRecord <br/>
 */
class IndicatorView : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val indicator = Indicator()
    private val animator = TimeAnimator()

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        if (changed) {
            indicator.left = 0.0f
            indicator.right = width.toFloat()
            indicator.top = 0.0f
            indicator.bottom = height.toFloat()

            indicator.layout()

            animator.apply {
                setTimeListener { _, time, _ ->
                    indicator.progress = time.toDouble() / 1000.0 // progress每秒增加1
                    invalidate()
                }
                start()
            }
        }
    }

    fun start() {
        animator.start()
    }

    fun stop() {
        animator.cancel()
    }

    override fun onDraw(canvas: Canvas) {
        indicator.draw(canvas)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stop()
    }
}