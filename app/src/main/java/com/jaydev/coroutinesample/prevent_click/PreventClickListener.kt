package com.jaydev.coroutinesample.prevent_click

import android.view.View
import androidx.annotation.IdRes
import java.util.*


class PreventClickListener(private val onClickListener: View.OnClickListener) : View.OnClickListener {

    private val preventLimitTime: Long = 3000

    private val prevClickTimeMap = Hashtable<Int, Long>()

    override fun onClick(v: View) {
        if (isAbleToClick(v.id)) {
            onClickListener.onClick(v)
        }
    }

    private fun isAbleToClick(@IdRes id: Int): Boolean {

        var prevClickTime = prevClickTimeMap.getOrElse(id) { 0 }

        if (prevClickTimeMap.containsKey(id))
            prevClickTime = prevClickTimeMap[id]

        val currentTime = System.currentTimeMillis()
        return if (currentTime - prevClickTime > preventLimitTime) {
            prevClickTime = currentTime
            prevClickTimeMap[id] = prevClickTime
            true
        } else {
            false
        }
    }
}