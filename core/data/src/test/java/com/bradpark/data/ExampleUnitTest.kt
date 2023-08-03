package com.bradpark.data

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
//@HiltAndroidTest
class ExampleUnitTest {
//
//    @Inject
//    lateinit var datasource: BookDatasource

    @Test
    fun addition_isCorrect() {
//        val pagingSource = BookPagingSource(datasource, BookParameter("하하", SearchSortType.ACCURACY, 1, 10))
        assertEquals(4, 2 + 2)
    }
}