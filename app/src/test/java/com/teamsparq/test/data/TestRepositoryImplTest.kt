package com.teamsparq.test.data

import com.teamsparq.test.models.TestModelDto
import com.teamsparq.test.models.toTestModel
import com.teamsparq.test.network.TestApi
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


private const val EXCEPTION_MESSAGE = "Something went wrong"
class TestRepositoryImplTest {

    private lateinit var repository: TestRepositoryImpl
    private lateinit var api: TestApi

    @Before
    fun setUp() {
        api = mockk()
        repository = TestRepositoryImpl(api)
    }

    @Test
    fun `fetchTestData returns Success when API call is successful`() = runTest {

        val fakeList = listOf(TestModelDto("Title", "Description"))
        coEvery { api.fetchTestData() } returns fakeList

        val result = repository.fetchTestData()

        assertTrue(result is NetworkResponse.Success)
        val data = (result as NetworkResponse.Success).data
        assertEquals(fakeList.toTestModel(), data)
    }

    @Test
    fun `fetchTestData returns Error when API throws exception`() = runTest {

        val exception = RuntimeException(EXCEPTION_MESSAGE)
        coEvery { api.fetchTestData() } throws exception

        val result = repository.fetchTestData()

        assertTrue(result is NetworkResponse.Error)
        val error = (result as NetworkResponse.Error).error
        assertEquals(EXCEPTION_MESSAGE, error.message)
    }
}