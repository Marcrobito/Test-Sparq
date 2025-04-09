package com.teamsparq.test.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TestModelDto(
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String
)

