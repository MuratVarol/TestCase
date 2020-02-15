package com.varol.testcase.internal.util

import android.content.Context
import com.varol.testcase.internal.extension.networkInfo

class NetworkHandler(private val context: Context) {
    val isConnected: Boolean
        get() = context.networkInfo?.isConnected ?: false
}