package com.test.wn.core.uibase

import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.test.wn.R
import kotlinx.android.synthetic.main.custom_loading.*

abstract class ActivityBase : AppCompatActivity() {

    abstract fun initEvent()
    abstract fun initObserveViewModel()

    fun isLoading() = appLoading.visibility == View.VISIBLE

    fun showLoading() {
        runOnUiThread {
            appLoading?.visibility = View.VISIBLE
            progressBar?.apply {
                show()
                bringToFront()
            }
            setViewAndChildrenEnabled(false)
        }
    }

    fun hideLoading() {
        runOnUiThread {
            appLoading?.visibility = View.GONE
            progressBar?.apply {
                hide()
            }
            setViewAndChildrenEnabled(true)
        }
    }

    fun replaceFragment(resId: Int , fragment: Fragment , tag: String?) {
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(R.anim.enter_left, R.anim.exit_left, R.anim.enter_right, R.anim.exit_right)
            replace(resId, fragment)
            addToBackStack(tag)
            commit()
        }
    }
//    fun replaceFragmentWithNoAnim(resId: Int , fragment: Fragment , tag: String?) {
//        supportFragmentManager.beginTransaction().apply {
//            replace(resId, fragment)
//            addToBackStack(tag)
//            commit()
//        }
//    }

    fun addFragment(resId: Int , fragment: Fragment , tag: String?) {
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(R.anim.enter_left, R.anim.exit_left, R.anim.enter_right, R.anim.exit_right)
            add(resId, fragment)
            addToBackStack(tag)
            commit()
        }
    }

    private fun setViewAndChildrenEnabled(enabled: Boolean) {
        if (enabled) window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        else window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    fun removeFragmentWithTag(tag: String) {
        val fragmentList = supportFragmentManager.fragments.filter { it.tag == tag }
        if (fragmentList.isNotEmpty()) {
            fragmentList.forEach {fragment->
                supportFragmentManager.beginTransaction().remove(fragment).commit()
            }
        }
    }

    fun removeFragmentWithTag(tagList: List<String>) {
        tagList.forEach { tag->
            val fragmentList = supportFragmentManager.fragments.filter { it.tag == tag }
            if (fragmentList.isNotEmpty()) {
                fragmentList.forEach {fragment->
                    supportFragmentManager.beginTransaction().remove(fragment).commit()
                }
            }
        }
    }

}