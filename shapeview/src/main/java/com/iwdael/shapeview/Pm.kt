package com.iwdael.shapeview

import android.graphics.Path
import android.graphics.PathMeasure

/**
 * author : 段泽全(hacknife)
 * e-mail : hacknife@outlook.com
 * time   : 2019/8/5
 * desc   : MVVM
 * version: 1.0
 */
class Pm(
    vararg pair: Pair<Path, PathMeasure>
) : ArrayList<Pair<Path, PathMeasure>>() {
    init {
        addAll(pair)
    }
}