package com.teamsparq.test.ui.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamsparq.test.R
import com.teamsparq.test.data.NetworkResponse
import com.teamsparq.test.models.TestModel
import com.teamsparq.test.ui.components.FullSizeColumnContainer
import com.teamsparq.test.ui.components.Item
import com.teamsparq.test.viewmodels.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel(), padding: PaddingValues) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }

    when (state) {
        is NetworkResponse.NotInitialized -> {
            FullSizeColumnContainer {
                Text(stringResource(R.string.waiting))
            }
        }

        is NetworkResponse.Loading -> {
            FullSizeColumnContainer {
                CircularProgressIndicator()
            }
        }

        is NetworkResponse.Success -> {
            val data = (state as NetworkResponse.Success<List<TestModel>>).data
            LazyColumn(contentPadding = padding) {
                items(data) { testModel ->
                    Item(testModel)
                }
            }
        }

        is NetworkResponse.Error -> {
            val error = (state as NetworkResponse.Error).error
            FullSizeColumnContainer {
                Text(stringResource(id = R.string.error_message, error.message ?: ""))
            }
        }
    }
}