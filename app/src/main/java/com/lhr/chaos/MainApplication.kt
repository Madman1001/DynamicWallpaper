package com.lhr.chaos

import android.app.Application

/**
 * @author lhr
 * @date 2021/8/5
 * @des
 */
class MainApplication : Application() {
    private val tag = this::class.java.simpleName
    override fun onCreate() {
        super.onCreate()
    }
}