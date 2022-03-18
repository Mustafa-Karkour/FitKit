package com.example.fitkit

import android.widget.Toast
import com.google.ar.core.exceptions.*
import com.google.ar.sceneform.ux.ArFragment

class MyArFragment : ArFragment() {
    override fun onArUnavailableException(sessionException: UnavailableException) {
        val message: String
        when (sessionException) {
            is UnavailableArcoreNotInstalledException -> message = "Please install ARCore"
            is UnavailableApkTooOldException -> message = "Please upgrade ARCore"
            is UnavailableSdkTooOldException -> message = "Please upgrade the app"
            is UnavailableDeviceNotCompatibleException -> message = "The current device department does not support AR"
            else -> {
                message = "Failed to create AR session, please check model adaptation, arcore version and system version"
            }
        }
        Toast.makeText(context,message, Toast.LENGTH_LONG)
    }
}