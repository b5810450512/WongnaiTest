package com.test.wn.core.uibase

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.test.wn.R

abstract class FragmentBase : Fragment() {
    abstract fun initEvent()
    abstract fun initObserveViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvent()
        initObserveViewModel()
    }

    fun showLoading() = (activity as? ActivityBase)?.showLoading()

    fun hideLoading() = (activity as? ActivityBase)?.hideLoading()


    fun replaceFragment(resId: Int , fragment: Fragment , tag: String?) {
        fragmentManager!!.beginTransaction().apply {
            setCustomAnimations(R.anim.enter_left, R.anim.exit_left, R.anim.enter_right, R.anim.exit_right)
            replace(resId, fragment)
            addToBackStack(tag)
            commit()
        }
    }

    fun addFragment(resId: Int , fragment: Fragment , tag: String?) {
        fragmentManager!!.beginTransaction().apply {
            setCustomAnimations(R.anim.enter_left, R.anim.exit_left, R.anim.enter_right, R.anim.exit_right)
            add(resId, fragment)
            addToBackStack(tag)
            commit()
        }
    }

    fun replaceChildFragment(resId: Int , fragment: Fragment , tag: String?) {
        childFragmentManager.beginTransaction().apply {
            setCustomAnimations(R.anim.enter_left, R.anim.exit_left, R.anim.enter_right, R.anim.exit_right)
            replace(resId, fragment)
            addToBackStack(tag)
            commit()
        }
    }

    fun addChildFragment(resId: Int , fragment: Fragment , tag: String?) {
        childFragmentManager.beginTransaction().apply {
            setCustomAnimations(R.anim.enter_left, R.anim.exit_left, R.anim.enter_right, R.anim.exit_right)
            add(resId, fragment)
            addToBackStack(tag)
            commit()
        }
    }

    fun removeFragmentWithTag(tag: String) {
        val fragmentList = fragmentManager!!.fragments.filter { it.tag == tag }
        if (fragmentList.isNotEmpty()) {
            fragmentList.forEach {fragment->
                fragmentManager!!.beginTransaction().remove(fragment).commit()
            }
        }
    }

    fun removeFragmentWithTag(tagList: List<String>) {
        tagList.forEach { tag->
            val fragmentList = fragmentManager!!.fragments.filter { it.tag == tag }
            if (fragmentList.isNotEmpty()) {
                fragmentList.forEach {fragment->
                    fragmentManager!!.beginTransaction().remove(fragment).commit()
                }
            }
        }
    }
}