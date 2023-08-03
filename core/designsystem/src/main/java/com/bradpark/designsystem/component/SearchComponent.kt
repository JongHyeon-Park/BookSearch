package com.bradpark.designsystem.component

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bradpark.designsystem.R

/**
 * 검색 바
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookSearchBar(onSearch:(String) -> Unit) {
    val context = LocalContext.current
    var text by rememberSaveable { mutableStateOf("") }
    var isToast by remember { mutableStateOf(false) }
    SearchBar(
        modifier = Modifier.fillMaxWidth(),
        query = text,
        active = false,
        onActiveChange = { },
        onQueryChange = {
            text = it
        },
        onSearch = {
            if (it.isNotEmpty()) {
                onSearch(it)
            }
        },
        placeholder = { Text(stringResource(R.string.hint_text)) },
        trailingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier.clickable {
                    if (text.isNotEmpty()) {
                        onSearch(text)
                    } else {
                        isToast = true
                    }
                })
        },
    ) {
    }
    if (isToast) {
        isToast = false
        Toast.makeText(
            context,
            stringResource(R.string.hint_text),
            Toast.LENGTH_SHORT
        ).show()
    }
}

/**
 * 검색 바 미리보기
 */
@Preview
@Composable
private fun PreviewBookSearchBar() {
    BookSearchBar(onSearch = {})
}