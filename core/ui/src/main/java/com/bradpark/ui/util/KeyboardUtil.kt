package com.bradpark.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

/**
 * 키보드 숨기기 & 포커스 제거
 */
@Composable
fun HideKeyboard() {
    HideKeyboardController()
    ClearFocus()
}

/**
 * 키보드 숨기기
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun HideKeyboardController() {
    val keyboardController = LocalSoftwareKeyboardController.current
    keyboardController?.hide()
}

/**
 * 포커스 제거
 */
@Composable
private fun ClearFocus() {
    val focusManager = LocalFocusManager.current
    focusManager.clearFocus()
}