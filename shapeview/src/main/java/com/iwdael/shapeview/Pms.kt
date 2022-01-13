package com.iwdael.shapeview

import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure

/**
 * author : 段泽全(hacknife)
 * e-mail : hacknife@outlook.com
 * time   : 2019/8/5
 * desc   : MVVM
 * version: 1.0
 */
class Pms(size: Int) : ArrayList<Pm>() {
    init {
        for (index in 0 until size) add(Pm(Path(), Paint(), PathMeasure()))
    }
}