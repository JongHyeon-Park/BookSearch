package com.bradpark.data

import com.bradpark.domain.datasource.BookDatasource
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
class ExampleInstrumentedTest {

    @BindValue
    lateinit var datasource: BookDatasource
//
//    @Inject
//    lateinit var repository: BookSearchRepository
//
//    @Inject
//    lateinit var retrofitNetworkApi: RetrofitNetworkApi

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun loadPagingTest() {
//        val pagingSource = BookPagingSource(datasource, BookParameter("하하", SearchSortType.ACCURACY, 1, 10))
//        val pager = TestPager(PagingConfig(pageSize = 30), pagingSource)
//        val result = runBlocking {
//            pager.refresh() as PagingSource.LoadResult.Page
//        }
//        assert(result.data.size == 10)

    }
}