package com.bradpark.searchbook.navigation

import com.bradpark.detail.navigation.searchBookDetailNavigationRoute
import com.bradpark.search.navigation.searchBookListNavigationRoute

enum class TopLevelDestination(val route: String) {
    Search(searchBookListNavigationRoute),
    Detail(searchBookDetailNavigationRoute)
}