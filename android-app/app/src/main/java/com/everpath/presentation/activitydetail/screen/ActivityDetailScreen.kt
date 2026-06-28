package com.everpath.presentation.activitydetail.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.everpath.EverpathApplication
import com.everpath.R
import com.everpath.domain.enums.ActivityStatus
import com.everpath.domain.model.Activity
import com.everpath.presentation.activitydetail.viewmodel.ActivityDetailViewModel
import com.everpath.presentation.activitydetail.viewmodel.ActivityDetailViewModelFactory
import com.everpath.ui.theme.EverpathBackground
import com.everpath.ui.theme.EverpathBorder
import com.everpath.ui.theme.EverpathDialogSurface
import com.everpath.ui.theme.EverpathError
import com.everpath.ui.theme.EverpathPrimary
import com.everpath.ui.theme.EverpathStatusActiveContainer
import com.everpath.ui.theme.EverpathStatusCompleted
import com.everpath.ui.theme.EverpathStatusCompletedContainer
import com.everpath.ui.theme.EverpathSurface
import com.everpath.ui.theme.EverpathSurfaceSoft
import com.everpath.ui.theme.EverpathSurfaceVariant
import com.everpath.ui.theme.EverpathTextDisabled
import com.everpath.ui.theme.EverpathTextPrimary
import com.everpath.ui.theme.EverpathTextSecondary
import com.everpath.ui.theme.EverpathWhite

@Composable
fun ActivityDetailScreen(
    activityId: String,
    navController: NavHostController
) {

    val application =
        LocalContext.current.applicationContext
                as EverpathApplication

    val factory =
        remember {
            ActivityDetailViewModelFactory(
                getActivityByIdUseCase =
                    application
                        .appContainer
                        .getActivityByIdUseCase,

                updateActivityUseCase =
                    application
                        .appContainer
                        .updateActivityUseCase,

                deleteActivityUseCase =
                    application
                        .appContainer
                        .deleteActivityUseCase,

                fetchUserProgressUseCase =
                    application
                        .appContainer
                        .fetchUserProgressUseCase
            )
        }

    val viewModel: ActivityDetailViewModel =
        viewModel(
            factory = factory
        )

    val activityState =
        viewModel
            .activity
            .collectAsStateWithLifecycle()

    val showEditDialog =
        remember {
            mutableStateOf(false)
        }

    val showDeleteDialog =
        remember {
            mutableStateOf(false)
        }

    LaunchedEffect(activityId) {
        viewModel.loadActivity(activityId)
    }

    val currentActivity =
        activityState.value

    if (currentActivity == null) {

        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(EverpathBackground),
            contentAlignment =
                Alignment.Center
        ) {
            CircularProgressIndicator(
                color = EverpathPrimary
            )
        }

        return
    }

    Scaffold(
        containerColor = EverpathBackground
    ) { paddingValues ->

        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 16.dp,
                        bottom = 32.dp
                    )
        ) {

            ActivityHeader(
                title = currentActivity.title,
                onBackClick = {
                    navController.popBackStack()
                }
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            ActivityHeroCard(
                activity = currentActivity
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            ActionCard(
                title = "Editar actividad",
                description = "Modifica el título, descripción o estado.",
                icon = Icons.Default.Edit,
                containerColor = EverpathStatusActiveContainer,
                contentColor = EverpathPrimary,
                onClick = {
                    showEditDialog.value = true
                }
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            ActionCard(
                title = "Eliminar actividad",
                description = "Quita esta actividad de tu meta.",
                icon = Icons.Default.Delete,
                containerColor =
                    EverpathError.copy(
                        alpha = 0.14f
                    ),
                contentColor = EverpathError,
                onClick = {
                    showDeleteDialog.value = true
                }
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            MotivationCard()

        }

    }

    if (showEditDialog.value) {

        EditActivityDialog(
            initialTitle = currentActivity.title,
            initialDescription = currentActivity.description,
            initialStatus = currentActivity.status,

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

            containerColor = EverpathDialogSurface,
            shape = RoundedCornerShape(30.dp),

            title = {
                Text(
                    text = "Eliminar actividad",
                    color = EverpathTextPrimary,
                    fontWeight = FontWeight.Bold
                )
            },

            text = {
                Text(
                    text = "¿Deseas eliminar esta actividad?",
                    color = EverpathTextSecondary
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

                    Text(
                        text = "Eliminar",
                        color = EverpathError,
                        fontWeight = FontWeight.Bold
                    )

                }

            },

            dismissButton = {

                TextButton(
                    onClick = {
                        showDeleteDialog.value = false
                    }
                ) {

                    Text(
                        text = "Cancelar",
                        color = EverpathPrimary,
                        fontWeight = FontWeight.SemiBold
                    )

                }

            }
        )

    }

}

@Composable
private fun ActivityHeader(
    title: String,
    onBackClick: () -> Unit
) {

    Row(
        modifier =
            Modifier.fillMaxWidth(),
        verticalAlignment =
            Alignment.CenterVertically
    ) {

        IconButton(
            onClick = onBackClick,
            modifier =
                Modifier
                    .size(42.dp)
                    .background(
                        color = EverpathSurface,
                        shape = CircleShape
                    )
        ) {

            Icon(
                imageVector =
                    Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
                tint = EverpathPrimary,
                modifier =
                    Modifier.size(22.dp)
            )

        }

        Spacer(
            modifier = Modifier.width(12.dp)
        )

        Column(
            modifier =
                Modifier.weight(1f)
        ) {

            Text(
                text = title,
                color = EverpathTextPrimary,
                style =
                    MaterialTheme
                        .typography
                        .titleLarge,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.ExtraBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = "Detalle de actividad",
                color = EverpathTextSecondary,
                style =
                    MaterialTheme
                        .typography
                        .bodyMedium,
                modifier =
                    Modifier.padding(
                        top = 2.dp
                    )
            )

        }

    }

}

@Composable
private fun ActivityHeroCard(
    activity: Activity
) {

    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(32.dp),
                    clip = false
                ),
        shape = RoundedCornerShape(32.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = EverpathSurface
            )
    ) {

        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
        ) {

            Row(
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Box(
                    modifier =
                        Modifier
                            .size(88.dp)
                            .background(
                                color =
                                    activity
                                        .status
                                        .toContainerColor(),
                                shape = CircleShape
                            ),
                    contentAlignment =
                        Alignment.Center
                ) {

                    Image(
                        painter =
                            painterResource(
                                id =
                                    activity
                                        .status
                                        .toIconRes()
                            ),
                        contentDescription =
                            activity
                                .status
                                .toSpanishName(),
                        modifier =
                            Modifier.size(52.dp),
                        contentScale =
                            ContentScale.Fit
                    )

                }

                Spacer(
                    modifier = Modifier.width(18.dp)
                )

                Column(
                    modifier =
                        Modifier.weight(1f)
                ) {

                    Text(
                        text = "Estado",
                        color = EverpathTextSecondary,
                        style =
                            MaterialTheme
                                .typography
                                .titleSmall,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    ActivityStatusChip(
                        status = activity.status
                    )

                }

            }

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(
                            EverpathBorder.copy(
                                alpha = 0.65f
                            )
                        )
            )

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            Text(
                text = "Descripción",
                color = EverpathTextSecondary,
                style =
                    MaterialTheme
                        .typography
                        .titleSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text =
                    activity.description.ifBlank {
                        "Sin descripción"
                    },
                color = EverpathTextPrimary,
                style =
                    MaterialTheme
                        .typography
                        .bodyLarge
            )

        }

    }

}

@Composable
private fun ActivityStatusChip(
    status: ActivityStatus
) {

    Surface(
        color = status.toContainerColor(),
        shape = RoundedCornerShape(50.dp),
        border =
            BorderStroke(
                width = 1.dp,
                color =
                    status
                        .toColor()
                        .copy(
                            alpha = 0.28f
                        )
            )
    ) {

        Row(
            modifier =
                Modifier.padding(
                    horizontal = 14.dp,
                    vertical = 9.dp
                ),
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Image(
                painter =
                    painterResource(
                        id = status.toIconRes()
                    ),
                contentDescription =
                    status.toSpanishName(),
                modifier =
                    Modifier.size(22.dp),
                contentScale =
                    ContentScale.Fit
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = status.toSpanishName(),
                color = status.toColor(),
                style =
                    MaterialTheme
                        .typography
                        .titleSmall,
                fontWeight = FontWeight.ExtraBold
            )

        }

    }

}

@Composable
private fun ActionCard(
    title: String,
    description: String,
    icon: ImageVector,
    containerColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {

    Surface(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                },
        color = containerColor,
        shape = RoundedCornerShape(26.dp)
    ) {

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Box(
                modifier =
                    Modifier
                        .size(56.dp)
                        .background(
                            color =
                                EverpathWhite.copy(
                                    alpha = 0.50f
                                ),
                            shape = CircleShape
                        ),
                contentAlignment =
                    Alignment.Center
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = contentColor,
                    modifier =
                        Modifier.size(28.dp)
                )

            }

            Spacer(
                modifier = Modifier.width(16.dp)
            )

            Column(
                modifier =
                    Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    color = EverpathTextPrimary,
                    style =
                        MaterialTheme
                            .typography
                            .titleMedium,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = description,
                    color = EverpathTextSecondary,
                    style =
                        MaterialTheme
                            .typography
                            .bodyMedium,
                    modifier =
                        Modifier.padding(
                            top = 3.dp
                        )
                )

            }

        }

    }

}

@Composable
private fun MotivationCard() {

    Surface(
        modifier =
            Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color =
                        EverpathPrimary.copy(
                            alpha = 0.25f
                        ),
                    shape = RoundedCornerShape(28.dp)
                ),
        color = EverpathSurfaceSoft,
        shape = RoundedCornerShape(28.dp)
    ) {

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(22.dp),
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Box(
                modifier =
                    Modifier
                        .size(54.dp)
                        .background(
                            color =
                                EverpathPrimary.copy(
                                    alpha = 0.12f
                                ),
                            shape = CircleShape
                        ),
                contentAlignment =
                    Alignment.Center
            ) {

                Text(
                    text = "✦",
                    color = EverpathPrimary,
                    style =
                        MaterialTheme
                            .typography
                            .headlineSmall,
                    fontWeight = FontWeight.Bold
                )

            }

            Spacer(
                modifier = Modifier.width(16.dp)
            )

            Text(
                text = "Cada pequeño paso te acerca a tu mejor versión.",
                color = EverpathPrimary,
                style =
                    MaterialTheme
                        .typography
                        .titleMedium,
                fontWeight = FontWeight.Bold
            )

        }

    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun EditActivityDialog(
    initialTitle: String,
    initialDescription: String,
    initialStatus: ActivityStatus,
    onDismiss: () -> Unit,
    onSave: (
        String,
        String,
        ActivityStatus
    ) -> Unit
) {

    val title =
        remember {
            mutableStateOf(initialTitle)
        }

    val description =
        remember {
            mutableStateOf(initialDescription)
        }

    val status =
        remember {
            mutableStateOf(initialStatus)
        }

    val scrollState =
        rememberScrollState()

    val isValid =
        title.value
            .trim()
            .isNotEmpty()

    AlertDialog(
        onDismissRequest = onDismiss,

        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),

        properties =
            DialogProperties(
                usePlatformDefaultWidth = false
            ),

        containerColor = EverpathDialogSurface,

        shape =
            RoundedCornerShape(34.dp),

        title = {

            Row(
                modifier =
                    Modifier.fillMaxWidth(),
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Box(
                    modifier =
                        Modifier
                            .size(68.dp)
                            .background(
                                color =
                                    status
                                        .value
                                        .toContainerColor(),
                                shape = CircleShape
                            ),
                    contentAlignment =
                        Alignment.Center
                ) {

                    Image(
                        painter =
                            painterResource(
                                id =
                                    status
                                        .value
                                        .toIconRes()
                            ),
                        contentDescription =
                            status
                                .value
                                .toSpanishName(),
                        modifier =
                            Modifier.size(42.dp),
                        contentScale =
                            ContentScale.Fit
                    )

                }

                Spacer(
                    modifier = Modifier.width(14.dp)
                )

                Column(
                    modifier =
                        Modifier.weight(1f)
                ) {

                    Text(
                        text = "Editar actividad",
                        color = EverpathTextPrimary,
                        style =
                            MaterialTheme
                                .typography
                                .titleLarge,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Text(
                        text = "Actualiza los detalles de tu acción",
                        color = EverpathTextSecondary,
                        style =
                            MaterialTheme
                                .typography
                                .bodySmall
                    )

                }

            }

        },

        text = {

            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .verticalScroll(scrollState)
            ) {

                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(
                                EverpathBorder.copy(
                                    alpha = 0.55f
                                )
                            )
                )

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                Text(
                    text = "Título",
                    color = EverpathTextPrimary,
                    style =
                        MaterialTheme
                            .typography
                            .titleSmall,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = title.value,
                    onValueChange = {
                        title.value = it
                    },
                    placeholder = {
                        Text("Nombre de la actividad")
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(20.dp),
                    colors =
                        OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = EverpathPrimary,
                            unfocusedBorderColor = EverpathBorder,
                            focusedContainerColor = EverpathSurfaceSoft,
                            unfocusedContainerColor = EverpathSurfaceSoft,
                            focusedLabelColor = EverpathPrimary,
                            unfocusedLabelColor = EverpathTextSecondary,
                            cursorColor = EverpathPrimary,
                            focusedPlaceholderColor =
                                EverpathTextSecondary.copy(
                                    alpha = 0.65f
                                ),
                            unfocusedPlaceholderColor =
                                EverpathTextSecondary.copy(
                                    alpha = 0.65f
                                )
                        ),
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(
                    text = "Descripción",
                    color = EverpathTextPrimary,
                    style =
                        MaterialTheme
                            .typography
                            .titleSmall,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = description.value,
                    onValueChange = {
                        description.value = it
                    },
                    placeholder = {
                        Text("Describe esta actividad...")
                    },
                    minLines = 3,
                    maxLines = 4,
                    shape = RoundedCornerShape(20.dp),
                    colors =
                        OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = EverpathPrimary,
                            unfocusedBorderColor = EverpathBorder,
                            focusedContainerColor = EverpathSurfaceSoft,
                            unfocusedContainerColor = EverpathSurfaceSoft,
                            focusedLabelColor = EverpathPrimary,
                            unfocusedLabelColor = EverpathTextSecondary,
                            cursorColor = EverpathPrimary,
                            focusedPlaceholderColor =
                                EverpathTextSecondary.copy(
                                    alpha = 0.65f
                                ),
                            unfocusedPlaceholderColor =
                                EverpathTextSecondary.copy(
                                    alpha = 0.65f
                                )
                        ),
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                )

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                SelectedActivityStatusPreview(
                    status = status.value
                )

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                Text(
                    text = "Estado",
                    color = EverpathTextPrimary,
                    style =
                        MaterialTheme
                            .typography
                            .titleSmall,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Selecciona cómo va esta actividad.",
                    color = EverpathTextSecondary,
                    style =
                        MaterialTheme
                            .typography
                            .bodySmall,
                    modifier =
                        Modifier.padding(
                            top = 2.dp
                        )
                )

                FlowRow(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                    horizontalArrangement =
                        Arrangement.spacedBy(10.dp),
                    verticalArrangement =
                        Arrangement.spacedBy(10.dp)
                ) {

                    ActivityStatus.entries.forEach { option ->

                        ActivityStatusOption(
                            status = option,
                            selected =
                                status.value == option,
                            onClick = {
                                status.value = option
                            }
                        )

                    }

                }

            }

        },

        confirmButton = {

            Button(
                enabled = isValid,
                onClick = {

                    onSave(
                        title.value.trim(),
                        description.value.trim(),
                        status.value
                    )

                },
                shape = RoundedCornerShape(24.dp),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = EverpathPrimary,
                        contentColor = EverpathWhite,
                        disabledContainerColor = EverpathSurfaceVariant,
                        disabledContentColor = EverpathTextDisabled
                    ),
                modifier =
                    Modifier.height(48.dp)
            ) {

                Text(
                    text = "Guardar",
                    fontWeight = FontWeight.Bold
                )

            }

        },

        dismissButton = {

            TextButton(
                onClick = onDismiss
            ) {

                Text(
                    text = "Cancelar",
                    color = EverpathPrimary,
                    fontWeight = FontWeight.SemiBold
                )

            }

        }

    )

}

@Composable
private fun SelectedActivityStatusPreview(
    status: ActivityStatus
) {

    Surface(
        modifier =
            Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(24.dp),
                    clip = false
                ),
        color = status.toContainerColor(),
        shape = RoundedCornerShape(24.dp)
    ) {

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 14.dp
                    ),
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Box(
                modifier =
                    Modifier
                        .size(48.dp)
                        .background(
                            color =
                                status
                                    .toColor()
                                    .copy(
                                        alpha = 0.10f
                                    ),
                            shape = CircleShape
                        ),
                contentAlignment =
                    Alignment.Center
            ) {

                Image(
                    painter =
                        painterResource(
                            id = status.toIconRes()
                        ),
                    contentDescription =
                        status.toSpanishName(),
                    modifier =
                        Modifier.size(30.dp),
                    contentScale =
                        ContentScale.Fit
                )

            }

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Column(
                modifier =
                    Modifier.weight(1f)
            ) {

                Text(
                    text = "Estado seleccionado",
                    color = EverpathTextSecondary,
                    style =
                        MaterialTheme
                            .typography
                            .labelMedium
                )

                Text(
                    text = status.toSpanishName(),
                    color = status.toColor(),
                    style =
                        MaterialTheme
                            .typography
                            .titleMedium,
                    fontWeight = FontWeight.ExtraBold
                )

            }

        }

    }

}

@Composable
private fun ActivityStatusOption(
    status: ActivityStatus,
    selected: Boolean,
    onClick: () -> Unit
) {

    val backgroundColor =
        if (selected) {
            status.toContainerColor()
        } else {
            EverpathSurfaceSoft
        }

    val borderColor =
        if (selected) {
            status.toColor()
        } else {
            EverpathBorder
        }

    val textColor =
        if (selected) {
            status.toColor()
        } else {
            EverpathTextPrimary
        }

    Surface(
        modifier =
            Modifier
                .clickable {
                    onClick()
                }
                .border(
                    width =
                        if (selected) {
                            2.dp
                        } else {
                            1.dp
                        },
                    color = borderColor,
                    shape = RoundedCornerShape(50.dp)
                ),
        color = backgroundColor,
        shape = RoundedCornerShape(50.dp)
    ) {

        Row(
            modifier =
                Modifier.padding(
                    horizontal = 14.dp,
                    vertical = 9.dp
                ),
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Image(
                painter =
                    painterResource(
                        id = status.toIconRes()
                    ),
                contentDescription =
                    status.toSpanishName(),
                modifier =
                    Modifier.size(19.dp),
                contentScale =
                    ContentScale.Fit
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = status.toSpanishName(),
                color = textColor,
                style =
                    MaterialTheme
                        .typography
                        .labelMedium,
                fontWeight =
                    if (selected) {
                        FontWeight.Bold
                    } else {
                        FontWeight.Medium
                    }
            )

        }

    }

}

private fun ActivityStatus.toSpanishName(): String {

    return when (this) {

        ActivityStatus.PENDING ->
            "Pendiente"

        ActivityStatus.IN_PROGRESS ->
            "En progreso"

        ActivityStatus.COMPLETED ->
            "Completada"

    }

}

@DrawableRes
private fun ActivityStatus.toIconRes(): Int {

    return when (this) {

        ActivityStatus.PENDING ->
            R.drawable.ic_activity_pending

        ActivityStatus.IN_PROGRESS ->
            R.drawable.ic_progress

        ActivityStatus.COMPLETED ->
            R.drawable.ic_activity_completed

    }

}

private fun ActivityStatus.toColor(): Color {

    return when (this) {

        ActivityStatus.PENDING ->
            Color(0xFFD76A5E)

        ActivityStatus.IN_PROGRESS ->
            Color(0xFFC28A22)

        ActivityStatus.COMPLETED ->
            EverpathStatusCompleted

    }

}

private fun ActivityStatus.toContainerColor(): Color {

    return when (this) {

        ActivityStatus.PENDING ->
            Color(0xFFF4E3E1)

        ActivityStatus.IN_PROGRESS ->
            Color(0xFFF5E8CC)

        ActivityStatus.COMPLETED ->
            EverpathStatusCompletedContainer

    }

}