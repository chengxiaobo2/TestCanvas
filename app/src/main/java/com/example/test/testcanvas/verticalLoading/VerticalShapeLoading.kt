package com.example.test.testcanvas.verticalLoading

import android.animation.TimeAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import com.example.test.testcanvas.R

/**
 * 柱状图动画的View
 *
 * @author chengxiaobo
 * @version 1.0
 * @title
 * @description
 * @company 北京奔流网络信息技术有线公司
 * @created 2019/2/14
 * @changeRecord <br/>
 */
class VerticalShapeLoading : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.VerticalShapeLoading)
        loadingColor = typedArray.getColor(R.styleable.VerticalShapeLoading_loading_color, Color.WHITE)
        typedArray.recycle()
    }

    var loadingColor = Color.WHITE

    private val indicator = Indicator()
    private val animator = TimeAnimator()


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (changed) {
            indicator.padding = width * 0.15f
            indicator.margin = width * 0.06f
            if (indicator.margin < 1.0f) {
                indicator.margin = 1.0f
            }
            indicator.blockWith = (width - indicator.padding * 2 - indicator.margin * 3) / 4.0f
            indicator.radio = indicator.blockWith / 2.0f
            indicator.indicatorHeight = height

            indicator.baseLines = arrayListOf(height / 3.0f, height / 3.5f, height / 3.5f, height / 4.0f)
            indicator.amplitudes = arrayListOf(height / 2.5f, height / 3.5f, height / 3.0f, height / 4.0f)
            indicator.color = loadingColor
            indicator.layout()
            animator.apply {
                setTimeListener { _, time, _ ->
                    indicator.progress = (time.toFloat() / 1000f % 1.0f) * 2
                    invalidate()
                }
                start()
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        indicator.draw(canvas)
    }

    fun start() {
        animator.start()
    }

    fun stop() {
        animator.cancel()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stop()
    }
}