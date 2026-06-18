package com.everpath.presentation.activity.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.everpath.domain.model.Activity

@Composable
fun ActivityList(
    activities: List<Activity>,
    onActivityClick: (String) -> Unit
) {

    Column {

        activities.forEach { activity ->

            ActivityCard(

                activity = activity,

                onClick = {

                    onActivityClick(
                        activity.id
                    )

                }

            )

        }

    }

}