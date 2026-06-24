package com.everpath.presentation.activitydetail.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.everpath.EverpathApplication
import com.everpath.presentation.activity.components.EditActivityDialog
import com.everpath.presentation.activitydetail.viewmodel.ActivityDetailViewModel
import com.everpath.presentation.activitydetail.viewmodel.ActivityDetailViewModelFactory
import com.everpath.domain.enums.ActivityStatus

@Composable
fun ActivityDetailScreen(
    activityId: String,
    navController: NavHostController
) {

    val application =
        LocalContext.current.applicationContext
                as EverpathApplication

    val factory = remember {

        ActivityDetailViewModelFactory(
            getActivityByIdUseCase =
                application
                    .appContainer
                    .getActivityByIdUseCase,

            updateActivityUseCase =
                application
                    .appContainer
                    .updateActivityUseCase,

            completeActivityWithAchievementsUseCase =
                application
                    .appContainer
                    .completeActivityWithAchievementsUseCase,

            deleteActivityUseCase =
                application
                    .appContainer
                    .deleteActivityUseCase
        )
    }

    val viewModel: ActivityDetailViewModel =
        viewModel(
            factory = factory
        )

    val showEditDialog =
        remember {
            mutableStateOf(false)
        }

    val showDeleteDialog =
        remember {
            mutableStateOf(false)
        }

    val activity =
        viewModel.activity
            .collectAsStateWithLifecycle()

    LaunchedEffect(activityId) {

        viewModel.loadActivity(
            activityId
        )

    }

    if (activity.value == null) {

        CircularProgressIndicator()

        return

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = activity.value!!.title,
            style =
                MaterialTheme
                    .typography
                    .headlineMedium
        )

        Text(
            text = activity.value!!.description,
            modifier = Modifier.padding(
                top = 8.dp
            )
        )

        val statusText = when (
            activity.value!!.status
        ) {

            ActivityStatus.PENDING ->
                "Pendiente"

            ActivityStatus.IN_PROGRESS ->
                "En progreso"

            ActivityStatus.COMPLETED ->
                "Completada"

        }

        Text(
            text = "Estado: $statusText",
            modifier = Modifier.padding(
                top = 16.dp
            )
        )

        Button(
            onClick = {
                showEditDialog.value = true
            }
        ) {

            Text("Editar")

        }

        Button(
            onClick = {
                showDeleteDialog.value = true
            }
        ) {

            Text("Eliminar")

        }

    }

    if (
        showEditDialog.value &&
        activity.value != null
    ) {

        EditActivityDialog(

            initialTitle =
                activity.value!!.title,

            initialDescription =
                activity.value!!.description,

            initialStatus =
                activity.value!!.status,

            onDismiss = {

                showEditDialog.value = false

            },

            onSave = { title, description, status ->

                viewModel.updateActivity(

                    title = title,

                    description = description,

                    status = status

                )

                showEditDialog.value = false

            }

        )

    }

    if (showDeleteDialog.value) {

        AlertDialog(

            onDismissRequest = {
                showDeleteDialog.value = false
            },

            title = {
                Text("Eliminar Actividad")
            },

            text = {
                Text(
                    "¿Deseas eliminar esta actividad?"
                )
            },

            confirmButton = {

                TextButton(

                    onClick = {

                        viewModel.deleteActivity {

                            navController.popBackStack()

                        }

                    }

                ) {

                    Text("Eliminar")

                }

            },

            dismissButton = {

                TextButton(

                    onClick = {
                        showDeleteDialog.value = false
                    }

                ) {

                    Text("Cancelar")

                }

            }

        )

    }


}
