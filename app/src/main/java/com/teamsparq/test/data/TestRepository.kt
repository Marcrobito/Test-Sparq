package com.teamsparq.test.data

import com.teamsparq.test.models.TestResponse

interface TestRepository  {
    suspend fun fetchTestData():TestResponse
}