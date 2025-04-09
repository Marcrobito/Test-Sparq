package com.teamsparq.test.network

import com.teamsparq.test.models.TestModelDto
import retrofit2.http.GET

interface TestApi {
    @GET("test.json")
    suspend fun fetchTestData():List<TestModelDto>
}