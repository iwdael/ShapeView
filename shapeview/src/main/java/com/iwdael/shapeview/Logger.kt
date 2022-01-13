package com.iwdael.shapeview

import android.util.Log

/**
 * author : Iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * version: 1.0
 */
object Logger {
    fun v(tag: String, content: String) {
        Log.v("dzq-${tag}", content)
    }
}