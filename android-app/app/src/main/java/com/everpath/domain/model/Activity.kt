package com.everpath.domain.model

import com.everpath.domain.enums.ActivityStatus

data class Activity(
    val id: String,
    val goalId: String,
    val title: String,
    val description: String,
    val status: ActivityStatus
)