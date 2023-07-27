package com.example.core.datastore.datastore

data class ChangeListVersions(
    val topicVersion: Int = -1,
    val newsResourceVersion: Int = -1,
)