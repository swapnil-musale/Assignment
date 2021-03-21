package com.example.assignment.util

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.R

class CommonToolBarView {

    var commonBack: ImageView? = null
    var commonTitle: TextView? = null

    fun setup(activity: AppCompatActivity, title: String) {

        commonBack = activity.findViewById(R.id.commonBack)
        commonTitle = activity.findViewById(R.id.commonTitle)
        commonBack?.setOnClickListener {
            activity.finish()
        }
        commonTitle?.text = title
    }

    fun setup(activity: AppCompatActivity) {
        commonBack = activity.findViewById(R.id.commonBack)
        commonBack?.setOnClickListener {
            activity.finish()
        }
    }

    fun setup(activity: AppCompatActivity, title: String, onBackPress: OnBackPress) {
        setup(activity, title)
        commonBack?.setOnClickListener {
            onBackPress.onBack()
        }
    }

    interface OnBackPress {
        fun onBack()
    }
}