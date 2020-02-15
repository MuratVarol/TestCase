package com.varol.testcase.internal.extension

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat.getSystemService


fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun AppCompatEditText.hideKeyboard() {
    val inputMethodManager =
        (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}