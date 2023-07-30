package com.baidu.androidlearn.lesson9

import android.animation.Animator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LessonFourActivity : AppCompatActivity() {

    private var rootView: FrameLayout? = null

    /** RecycleView 实例 */
    private var recycleView: RecyclerView? = null

    /** RecycleView 的适配器 */
    private var adapter: DemoAdapter? = null

    /** 使用帧动画实现的loading */
    private var frameAnimLoading:ImageView? = null
    /** 使用补间动画实现的loading */
    private var rotateAnimLoading:ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lesson_4)
        rootView = findViewById(R.id.root)


        // 添加RecycleView
//        addRecycleView()
    }

    /**
     * 添加RecycleView
     */
    private fun addRecycleView() {
        // 从布局中进行获取
        recycleView = findViewById(R.id.rv_demo)
        adapter = DemoAdapter(createDemoDate())
        recycleView?.adapter = adapter
        recycleView?.layoutManager = LinearLayoutManager(this)
    }

    /**
     * 展示使用帧动画实现的loading
     */
    private fun showFrameAnimLoading() {
        frameAnimLoading = findViewById(R.id.iv_loading)
        (frameAnimLoading?.background as? AnimationDrawable)?.start()
    }

    /**
     * 展示补间动画实现的loading
     */
    @SuppressLint("ResourceType")
    private fun showRotateLoading() {
        rotateAnimLoading = findViewById(R.id.iv_loading)
        rootView?.setBackgroundColor(Color.BLACK)

        val anim = AnimationUtils.loadAnimation(this, R.anim.rotate_anim)
        anim.interpolator = LinearInterpolator()
        rotateAnimLoading?.startAnimation(anim)
    }

    /**
     * 展示属性动画
     */
    private fun showValueAnim() {
        val view = findViewById<ImageView>(R.id.iv_loading)
        val valueAnim = ValueAnimator.ofInt(0, 100)
        valueAnim?.duration = 5000L
        // 加速
//        valueAnim?.interpolator = AccelerateInterpolator(10f)
        // 减速
        valueAnim?.interpolator = DecelerateInterpolator(10f)
        valueAnim?.addUpdateListener {
            val currentValue = it.animatedValue as Int
//            view.alpha = currentValue / 100f
            view.translationY = currentValue * 10f
        }

        valueAnim?.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator, isReverse: Boolean) {

            }


            override fun onAnimationEnd(animation: Animator) {

            }


            override fun onAnimationCancel(animation: Animator) {

            }


            override fun onAnimationRepeat(animation: Animator) {

            }

        })

        valueAnim?.start()

    }

    private fun createDemoDate(): ArrayList<ItemBean> {

        val list = ArrayList<ItemBean>()

        val firstItem = ItemBean(R.drawable.ic_launcher_background, "我是标题11", "我是内容11")
        val secondItem =
            ItemBean(R.drawable.ic_launcher_foreground, "我是标题22", "我是内容22我是内容22我是内容22我是内容22")

        for (i in 0 until 10) {
            list.add(firstItem)
            list.add(secondItem)
        }

        return list
    }

    fun startAnim(view: View) {
        // 帧动画
//        showFrameAnimLoading()
        // 补间动画
//        showRotateLoading()
        // 属性动画
        showValueAnim()
    }
}