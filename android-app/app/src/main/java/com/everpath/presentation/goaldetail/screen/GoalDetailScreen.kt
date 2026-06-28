package com.everpath.presentation.goaldetail.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.everpath.EverpathApplication
import com.everpath.R
import com.everpath.domain.enums.ActivityStatus
import com.everpath.domain.enums.GoalStatus
import com.everpath.domain.enums.LifeAreaType
import com.everpath.domain.model.GoalNode
import com.everpath.navigation.AppDestination
import com.everpath.presentation.activity.components.ActivityList
import com.everpath.presentation.activity.components.CreateActivityDialog
import com.everpath.presentation.activity.viewmodel.ActivityViewModel
import com.everpath.presentation.activity.viewmodel.ActivityViewModelFactory
import com.everpath.presentation.everpath.components.EditGoalDialog
import com.everpath.presentation.goaldetail.viewmodel.GoalDetailViewModel
import com.everpath.presentation.goaldetail.viewmodel.GoalDetailViewModelFactory
import com.everpath.ui.theme.EverpathAreaCareer
import com.everpath.ui.theme.EverpathAreaCareerContainer
import com.everpath.ui.theme.EverpathAreaCreativity
import com.everpath.ui.theme.EverpathAreaCreativityContainer
import com.everpath.ui.theme.EverpathAreaFinance
import com.everpath.ui.theme.EverpathAreaFinanceContainer
import com.everpath.ui.theme.EverpathAreaHealth
import com.everpath.ui.theme.EverpathAreaHealthContainer
import com.everpath.ui.theme.EverpathAreaRelationships
import com.everpath.ui.theme.EverpathAreaRelationshipsContainer
import com.everpath.ui.theme.EverpathAreaStudies
import com.everpath.ui.theme.EverpathAreaStudiesContainer
import com.everpath.ui.theme.EverpathAreaTravel
import com.everpath.ui.theme.EverpathAreaTravelContainer
import com.everpath.ui.theme.EverpathBackground
import com.everpath.ui.theme.EverpathBorder
import com.everpath.ui.theme.EverpathError
import com.everpath.ui.theme.EverpathPrimary
import com.everpath.ui.theme.EverpathStatusActive
import com.everpath.ui.theme.EverpathStatusActiveContainer
import com.everpath.ui.theme.EverpathStatusArchived
import com.everpath.ui.theme.EverpathStatusArchivedContainer
import com.everpath.ui.theme.EverpathStatusCompleted
import com.everpath.ui.theme.EverpathStatusCompletedContainer
import com.everpath.ui.theme.EverpathStatusLocked
import com.everpath.ui.theme.EverpathStatusLockedContainer
import com.everpath.ui.theme.EverpathSurface
import com.everpath.ui.theme.EverpathSurfaceSoft
import com.everpath.ui.theme.EverpathSurfaceVariant
import com.everpath.ui.theme.EverpathTextPrimary
import com.everpath.ui.theme.EverpathTextSecondary
import com.everpath.ui.theme.EverpathWhite

@Composable
fun GoalDetailScreen(
    goalId: String,
    navController: NavHostController
) {

    val application =
        LocalContext.current.applicationContext
                as EverpathApplication

    val factory =
        remember {
            GoalDetailViewModelFactory(
                getGoalNodeByIdUseCase =
                    application
                        .appContainer
                        .getGoalNodeByIdUseCase,

                updateGoalNodeUseCase =
                    application
                        .appContainer
                        .updateGoalNodeUseCase,

                deleteGoalNodeUseCase =
                    application
                        .appContainer
                        .deleteGoalNodeUseCase
            )
        }

    val activityFactory =
        remember {
            ActivityViewModelFactory(
                getActivitiesByGoalIdUseCase =
                    application
                        .appContainer
                        .getActivitiesByGoalIdUseCase,

                fetchActivitiesByGoalUseCase =
                    application
                        .appContainer
                        .fetchActivitiesByGoalUseCase,

                saveActivityUseCase =
                    application
                        .appContainer
                        .saveActivityUseCase,

                updateActivityUseCase =
                    application
                        .appContainer
                        .updateActivityUseCase,

                deleteActivityUseCase =
                    application
                        .appContainer
                        .deleteActivityUseCase
            )
        }

    val viewModel: GoalDetailViewModel =
        viewModel(
            factory = factory
        )

    val activityViewModel: ActivityViewModel =
        viewModel(
            factory = activityFactory
        )

    val showEditDialog =
        remember {
            mutableStateOf(false)
        }

    val showDeleteDialog =
        remember {
            mutableStateOf(false)
        }

    val showCreateActivityDialog =
        remember {
            mutableStateOf(false)
        }

    val uiState =
        viewModel
            .uiState
            .collectAsStateWithLifecycle()

    val activityUiState =
        activityViewModel
            .uiState
            .collectAsStateWithLifecycle()

    LaunchedEffect(goalId) {
        viewModel.loadGoal(goalId)
        activityViewModel.loadActivities(goalId)
    }

    LaunchedEffect(
        activityUiState.value.activities
    ) {

        val activities =
            activityUiState.value.activities

        if (activities.isNotEmpty()) {

            val allCompleted =
                activities.all {
                    it.status == ActivityStatus.COMPLETED
                }

            if (
                allCompleted &&
                uiState.value.goal?.status != GoalStatus.COMPLETED
            ) {
                viewModel.updateGoalStatus(
                    GoalStatus.COMPLETED
                )
            } else if (
                !allCompleted &&
                uiState.value.goal?.status == GoalStatus.COMPLETED
            ) {
                viewModel.updateGoalStatus(
                    GoalStatus.ACTIVE
                )
            }
        }
    }

    val currentGoal =
        uiState.value.goal

    if (currentGoal == null) {

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

    val totalActivities =
        activityUiState.value.activities.size

    val completedActivities =
        activityUiState.value.activities.count {
            it.status == ActivityStatus.COMPLETED
        }

    val progressPercentage =
        if (totalActivities == 0) {
            0
        } else {
            (completedActivities * 100) / totalActivities
        }

    val scrollState =
        rememberScrollState()

    Scaffold(
        containerColor = EverpathBackground,

        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    showCreateActivityDialog.value = true
                },
                containerColor = EverpathPrimary,
                contentColor = EverpathWhite,
                shape = CircleShape,
                modifier =
                    Modifier
                        .size(66.dp)
                        .shadow(
                            elevation = 12.dp,
                            shape = CircleShape
                        )
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar Actividad",
                    modifier = Modifier.size(34.dp)
                )

            }

        }
    ) { paddingValues ->

        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(scrollState)
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 12.dp,
                        bottom = 96.dp
                    )
        ) {

            Row(
                modifier =
                    Modifier.fillMaxWidth(),
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = EverpathTextPrimary
                    )
                }

            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            GoalHeroCard(
                goal = currentGoal,
                completedActivities = completedActivities,
                totalActivities = totalActivities,
                progressPercentage = progressPercentage,
                progress = activityUiState.value.progress
            )

            Spacer(
                modifier = Modifier.height(26.dp)
            )

            ActivitiesHeader()

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            if (
                activityUiState.value.activities.isEmpty()
            ) {

                EmptyActivitiesState()

            } else {

                Card(
                    modifier =
                        Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(26.dp),
                    colors =
                        CardDefaults.cardColors(
                            containerColor = EverpathSurface
                        ),
                    elevation =
                        CardDefaults.cardElevation(
                            defaultElevation = 4.dp
                        )
                ) {

                    ActivityList(
                        activities =
                            activityUiState.value.activities,

                        onActivityClick = { activityId ->

                            navController.navigate(
                                AppDestination
                                    .ActivityDetail
                                    .createRoute(activityId)
                            )

                        }
                    )

                }

            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            ActionButtons(
                onEditClick = {
                    showEditDialog.value = true
                },
                onDeleteClick = {
                    showDeleteDialog.value = true
                }
            )

        }

    }

    if (
        showEditDialog.value
    ) {

        EditGoalDialog(
            initialTitle = currentGoal.title,
            initialDescription = currentGoal.description,
            initialStatus = currentGoal.status,
            initialLifeArea = currentGoal.lifeArea,

            onDismiss = {
                showEditDialog.value = false
            },

            onSave = {
                    title,
                    description,
                    status,
                    lifeArea ->

                viewModel.updateGoal(
                    title = title,
                    description = description,
                    status = status,
                    lifeArea = lifeArea
                )

                showEditDialog.value = false
            }
        )

    }

    if (
        showDeleteDialog.value
    ) {

        AlertDialog(
            onDismissRequest = {
                showDeleteDialog.value = false
            },

            containerColor = EverpathSurface,
            shape = RoundedCornerShape(28.dp),

            title = {
                Text(
                    text = "Eliminar Meta",
                    color = EverpathTextPrimary,
                    fontWeight = FontWeight.Bold
                )
            },

            text = {
                Text(
                    text = "¿Deseas eliminar esta meta?",
                    color = EverpathTextSecondary
                )
            },

            confirmButton = {

                TextButton(
                    onClick = {

                        viewModel.deleteGoal {
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

    if (
        showCreateActivityDialog.value
    ) {

        CreateActivityDialog(
            onDismiss = {
                showCreateActivityDialog.value = false
            },

            onSave = { title, description ->

                activityViewModel.createActivity(
                    goalId = currentGoal.id,
                    title = title,
                    description = description
                )

                showCreateActivityDialog.value = false
            }
        )

    }

}

@Composable
private fun GoalHeroCard(
    goal: GoalNode,
    completedActivities: Int,
    totalActivities: Int,
    progressPercentage: Int,
    progress: Float
) {

    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 12.dp,
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
                            .size(82.dp)
                            .background(
                                color = goal.lifeArea.toContainerColor(),
                                shape = CircleShape
                            ),
                    contentAlignment =
                        Alignment.Center
                ) {

                    Image(
                        painter =
                            painterResource(
                                id = goal.lifeArea.toIconRes()
                            ),
                        contentDescription = goal.lifeArea.toSpanishName(),
                        modifier = Modifier.size(46.dp),
                        contentScale = ContentScale.Fit
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
                        text = goal.title,
                        color = EverpathTextPrimary,
                        style =
                            MaterialTheme
                                .typography
                                .headlineSmall,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight =
                            MaterialTheme
                                .typography
                                .headlineSmall
                                .lineHeight
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text =
                            goal.description.ifBlank {
                                "Sin descripción"
                            },
                        color = EverpathTextSecondary,
                        style =
                            MaterialTheme
                                .typography
                                .bodyMedium
                    )

                }

            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Row(
                horizontalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                InfoChip(
                    iconRes = goal.lifeArea.toIconRes(),
                    text = goal.lifeArea.toSpanishName(),
                    contentColor = goal.lifeArea.toColor(),
                    containerColor = goal.lifeArea.toContainerColor()
                )

                StatusChip(
                    status = goal.status
                )

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
                                alpha = 0.55f
                            )
                        )
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(
                text = "Progreso",
                color = EverpathTextPrimary,
                style =
                    MaterialTheme
                        .typography
                        .titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Row(
                modifier =
                    Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween,
                verticalAlignment =
                    Alignment.Bottom
            ) {

                Text(
                    text = "$progressPercentage%",
                    color = EverpathPrimary,
                    style =
                        MaterialTheme
                            .typography
                            .headlineMedium,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = "$completedActivities/$totalActivities actividades",
                    color = EverpathTextSecondary,
                    style =
                        MaterialTheme
                            .typography
                            .bodyMedium,
                    fontWeight = FontWeight.Medium
                )

            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            LinearProgressIndicator(
                progress = {
                    progress
                },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(10.dp),
                color = EverpathPrimary,
                trackColor = EverpathSurfaceVariant
            )

        }

    }

}

@Composable
private fun InfoChip(
    @DrawableRes iconRes: Int,
    text: String,
    contentColor: Color,
    containerColor: Color
) {

    Surface(
        color = containerColor,
        shape = RoundedCornerShape(50.dp)
    ) {

        Row(
            modifier =
                Modifier.padding(
                    horizontal = 14.dp,
                    vertical = 8.dp
                ),
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Image(
                painter =
                    painterResource(
                        id = iconRes
                    ),
                contentDescription = text,
                modifier = Modifier.size(18.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = text,
                color = contentColor,
                style =
                    MaterialTheme
                        .typography
                        .labelLarge,
                fontWeight = FontWeight.Bold
            )

        }

    }

}

@Composable
private fun StatusChip(
    status: GoalStatus
) {

    Surface(
        color = status.toContainerColor(),
        shape = RoundedCornerShape(50.dp)
    ) {

        Row(
            modifier =
                Modifier.padding(
                    horizontal = 14.dp,
                    vertical = 8.dp
                ),
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Box(
                modifier =
                    Modifier
                        .size(9.dp)
                        .background(
                            color = status.toColor(),
                            shape = CircleShape
                        )
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
                        .labelLarge,
                fontWeight = FontWeight.Bold
            )

        }

    }

}

@Composable
private fun ActivitiesHeader() {

    Row(
        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Image(
            painter =
                painterResource(
                    id = R.drawable.ic_activities
                ),
            contentDescription = "Actividades",
            modifier = Modifier.size(34.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(
            modifier = Modifier.width(10.dp)
        )

        Text(
            text = "Actividades",
            color = EverpathTextPrimary,
            style =
                MaterialTheme
                    .typography
                    .titleLarge,
            fontWeight = FontWeight.ExtraBold
        )

    }

}

@Composable
private fun EmptyActivitiesState() {

    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color =
                        EverpathBorder.copy(
                            alpha = 0.65f
                        ),
                    shape = RoundedCornerShape(30.dp)
                ),
        shape = RoundedCornerShape(30.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = EverpathSurfaceSoft
            ),
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 3.dp
            )
    ) {

        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 28.dp,
                        vertical = 34.dp
                    ),
            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Box(
                modifier =
                    Modifier
                        .size(104.dp)
                        .background(
                            color =
                                EverpathPrimary.copy(
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
                            id = R.drawable.ic_activities
                        ),
                    contentDescription = "Sin actividades",
                    modifier = Modifier.size(60.dp),
                    contentScale = ContentScale.Fit
                )

            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(
                text = "Aún no hay actividades",
                color = EverpathTextPrimary,
                style =
                    MaterialTheme
                        .typography
                        .titleLarge,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Agrega tu primera actividad para comenzar a avanzar hacia tu meta.",
                color = EverpathTextSecondary,
                style =
                    MaterialTheme
                        .typography
                        .bodyMedium,
                textAlign = TextAlign.Center
            )

        }

    }

}

@Composable
private fun ActionButtons(
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {

    Row(
        modifier =
            Modifier.fillMaxWidth(),
        horizontalArrangement =
            Arrangement.spacedBy(14.dp)
    ) {

        Button(
            onClick = onEditClick,
            modifier =
                Modifier
                    .weight(1f)
                    .height(54.dp),
            shape = RoundedCornerShape(28.dp),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = EverpathStatusActiveContainer,
                    contentColor = EverpathPrimary
                ),
            elevation =
                ButtonDefaults.buttonElevation(
                    defaultElevation = 0.dp
                )
        ) {

            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar"
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = "Editar",
                fontWeight = FontWeight.Bold
            )

        }

        Button(
            onClick = onDeleteClick,
            modifier =
                Modifier
                    .weight(1f)
                    .height(54.dp),
            shape = RoundedCornerShape(28.dp),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor =
                        EverpathError.copy(
                            alpha = 0.14f
                        ),
                    contentColor = EverpathError
                ),
            elevation =
                ButtonDefaults.buttonElevation(
                    defaultElevation = 0.dp
                )
        ) {

            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Eliminar"
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = "Eliminar",
                fontWeight = FontWeight.Bold
            )

        }

    }

}

private fun LifeAreaType.toSpanishName(): String {

    return when (this) {

        LifeAreaType.HEALTH ->
            "Salud"

        LifeAreaType.STUDIES ->
            "Estudios"

        LifeAreaType.CAREER ->
            "Carrera"

        LifeAreaType.FINANCE ->
            "Finanzas"

        LifeAreaType.RELATIONSHIPS ->
            "Relaciones"

        LifeAreaType.CREATIVITY ->
            "Creatividad"

        LifeAreaType.TRAVEL ->
            "Viajes"
    }
}

@DrawableRes
private fun LifeAreaType.toIconRes(): Int {

    return when (this) {

        LifeAreaType.HEALTH ->
            R.drawable.ic_area_health

        LifeAreaType.STUDIES ->
            R.drawable.ic_area_studies

        LifeAreaType.CAREER ->
            R.drawable.ic_area_career

        LifeAreaType.FINANCE ->
            R.drawable.ic_area_finance

        LifeAreaType.RELATIONSHIPS ->
            R.drawable.ic_area_relationships

        LifeAreaType.CREATIVITY ->
            R.drawable.ic_area_creativity

        LifeAreaType.TRAVEL ->
            R.drawable.ic_area_travel
    }
}

private fun LifeAreaType.toColor(): Color {

    return when (this) {

        LifeAreaType.HEALTH ->
            EverpathAreaHealth

        LifeAreaType.STUDIES ->
            EverpathAreaStudies

        LifeAreaType.CAREER ->
            EverpathAreaCareer

        LifeAreaType.FINANCE ->
            EverpathAreaFinance

        LifeAreaType.RELATIONSHIPS ->
            EverpathAreaRelationships

        LifeAreaType.CREATIVITY ->
            EverpathAreaCreativity

        LifeAreaType.TRAVEL ->
            EverpathAreaTravel
    }
}

private fun LifeAreaType.toContainerColor(): Color {

    return when (this) {

        LifeAreaType.HEALTH ->
            EverpathAreaHealthContainer

        LifeAreaType.STUDIES ->
            EverpathAreaStudiesContainer

        LifeAreaType.CAREER ->
            EverpathAreaCareerContainer

        LifeAreaType.FINANCE ->
            EverpathAreaFinanceContainer

        LifeAreaType.RELATIONSHIPS ->
            EverpathAreaRelationshipsContainer

        LifeAreaType.CREATIVITY ->
            EverpathAreaCreativityContainer

        LifeAreaType.TRAVEL ->
            EverpathAreaTravelContainer
    }
}

private fun GoalStatus.toSpanishName(): String {

    return when (this) {

        GoalStatus.LOCKED ->
            "Bloqueada"

        GoalStatus.ACTIVE ->
            "Activa"

        GoalStatus.COMPLETED ->
            "Completada"

        GoalStatus.ARCHIVED ->
            "Archivada"
    }
}

private fun GoalStatus.toColor(): Color {

    return when (this) {

        GoalStatus.LOCKED ->
            EverpathStatusLocked

        GoalStatus.ACTIVE ->
            EverpathStatusActive

        GoalStatus.COMPLETED ->
            EverpathStatusCompleted

        GoalStatus.ARCHIVED ->
            EverpathStatusArchived
    }
}

private fun GoalStatus.toContainerColor(): Color {

    return when (this) {

        GoalStatus.LOCKED ->
            EverpathStatusLockedContainer

        GoalStatus.ACTIVE ->
            EverpathStatusActiveContainer

        GoalStatus.COMPLETED ->
            EverpathStatusCompletedContainer

        GoalStatus.ARCHIVED ->
            EverpathStatusArchivedContainer
    }
}