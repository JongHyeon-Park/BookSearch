package com.bradpark.detail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bradpark.detail.SearchBookDetailRoute
import com.bradpark.domain.model.BookData
import kotlinx.serialization.json.Json

internal const val bookData = "bookData"

const val searchBookDetailNavigationRoute = "search_book_detail_route"

fun NavGraphBuilder.searchBookDetailScreen() {
    composable(
        route = "$searchBookDetailNavigationRoute/{$bookData}"
    ) { backStackEntry ->
        val bookData = backStackEntry.arguments?.getString(bookData)
        bookData?.apply {
            val argBookData = Json.decodeFromString(BookData.serializer(),this)
            SearchBookDetailRoute(bookData = argBookData)
        }

    }
}