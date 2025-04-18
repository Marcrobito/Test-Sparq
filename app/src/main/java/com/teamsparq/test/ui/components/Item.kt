package com.teamsparq.test.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamsparq.test.models.TestModel

@Composable
fun Item(testModel: TestModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(testModel.title, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(testModel.description, style = MaterialTheme.typography.bodyMedium)
    }
}