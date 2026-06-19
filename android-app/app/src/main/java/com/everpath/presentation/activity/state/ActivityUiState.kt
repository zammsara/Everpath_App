package com.everpath.presentation.activity.state

import com.everpath.domain.enums.ActivityStatus
import com.everpath.domain.model.Activity

data class ActivityUiState(

    val activities: List<Activity> = emptyList(),
    val selectedActivityId: String? = null,
    val isLoading: Boolean = true

) {
    val progress: Float
        get() {
            if (
                activities.isEmpty()
            ) {
                return 0f
            }
            val completedActivities =
                activities.count {
                    it.status ==
                            ActivityStatus.COMPLETED
                }
            return (
                    completedActivities.toFloat() / activities.size.toFloat()
                    )
        }

}