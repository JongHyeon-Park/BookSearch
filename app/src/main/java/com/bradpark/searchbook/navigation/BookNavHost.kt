package com.bradpark.searchbook.navigation

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.bradpark.detail.navigation.searchBookDetailNavigationRoute
import com.bradpark.detail.navigation.searchBookDetailScreen
import com.bradpark.domain.model.BookData
import com.bradpark.search.navigation.searchBookListScreen
import kotlinx.serialization.json.Json

@Composable
fun BookNavGraph() {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = TopLevelDestination.Search.route,
    ) {
        searchBookListScreen(
            navigate = { bookData ->
                val bookData = Uri.encode(Json.encodeToString(BookData.serializer(),bookData))
                navController.navigate("${TopLevelDestination.Detail.route}/$bookData")
            }
        )
        searchBookDetailScreen()
    }
}