package com.example.assignment.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun View?.gone() {
    this?.visibility = View.GONE
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun RecyclerView.scrollToPosition(position: Int) {
    this.layoutManager.let {
        it?.smoothScrollToPosition(this, RecyclerView.State(), position)
    }
}