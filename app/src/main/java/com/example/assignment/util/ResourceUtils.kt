package com.example.assignment.util

import android.content.Context
import androidx.core.content.ContextCompat

object ResourceUtils {

    fun getColor(context: Context, colorInt: Int): Int {
        return ContextCompat.getColor(context, colorInt)
    }
}