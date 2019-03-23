package com.example.test.testcanvas.camera

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.testcanvas.R
import com.example.test.testcanvas.dp2px
import com.example.test.testcanvas.getBitmapFromResource

/**
 * Flipboard翻转动画
 * 扔物线代码-https://github.com/rengwuxian/HenCoderPlus/blob/master/08_animation/src/main/java/com/hencoder/a08_animation/view/FancyFlipView.java
 * @author chengxiaobo
 * @time 2019/3/23 18:45
 */
class FlipboradAnimation : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val camera = Camera()
    val bitmapSource = Rect()
    var bitmap: Bitmap? = null

    val imageDestination = RectF()
    var centerX = 0.0f
    var centerY = 0.0f
    var imageWidth = 0.0f

    val rectRight = RectF()
    val rectLeft = RectF()

    var rightTranslateY = 0.0f  //0-30
        set(value) {
            invalidate()
            field = value
        }
    var leftTranslateY = 0.0f   //0--30
        set(value) {
            invalidate()
            field = value
        }
    var rotate = 0.0f //0-270
        set(value) {
            invalidate()
            field = value
        }

    var rightTranslateYAnimation: ObjectAnimator
    var leftTranslateYAnimation: ObjectAnimator
    var rotateAnimation: ObjectAnimator

    init {
        rightTranslateYAnimation = ObjectAnimator.ofFloat(this, "rightTranslateY", 0.0f, 30.0f)
        leftTranslateYAnimation = ObjectAnimator.ofFloat(this, "leftTranslateY", 0.0f, -30.0f)
        rotateAnimation = ObjectAnimator.ofFloat(this, "rotate", 0.0f, 270.0f)
        rightTranslateYAnimation.setDuration(1000L).addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                rotateAnimation.start()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
        rotateAnimation.setDuration(1500L).addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                leftTranslateYAnimation.start()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
        leftTranslateYAnimation.setDuration(1000L)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        imageWidth = width / 2.0f
        bitmap = getBitmapFromResource(R.drawable.pic_c, imageWidth.toInt(), context)
        with(bitmapSource) {
            top = 0
            bottom = imageWidth.toInt()
            left = 0
            right = imageWidth.toInt()
        }
        centerX = width / 2.0f
        centerY = height / 2.0f
        with(imageDestination) {
            top = centerY - imageWidth / 2
            bottom = centerY + imageWidth / 2
            left = centerX - imageWidth / 2
            right = centerX + imageWidth / 2
        }
        camera.setLocation(0.0f, 0.0f, dp2px(6.0f))

        with(rectLeft) {
            top = -imageWidth
            bottom = imageWidth
            left = -imageWidth * 2
            right = 0.0f
        }
        with(rectRight) {
            top = -imageWidth
            bottom = imageWidth
            left = 0.0f
            right = imageWidth
        }

        postDelayed({
            rightTranslateYAnimation.start()
        }, 2000L)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //左侧
        camera.save()
        camera.rotateY(leftTranslateY)
        canvas.save()
        canvas.translate(centerX, centerY)
        canvas.rotate(-rotate)
        camera.applyToCanvas(canvas)
        canvas.clipRect(rectLeft)
        canvas.rotate(rotate)
        canvas.translate(-centerX, -centerY)
        //画
        bitmap?.let {
            canvas?.drawBitmap(it, bitmapSource, imageDestination, paint)
        }
        camera.restore()
        canvas.restore()

        //右侧
        camera.save()
        camera.rotateY(rightTranslateY)
        canvas.save()
        canvas.translate(centerX, centerY)
        canvas.rotate(-rotate)
        camera.applyToCanvas(canvas)
        canvas.clipRect(rectRight)
        canvas.rotate(rotate)
        canvas.translate(-centerX, -centerY)
        //画
        bitmap?.let {
            canvas?.drawBitmap(it, bitmapSource, imageDestination, paint)
        }
        camera.restore()
        canvas.restore()

    }
}