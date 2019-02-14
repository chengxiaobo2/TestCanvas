package com.example.test.testcanvas.indicatorLoading

/**
 * 四个块对应的动画函数
 *
 * @author chengxiaobo
 * @version 1.0
 * @title
 * @description
 * @company 北京奔流网络信息技术有线公司
 * @created 2019/2/14
 * @changeRecord <br/>
 */
class PhaseTranslator(val alpha: Double, private val delta: Double) {
    /**
     * 说明
     * alpha 为一秒几个周期
     * delta 为初始值
     * 计算过程
     * 1.求-cos
     * 2.-cos+1
     * 3.(-cos+1)*0.5
     */
    fun translate(value: Double): Double {
        return -Math.cos((value * alpha + delta) * (2.0 * Math.PI)) * 0.5 + 0.5
    }
}