package com.teamsparq.test.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamsparq.test.data.NetworkResponse
import com.teamsparq.test.data.TestRepository
import com.teamsparq.test.models.TestResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: TestRepository) : ViewModel() {

    private val _state = MutableStateFlow<TestResponse>(NetworkResponse.NotInitialized)
    val state: StateFlow<TestResponse> get() = _state

    fun fetchData() {
        _state.value = NetworkResponse.Loading
        viewModelScope.launch {
            _state.value = repository.fetchTestData()
        }
    }
}