package com.example.test.testcanvas

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Bitmap


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
