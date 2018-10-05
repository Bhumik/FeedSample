package com.finalhints.mymandirdemo.view.ui.base

import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    protected fun showError(message: String) {

    }

    fun toast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }
}
