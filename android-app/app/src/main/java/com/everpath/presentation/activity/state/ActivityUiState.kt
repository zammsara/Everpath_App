package com.everpath.presentation.activity.state

import com.everpath.domain.model.Activity

data class ActivityUiState(

    val activities: List<Activity> = emptyList(),

    val selectedActivityId: String? = null,

    val isLoading: Boolean = true

)