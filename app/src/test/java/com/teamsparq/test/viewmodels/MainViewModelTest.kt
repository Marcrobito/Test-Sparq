package com.teamsparq.test.viewmodels

import com.teamsparq.test.data.NetworkResponse
import com.teamsparq.test.data.TestRepository
import com.teamsparq.test.models.TestModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

private const val NETWORK_FAILURE = "network failure"
@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: TestRepository
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = MainViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state should be NotInitialized`() = runTest {
        assert(viewModel.state.value is NetworkResponse.NotInitialized)
    }

    @Test
    fun `fetchData should emit Loading and then Success`() = runTest {
        val fakeData = listOf(TestModel("Title", "Desc"))
        coEvery { repository.fetchTestData() } returns NetworkResponse.Success(fakeData)

        viewModel.fetchData()
        advanceUntilIdle()

        assert(viewModel.state.value is NetworkResponse.Success)
        val success = viewModel.state.value as NetworkResponse.Success
        assert(success.data == fakeData)
    }

    @Test
    fun `fetchData should emit Error when repository throws`() = runTest {
        val exception = RuntimeException(NETWORK_FAILURE)
        coEvery { repository.fetchTestData() } returns NetworkResponse.Error(exception)

        viewModel.fetchData()
        advanceUntilIdle()

        assert(viewModel.state.value is NetworkResponse.Error)
        val error = viewModel.state.value as NetworkResponse.Error
        assert(error.error.message == NETWORK_FAILURE)
    }
}