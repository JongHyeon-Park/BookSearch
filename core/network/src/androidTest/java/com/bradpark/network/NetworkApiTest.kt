package com.bradpark.network

import com.bradpark.network.retrofit.RetrofitNetworkApi
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Retrofit Network Test
 */
@HiltAndroidTest
class NetworkApiTest {

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var retrofitNetworkApi: RetrofitNetworkApi

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    /**
     * Inject 가 제대로 되는지 확인
     */
    @Test
    fun networkNullTest() {
        assertNotNull(retrofit)
        assertNotNull(retrofitNetworkApi)
    }

    /**
     * URL이 정식적으로 적용되었는지 확인
     */
    @Test
    fun networkUrlTest() {
        assert(retrofit.baseUrl().toString() == BuildConfig.KAKAO_URL + "/")
    }

    /**
     * API 호출이 정상적으로 되는지 확인
     */
    @Test
    fun networkApiCallTest() {
        val response = runBlocking {
            retrofitNetworkApi.getBookSearchResponse("하하", "accuracy", 1, 10)
        }
        assert(response.documents.size == 10)
    }

}