package com.jaydev.coroutinesample.prevent_click

import android.view.View
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.actor


fun View.setOnPreventClickListener(action: suspend (View) -> Unit) {
    val event = GlobalScope.actor<View>(Dispatchers.Main) {
        for (event in channel) action(event)
    }

    setOnClickListener{
        event.offer(it)
    }
}