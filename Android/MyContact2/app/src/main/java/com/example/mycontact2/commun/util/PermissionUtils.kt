package com.example.mycontact2.commun.util

import android.app.Activity
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts


fun Activity.requestMultiplePermission(
    key: String,
    permissions: List<String>,
    onPermissionNotGranted: (() -> Unit)? = null,
    onPermissionGranted: (permissions: List<String>) -> Unit,
    onPermissionDenied: (permissions: List<String>) -> Unit
): ActivityResultLauncher<Array<String>>? {
    if (this !is ComponentActivity) {
        Log.d("PermissionUtils", "Your activity does not support this action")
        return null
    } else {
        return this.activityResultRegistry.register(
            key,
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            it.forEach { (key, value) ->
                if (!permissions.contains(key)) {
                    onPermissionNotGranted?.invoke()
                }
                val grantedPermission = mutableListOf<String>()
                val deniedPermission = mutableListOf<String>()
                permissions.forEach { permission ->
                    if (permission == key) {
                        if (value) {
                            grantedPermission.add(permission)
                        } else {
                            deniedPermission.add(permission)
                        }
                    }
                }
                if (grantedPermission.isNotEmpty()){
                    onPermissionGranted(grantedPermission)
                }
                if (deniedPermission.isNotEmpty()){
                    onPermissionDenied(deniedPermission)
                }
            }

        }
    }

}