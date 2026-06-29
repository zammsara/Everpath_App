package com.everpath.presentation.profile.screen

import androidx.compose.foundation.Canvas
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.everpath.R
import kotlin.math.roundToInt
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.everpath.EverpathApplication
import com.everpath.domain.model.Achievement
import com.everpath.presentation.profile.viewmodel.ProfileViewModel
import com.everpath.presentation.profile.viewmodel.ProfileViewModelFactory

@Suppress("UNUSED_PARAMETER")
@Composable
fun ProfileScreen(
    navController: NavHostController? = null
) {

    val application =
        LocalContext.current.applicationContext
                as EverpathApplication

    val factory =
        remember {

            ProfileViewModelFactory(
                getGoalNodesUseCase =
                    application
                        .appContainer
                        .getGoalNodesUseCase,

                getUserProgressUseCase =
                    application
                        .appContainer
                        .getUserProgressUseCase,

                getUserLevelUseCase =
                    application
                        .appContainer
                        .getUserLevelUseCase,

                getAchievementsUseCase =
                    application
                        .appContainer
                        .getAchievementsUseCase,

                getLevelProgressUseCase =
                    application
                        .appContainer
                        .getLevelProgressUseCase
            )

        }

    val viewModel: ProfileViewModel =
        viewModel(
            factory = factory
        )

    val uiState by
    viewModel
        .uiState
        .collectAsStateWithLifecycle()

    if (
        uiState.isLoading
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize(),
            contentAlignment =
                Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    val levelProgress =
        uiState.levelProgress
            ?: return


    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(
                    brush =
                        Brush.verticalGradient(
                            colors =
                                listOf(
                                    ProfileBackgroundTop,
                                    ProfileBackgroundBottom
                                )
                        )
                )
    ) {

        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 28.dp,
                        bottom = 112.dp
                    ),
            verticalArrangement =
                Arrangement.spacedBy(18.dp)
        ) {

            ProfileHeader(
                userName = "Usuario Everpath",
                level = uiState.level,
                xp = uiState.xp
            )

            LevelProgressHeroCard(
                level = uiState.level,
                currentLevelXp =
                    levelProgress.currentLevelXp,
                nextLevelXp =
                    levelProgress.requiredXpForNextLevel,
                totalXp =
                    uiState.xp,
                progress =
                    levelProgress.progress
            )

            ProfileStatsCard(
                goalCount = uiState.goalCount,
                completedGoalCount = uiState.completedGoalCount,
                activityCount = uiState.activityCount,
                completedActivityCount = uiState.completedActivityCount
            )

            GeneralProgressMiniCard(
                progress = uiState.globalProgress
            )

            CompletionSummaryCard(
                completedGoalCount = uiState.completedGoalCount,
                completedActivityCount = uiState.completedActivityCount
            )

            AchievementsSection(
                achievements = uiState.achievements
            )

        }

    }

}

@Composable
private fun ProfileHeader(
    userName: String,
    level: Int,
    xp: Int
) {

    Row(
        modifier =
            Modifier.fillMaxWidth(),
        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Column(
            modifier =
                Modifier.weight(1f)
        ) {

            Text(
                text = userName,
                color = ProfileTextPrimary,
                style =
                    MaterialTheme
                        .typography
                        .headlineLarge,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = "Tu progreso personal",
                color = ProfileTextSecondary,
                style =
                    MaterialTheme
                        .typography
                        .titleMedium,
                fontWeight = FontWeight.Medium
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Row(
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Box(
                    modifier =
                        Modifier
                            .size(34.dp)
                            .background(
                                color = ProfilePrimary,
                                shape = RoundedCornerShape(12.dp)
                            ),
                    contentAlignment =
                        Alignment.Center
                ) {

                    Text(
                        text = level.toString(),
                        color = Color.White,
                        style =
                            MaterialTheme
                                .typography
                                .titleMedium,
                        fontWeight = FontWeight.ExtraBold
                    )

                }

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                Text(
                    text = "Nivel $level",
                    color = ProfilePrimary,
                    style =
                        MaterialTheme
                            .typography
                            .titleLarge,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                Text(
                    text = "$xp XP",
                    color = ProfileTextSecondary,
                    style =
                        MaterialTheme
                            .typography
                            .titleLarge,
                    fontWeight = FontWeight.Medium
                )

            }

        }

        Box(
            modifier =
                Modifier
                    .size(88.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = CircleShape,
                        clip = false
                    )
                    .background(
                        color = ProfileCard,
                        shape = CircleShape
                    )
                    .border(
                        width = 1.dp,
                        color = Color.White.copy(
                            alpha = 0.85f
                        ),
                        shape = CircleShape
                    ),
            contentAlignment =
                Alignment.Center
        ) {

            Image(
                painter =
                    painterResource(
                        id = R.drawable.everpath_logo
                    ),
                contentDescription = "Logo Everpath",
                modifier =
                    Modifier.size(62.dp),
                contentScale =
                    ContentScale.Fit
            )

        }

    }

}

@Composable
private fun LevelProgressHeroCard(
    level: Int,
    currentLevelXp: Int,
    nextLevelXp: Int,
    totalXp: Int,
    progress: Float
) {

    val percent =
        (progress * 100f)
            .roundToInt()

    val remainingXp =
        (
                nextLevelXp -
                        currentLevelXp
                ).coerceAtLeast(0)

    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(32.dp),
                    clip = false
                ),
        shape =
            RoundedCornerShape(32.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = ProfileCard
            ),
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
    ) {

        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(
                        brush =
                            Brush.linearGradient(
                                colors =
                                    listOf(
                                        ProfilePrimarySoft.copy(
                                            alpha = 0.95f
                                        ),
                                        ProfileCard,
                                        ProfileCard
                                    )
                            )
                    )
                    .padding(22.dp)
        ) {

            Column(
                verticalArrangement =
                    Arrangement.spacedBy(16.dp)
            ) {

                Row(
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Box(
                        modifier =
                            Modifier
                                .size(72.dp)
                                .shadow(
                                    elevation = 6.dp,
                                    shape = CircleShape,
                                    clip = false
                                )
                                .background(
                                    color = ProfilePrimarySoft,
                                    shape = CircleShape
                                )
                                .border(
                                    width = 4.dp,
                                    color = Color.White.copy(
                                        alpha = 0.85f
                                    ),
                                    shape = CircleShape
                                ),
                        contentAlignment =
                            Alignment.Center
                    ) {

                        Image(
                            painter =
                                painterResource(
                                    id = R.drawable.ic_dashboard_star
                                ),
                            contentDescription = "Nivel",
                            modifier =
                                Modifier.size(42.dp),
                            contentScale =
                                ContentScale.Fit
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
                            text = "Progreso de Nivel",
                            color = ProfileTextPrimary,
                            style =
                                MaterialTheme
                                    .typography
                                    .headlineSmall,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Text(
                            text = "Sigue avanzando hacia tu siguiente nivel.",
                            color = ProfileTextSecondary,
                            style =
                                MaterialTheme
                                    .typography
                                    .bodyMedium
                        )

                    }

                    Surface(
                        color = ProfilePrimary,
                        shape = RoundedCornerShape(22.dp)
                    ) {

                        Text(
                            text = "Nivel $level",
                            color = Color.White,
                            style =
                                MaterialTheme
                                    .typography
                                    .labelLarge,
                            fontWeight = FontWeight.Bold,
                            modifier =
                                Modifier.padding(
                                    horizontal = 14.dp,
                                    vertical = 8.dp
                                )
                        )

                    }

                }

                Row(
                    modifier =
                        Modifier.fillMaxWidth(),
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Column(
                        modifier =
                            Modifier.weight(1f)
                    ) {

                        Row(
                            verticalAlignment =
                                Alignment.Bottom
                        ) {

                            Text(
                                text = "$currentLevelXp",
                                color = ProfilePrimary,
                                style =
                                    MaterialTheme
                                        .typography
                                        .headlineLarge,
                                fontWeight = FontWeight.ExtraBold
                            )

                            Text(
                                text = " / $nextLevelXp XP",
                                color = ProfileTextPrimary,
                                style =
                                    MaterialTheme
                                        .typography
                                        .headlineSmall,
                                fontWeight = FontWeight.Bold
                            )

                        }

                        Text(
                            text = "Te faltan $remainingXp XP para avanzar.",
                            color = ProfileTextSecondary,
                            style =
                                MaterialTheme
                                    .typography
                                    .bodyLarge
                        )

                    }

                    ProfileProgressRing(
                        progress = progress,
                        percent = percent
                    )

                }

                LinearProgressIndicator(
                    progress = {
                        progress
                    },
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(10.dp),
                    color = ProfilePrimary,
                    trackColor =
                        ProfilePrimarySoft
                )

                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .background(
                                color = ProfilePrimarySoft.copy(
                                    alpha = 0.55f
                                ),
                                shape = RoundedCornerShape(22.dp)
                            )
                            .padding(14.dp),
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Box(
                        modifier =
                            Modifier
                                .size(42.dp)
                                .background(
                                    color = ProfileCard,
                                    shape = CircleShape
                                ),
                        contentAlignment =
                            Alignment.Center
                    ) {

                        Image(
                            painter =
                                painterResource(
                                    id = R.drawable.ic_progress
                                ),
                            contentDescription = "Experiencia",
                            modifier =
                                Modifier.size(26.dp),
                            contentScale =
                                ContentScale.Fit
                        )

                    }

                    Spacer(
                        modifier = Modifier.width(12.dp)
                    )

                    Text(
                        text = "Cada punto de experiencia representa un paso más en tu camino.",
                        color = ProfileTextSecondary,
                        style =
                            MaterialTheme
                                .typography
                                .bodyMedium,
                        modifier =
                            Modifier.weight(1f)
                    )

                }

            }

        }

    }

}

@Composable
private fun ProfileProgressRing(
    progress: Float,
    percent: Int
) {

    Box(
        modifier =
            Modifier.size(76.dp),
        contentAlignment =
            Alignment.Center
    ) {

        Canvas(
            modifier =
                Modifier.size(76.dp)
        ) {

            val strokeWidth =
                10.dp.toPx()

            val arcSize =
                Size(
                    width = size.width - strokeWidth,
                    height = size.height - strokeWidth
                )

            drawArc(
                color = ProfilePrimarySoft,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft =
                    Offset(
                        strokeWidth / 2f,
                        strokeWidth / 2f
                    ),
                size = arcSize,
                style =
                    Stroke(
                        width = strokeWidth,
                        cap = StrokeCap.Round
                    )
            )

            drawArc(
                color = ProfilePrimary,
                startAngle = -90f,
                sweepAngle = progress * 360f,
                useCenter = false,
                topLeft =
                    Offset(
                        strokeWidth / 2f,
                        strokeWidth / 2f
                    ),
                size = arcSize,
                style =
                    Stroke(
                        width = strokeWidth,
                        cap = StrokeCap.Round
                    )
            )

        }

        Text(
            text = "$percent%",
            color = ProfilePrimary,
            style =
                MaterialTheme
                    .typography
                    .titleLarge,
            fontWeight = FontWeight.ExtraBold
        )

    }

}

@Composable
private fun ProfileStatsCard(
    goalCount: Int,
    completedGoalCount: Int,
    activityCount: Int,
    completedActivityCount: Int
) {

    Card(
        modifier =
            Modifier.fillMaxWidth(),
        shape =
            RoundedCornerShape(28.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = ProfileCard
            ),
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
    ) {

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 12.dp,
                        vertical = 20.dp
                    ),
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            StatItem(
                iconRes = R.drawable.ic_goal_target,
                label = "Metas",
                value = goalCount,
                modifier = Modifier.weight(1f)
            )

            VerticalDividerSoft()

            StatItem(
                iconRes = R.drawable.ic_activity_completed,
                label = "Metas completadas",
                value = completedGoalCount,
                modifier = Modifier.weight(1f)
            )

            VerticalDividerSoft()

            StatItem(
                iconRes = R.drawable.ic_activities,
                label = "Actividades",
                value = activityCount,
                modifier = Modifier.weight(1f)
            )

            VerticalDividerSoft()

            StatItem(
                iconRes = R.drawable.ic_activity_completed,
                label = "Actividades completadas",
                value = completedActivityCount,
                modifier = Modifier.weight(1f)
            )

        }

    }

}

@Composable
private fun StatItem(
    iconRes: Int,
    label: String,
    value: Int,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        horizontalAlignment =
            Alignment.CenterHorizontally,
        verticalArrangement =
            Arrangement.spacedBy(8.dp)
    ) {

        Box(
            modifier =
                Modifier
                    .size(48.dp)
                    .background(
                        color = ProfilePrimarySoft,
                        shape = CircleShape
                    ),
            contentAlignment =
                Alignment.Center
        ) {

            Image(
                painter =
                    painterResource(
                        id = iconRes
                    ),
                contentDescription = label,
                modifier =
                    Modifier.size(28.dp),
                contentScale =
                    ContentScale.Fit
            )

        }

        Text(
            text = label,
            color = ProfileTextSecondary,
            style =
                MaterialTheme
                    .typography
                    .labelMedium,
            textAlign = TextAlign.Center,
            maxLines = 2,
            minLines = 2
        )

        Text(
            text = value.toString(),
            color = ProfileTextPrimary,
            style =
                MaterialTheme
                    .typography
                    .headlineSmall,
            fontWeight = FontWeight.ExtraBold
        )

    }

}

@Composable
private fun VerticalDividerSoft() {

    Box(
        modifier =
            Modifier
                .height(86.dp)
                .width(1.dp)
                .background(
                    color = ProfileDivider
                )
    )

}

@Composable
private fun GeneralProgressMiniCard(
    progress: Float
) {

    val safeProgress =
        progress.coerceIn(
            0f,
            1f
        )

    val percent =
        (safeProgress * 100f)
            .roundToInt()

    Card(
        modifier =
            Modifier.fillMaxWidth(),
        shape =
            RoundedCornerShape(28.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = ProfileCard
            ),
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
    ) {

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Box(
                modifier =
                    Modifier
                        .size(58.dp)
                        .background(
                            color = ProfileGreenSoft,
                            shape = CircleShape
                        ),
                contentAlignment =
                    Alignment.Center
            ) {

                Image(
                    painter =
                        painterResource(
                            id = R.drawable.ic_daschboard_general
                        ),
                    contentDescription = "Progreso general",
                    modifier =
                        Modifier.size(36.dp),
                    contentScale =
                        ContentScale.Fit
                )

            }

            Spacer(
                modifier = Modifier.width(14.dp)
            )

            Column(
                modifier =
                    Modifier.weight(1f),
                verticalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                Row(
                    modifier =
                        Modifier.fillMaxWidth(),
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Text(
                        text = "Progreso General",
                        color = ProfileTextPrimary,
                        style =
                            MaterialTheme
                                .typography
                                .titleLarge,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Spacer(
                        modifier =
                            Modifier.weight(1f)
                    )

                    Text(
                        text = "$percent%",
                        color = ProfileGreen,
                        style =
                            MaterialTheme
                                .typography
                                .titleLarge,
                        fontWeight = FontWeight.ExtraBold
                    )

                }

                LinearProgressIndicator(
                    progress = {
                        safeProgress
                    },
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(8.dp),
                    color = ProfileGreen,
                    trackColor = ProfileGreenSoft
                )

            }

        }

    }

}

@Composable
private fun CompletionSummaryCard(
    completedGoalCount: Int,
    completedActivityCount: Int
) {

    Card(
        modifier =
            Modifier.fillMaxWidth(),
        shape =
            RoundedCornerShape(28.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = ProfileCard
            ),
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
    ) {

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Box(
                modifier =
                    Modifier
                        .size(58.dp)
                        .background(
                            color = ProfileGreenSoft,
                            shape = CircleShape
                        ),
                contentAlignment =
                    Alignment.Center
            ) {

                Image(
                    painter =
                        painterResource(
                            id = R.drawable.ic_activity_completed
                        ),
                    contentDescription = "Completado",
                    modifier =
                        Modifier.size(34.dp),
                    contentScale =
                        ContentScale.Fit
                )

            }

            Spacer(
                modifier = Modifier.width(16.dp)
            )

            Column {

                Text(
                    text = "Has completado $completedGoalCount metas",
                    color = ProfileTextPrimary,
                    style =
                        MaterialTheme
                            .typography
                            .titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "y $completedActivityCount actividades.",
                    color = ProfileTextPrimary,
                    style =
                        MaterialTheme
                            .typography
                            .titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "Sigue avanzando.",
                    color = ProfilePrimary,
                    style =
                        MaterialTheme
                            .typography
                            .titleMedium,
                    fontWeight = FontWeight.Bold
                )

            }

        }

    }

}

@Composable
private fun AchievementsSection(
    achievements: List<Achievement>
) {

    Column(
        verticalArrangement =
            Arrangement.spacedBy(12.dp)
    ) {

        Row(
            modifier =
                Modifier.fillMaxWidth(),
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Text(
                text = "Logros",
                color = ProfileTextPrimary,
                style =
                    MaterialTheme
                        .typography
                        .headlineSmall,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(
                modifier =
                    Modifier.weight(1f)
            )

            Text(
                text = "Ver todos",
                color = ProfilePrimary,
                style =
                    MaterialTheme
                        .typography
                        .titleMedium,
                fontWeight = FontWeight.Bold
            )

        }

        achievements.forEach { achievement ->

            AchievementRow(
                achievement = achievement
            )

        }

    }

}

@Composable
private fun AchievementRow(
    achievement: Achievement
) {

    Card(
        modifier =
            Modifier.fillMaxWidth(),
        shape =
            RoundedCornerShape(26.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = ProfileCard
            ),
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
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
                        .size(58.dp)
                        .background(
                            color = ProfileGoldSoft,
                            shape = CircleShape
                        ),
                contentAlignment =
                    Alignment.Center
            ) {

                Image(
                    painter =
                        painterResource(
                            id = R.drawable.ic_dashboard_star
                        ),
                    contentDescription = achievement.title,
                    modifier =
                        Modifier.size(34.dp),
                    contentScale =
                        ContentScale.Fit
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
                    text = achievement.title,
                    color = ProfileTextPrimary,
                    style =
                        MaterialTheme
                            .typography
                            .titleLarge,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = achievement.description,
                    color = ProfileTextSecondary,
                    style =
                        MaterialTheme
                            .typography
                            .bodyMedium
                )

            }

        }

    }

}

private val ProfileBackgroundTop =
    Color(0xFFFCF8FF)

private val ProfileBackgroundBottom =
    Color(0xFFF3ECFF)

private val ProfileCard =
    Color(0xFFFFFBFF)

private val ProfilePrimary =
    Color(0xFF754EF6)

private val ProfilePrimarySoft =
    Color(0xFFE9DDFF)

private val ProfileTextPrimary =
    Color(0xFF17122F)

private val ProfileTextSecondary =
    Color(0xFF6E6591)

private val ProfileDivider =
    Color(0xFFE8DFF4)

private val ProfileGreen =
    Color(0xFF4F9A6A)

private val ProfileGreenSoft =
    Color(0xFFE2F3E7)

private val ProfileGoldSoft =
    Color(0xFFFFECCC)