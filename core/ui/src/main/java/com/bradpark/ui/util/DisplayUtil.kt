package com.bradpark.ui.util

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * 화면 해상도 1/3 의 높이
 * 요구 사항
 */
@Composable
fun pagingItemHeight(): Float {
    val context: Context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val dpHeight = displayMetrics.heightPixels / displayMetrics.density
    return dpHeight.div(3)
}