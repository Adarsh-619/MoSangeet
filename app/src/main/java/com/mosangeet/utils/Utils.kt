package com.mosangeet.utils

import android.app.Application
import com.mosangeet.App
import com.mosangeet.R
import com.mosangeet.interactors.runOnUiThread
import es.dmoral.toasty.Toasty

object Utils {
    @JvmStatic
    fun getApp(): Application {
        return App.getInstance()
    }

    fun hasFlags(flags: Int, flagsNeedToCheck: Int): Boolean {
        return flags and flagsNeedToCheck != 0
    }

    fun showGeneralErrorToast() {
        runOnUiThread {
            Toasty.normal(getApp(), R.string.str_error_general).show()
        }
    }
}