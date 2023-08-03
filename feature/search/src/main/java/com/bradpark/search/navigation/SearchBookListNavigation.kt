package com.bradpark.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bradpark.domain.model.BookData
import com.bradpark.search.SearchBookListRoute

const val searchBookListNavigationRoute = "search_book_list_route"

fun NavGraphBuilder.searchBookListScreen(
    navigate: (BookData) -> Unit,
) {
    composable(
        route = searchBookListNavigationRoute,
    ) { _ ->
        SearchBookListRoute(navigate = navigate)
    }
}