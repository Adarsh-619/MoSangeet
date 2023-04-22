package com.mosangeet.common

import androidx.annotation.WorkerThread
import com.mosangeet.common.MediaManager
import com.mosangeet.interactors.AppExecutors

object AppStartup {
    @JvmStatic
    fun onAppStartup() {
        AppExecutors.single().execute {
            initDataAsyncOnStartUp()
        }
    }

    @WorkerThread
    private fun initDataAsyncOnStartUp() {
        MediaManager.loadMediaIfNeeded()
    }
}