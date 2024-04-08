package com.dqc.base.presentation.activity

import android.os.Bundle
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import timber.log.Timber

class BaseActivity(@LayoutRes contentLayoutId: Int) : AppCompatActivity(contentLayoutId) {

    companion object {
        val Fragment.mainActivity: BaseActivity
            get() = activity as BaseActivity
    }

    var appBarLayout: LinearLayout? = null;
    var mainAppToolbar: MaterialToolbar? = null;
    var SearchTextInputEditText: TextInputEditText? = null;
    var searchLayout: LinearLayoutCompat? = null;
    var searchTextInputLayout: TextInputLayout? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        Timber.d("onCreate ${javaClass.simpleName}")
    }
}