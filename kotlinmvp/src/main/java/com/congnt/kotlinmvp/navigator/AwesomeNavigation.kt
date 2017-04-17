package com.congnt.kotlinmvp.navigator

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.util.Log
import com.congnt.kotlinmvp.R
import com.congnt.kotlinmvp.mvp.BaseActivity
import java.util.*

/**
 * This class will handle adding, replacing, removing fragment from Activity
 * How to user:
 * 1. In activity, create instance of this class
 * 2. To open Fragment: call openFragment(....)
 * 3. To back to previous fragment: call goBack()
 * 4. To back to any fragment: call goToFragment(tag)
 * Created by congn on 3/20/2017.
 */
class AwesomeNavigation<in F : Fragment>(var activity: BaseActivity) {
    private var fragmentManager: FragmentManager? = null
    private var fragmentTransaction: FragmentTransaction? = null
    private var currentBackStackSize: Int = 0
    private val DURATION = activity.resources.getInteger(android.R.integer.config_shortAnimTime)
    private var preTime: Long = 0

    init {
        this.fragmentManager = activity.supportFragmentManager
        this.fragmentManager!!.addOnBackStackChangedListener(activity)
    }

    /**
     * Open fragment
     * @param containerId   the id container
     * @param fragment      he fragment which you need to show up
     * @param actionType    add or replace method
     * @param tag           Tag
     * @param animationType animation
     */
    fun openFragment(@IdRes containerId: Int, fragment: F
                     , tag: String? = null, actionType: FragmentActionType = FragmentActionType.ADD, animationType: FragmentAnimationType = FragmentAnimationType.BOTTOM_UP) {
        if (containerId == 0) return
        //Check time to skip opening 2,3 fragment when click very fast
        var current = Date().time
        if (current - preTime > animationType.value) {
            preTime = current
        } else {
            return
        }

        //Checking if this fragment is in the top of backstack
        if (fragmentManager!!.backStackEntryCount > 0 && fragmentManager!!.getBackStackEntryAt(fragmentManager!!.backStackEntryCount - 1).name == tag) {
            return
        }
        fragmentTransaction = fragmentManager!!.beginTransaction()

        when (animationType) {
            AwesomeNavigation.FragmentAnimationType.LEFT_RIGHT -> fragmentTransaction!!.setCustomAnimations(R.anim.slide_in_left,
                    R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
            AwesomeNavigation.FragmentAnimationType.BOTTOM_UP -> fragmentTransaction!!.setCustomAnimations(R.anim.slide_in_bottom,
                    R.anim.slide_out_top, R.anim.slide_in_top, R.anim.slide_out_bottom)
            AwesomeNavigation.FragmentAnimationType.NONE -> {}
        }
        when (actionType) {
            FragmentActionType.ADD -> {
                //Do something if add fragment suck as: Hide previous fragment
                //Check if already have fragment with this tag
                fragmentTransaction!!.add(containerId, fragment, tag)
                fragmentTransaction!!.addToBackStack(tag).commit()
            }
            FragmentActionType.REPLACE -> {
                //Do something if replace fragment suck as: Hide previous fragment
                clearBackStack()
                fragmentTransaction!!.replace(containerId, fragment, tag)
                fragmentTransaction!!.addToBackStack(tag).commit()
            }
        }//If the fragment you need to add was added, restore this to the top
    }

    private fun clearBackStack() {
        if (fragmentManager!!.backStackEntryCount > 0) {
            val first = fragmentManager!!.getBackStackEntryAt(0)
            fragmentManager!!.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    /**
     * Hide previous fragments
     */
    fun hideFragments() {
        val stackCount = fragmentManager!!.backStackEntryCount
        if (fragmentManager!!.fragments != null) {
            val fragmentSize = fragmentManager!!.fragments.size
            Log.d(TAG, "hideFragments() called$stackCount $fragmentSize")
            if (stackCount > currentBackStackSize) {//Add
                for (i in 0..stackCount - 1) {
                    fragmentTransaction!!.hide(fragmentManager!!.fragments[i])
                }
            } else {//remove
                val fragmentTransaction = fragmentManager!!.beginTransaction()
                fragmentTransaction.commit()
                for (i in 0..stackCount - 1) {
                    fragmentTransaction.hide(fragmentManager!!.fragments[i])
                }
            }
        }
        currentBackStackSize = stackCount
    }

    /**
     * We have a list of backstack, and we need to show a fragment in a middle of stack, so, We don't pop top fragment, we hide it
     */
    fun gotoFragment(tag: String): Boolean {
        var current = Date().time
        if (current - preTime > DURATION) {
            preTime = current
        } else {
            return false
        }
        val fragmentByTag = fragmentManager!!.findFragmentByTag(tag)
        if (fragmentByTag != null) {
            val backstackCount = fragmentManager!!.backStackEntryCount
            for (i in backstackCount - 1 downTo 0) {
                if (fragmentManager!!.getBackStackEntryAt(i).name == tag) {
                    Log.d(TAG, "gotoFragment: Found")
                    break
                }
                fragmentManager!!.popBackStack()
            }
            return true
        } else {
            return false
        }
    }

    /**
     * Back to previous fragment

     * @return true if it's success
     */
    fun goBack(): Boolean {
        if (fragmentManager!!.backStackEntryCount > 0) {
            var current = Date().time
            if (current - preTime > DURATION) {
                preTime = current
            } else {
                return false
            }
            fragmentManager!!.popBackStack()
            return true
        }
        return false
    }
    fun clearAll(){
        var current = Date().time
        if (current - preTime > DURATION) {
            preTime = current
        } else {
            return
        }
        fragmentManager!!.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    val isRoot: Boolean
        get() = fragmentManager!!.backStackEntryCount <= 0

    enum class FragmentActionType {
        ADD, REPLACE
    }

    enum class FragmentAnimationType (var value: Int){
        LEFT_RIGHT(200), BOTTOM_UP(400), NONE(0)
    }

    companion object {
        private val TAG = "NavigationManager"
    }
}
