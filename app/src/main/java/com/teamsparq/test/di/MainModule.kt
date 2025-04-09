package com.teamsparq.test.di

import com.teamsparq.test.data.TestRepository
import com.teamsparq.test.data.TestRepositoryImpl
import com.teamsparq.test.network.NetworkService
import com.teamsparq.test.network.TestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    fun providesHackerNewsApi() = NetworkService.service

    @Provides
    fun providesTestRepository(api: TestApi): TestRepository = TestRepositoryImpl(api)
}