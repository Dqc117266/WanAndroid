package com.dqc.base.presentation.ext

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


fun View.showKeyboard(): Boolean {
    this.requestFocus()
    val inputMethodManager: InputMethodManager? =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    return inputMethodManager?.showSoftInput(this, 0) ?: false
}

fun View.hideKeyboard(): Boolean {
    this.clearFocus()
    val inputMethodManager: InputMethodManager? =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    return inputMethodManager?.hideSoftInputFromWindow(this.windowToken, 0) ?: false
}