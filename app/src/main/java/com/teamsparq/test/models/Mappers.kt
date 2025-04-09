package com.teamsparq.test.models

fun TestModelDto.toTestModel() = TestModel(title, description)

fun List<TestModelDto>.toTestModel() = map { it.toTestModel() }