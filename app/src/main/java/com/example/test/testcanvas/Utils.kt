package com.example.test.testcanvas

import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.util.TypedValue


/**
 * 工具类
 *
 * @author chengxiaobo
 * @version 1.0
 * @title
 * @description
 * @company 北京奔流网络信息技术有线公司
 * @created 2019/2/13
 * @changeRecord <br/>
 */

fun getBitmapFromResource(drawableId: Int, width: Int, context: Context): Bitmap {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(context.resources, drawableId, options)
    options.inJustDecodeBounds = false
    options.inDensity = options.outWidth
    options.inTargetDensity = width
    return BitmapFactory.decodeResource(context.resources, drawableId, options)
}

// px dp 互转
fun dp2Px(context: Context, dp: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

fun dp2px(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().displayMetrics)
}
