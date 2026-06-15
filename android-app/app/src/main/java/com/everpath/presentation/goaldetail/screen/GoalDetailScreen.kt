package com.everpath.presentation.goaldetail.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.everpath.EverpathApplication
import com.everpath.presentation.goaldetail.viewmodel.GoalDetailViewModel
import com.everpath.presentation.goaldetail.viewmodel.GoalDetailViewModelFactory

@Composable
fun GoalDetailScreen(
    goalId: String
) {

    val application =
        LocalContext.current.applicationContext
                as EverpathApplication

    val factory = remember {

        GoalDetailViewModelFactory(
            getGoalNodeByIdUseCase =
                application
                    .appContainer
                    .getGoalNodeByIdUseCase
        )

    }

    val viewModel: GoalDetailViewModel =
        viewModel(
            factory = factory
        )

    val goal =
        viewModel.goal
            .collectAsStateWithLifecycle()

    LaunchedEffect(goalId) {

        viewModel.loadGoal(
            goalId
        )

    }

    if (goal.value == null) {

        CircularProgressIndicator()

        return

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = goal.value!!.title,
            style =
                MaterialTheme
                    .typography
                    .headlineMedium
        )

        Text(
            text = goal.value!!.description,
            modifier = Modifier.padding(
                top = 8.dp
            )
        )

        Text(
            text =
                "Área: ${goal.value!!.lifeArea}",
            modifier = Modifier.padding(
                top = 16.dp
            )
        )

        Text(
            text =
                "Estado: ${goal.value!!.status}"
        )

        Text(
            text =
                "Actividades: ${goal.value!!.activities.size}"
        )

    }

}