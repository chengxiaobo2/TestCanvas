package com.example.test.testcanvas.indicatorLoading

/**
 * 弧形loading的动画函数
 *
 * @author chengxiaobo
 * @version 1.0
 * @title
 * @description
 * @company 北京奔流网络信息技术有线公司
 * @created 2019/2/14
 * @changeRecord [修改记录] <br/>
 */
// 在0到1之间循环，需要保证value * alpha + delta >= 0
class LoopTranslator(val alpha: Double, private val delta: Double) {
    fun translate(value: Double): Double {
        return (value * alpha + delta) % 1.0
    }
}