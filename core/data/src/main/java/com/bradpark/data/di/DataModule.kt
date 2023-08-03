package com.bradpark.data.di

import com.bradpark.data.datasource.BookDatasourceImpl
import com.bradpark.data.repository.BookSearchRepositoryImpl
import com.bradpark.domain.datasource.BookDatasource
import com.bradpark.domain.repository.BookSearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindBookSearchDatasource(
        bookDatasource: BookDatasourceImpl
    ): BookDatasource

    @Binds
    fun bindBookSearchRepository(
        bookSearchRepository: BookSearchRepositoryImpl
    ): BookSearchRepository
}