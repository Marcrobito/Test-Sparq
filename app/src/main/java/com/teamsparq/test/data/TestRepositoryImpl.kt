package com.teamsparq.test.data

import com.teamsparq.test.models.TestResponse
import com.teamsparq.test.models.toTestModel
import com.teamsparq.test.network.TestApi

class TestRepositoryImpl(private val testApi: TestApi) : TestRepository {
    override suspend fun fetchTestData(): TestResponse {
        return try {
            NetworkResponse.Success(testApi.fetchTestData().toTestModel())
        } catch (exception: Exception) {
            NetworkResponse.Error(exception)
        }
    }
}