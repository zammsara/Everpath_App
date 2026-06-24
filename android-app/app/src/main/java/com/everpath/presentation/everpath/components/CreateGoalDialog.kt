package com.everpath.presentation.everpath.components

import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.everpath.R
import com.everpath.domain.enums.LifeAreaType
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
import com.everpath.ui.theme.EverpathBorder
import com.everpath.ui.theme.EverpathDialogSurface
import com.everpath.ui.theme.EverpathPrimary
import com.everpath.ui.theme.EverpathSurfaceSoft
import com.everpath.ui.theme.EverpathSurfaceVariant
import com.everpath.ui.theme.EverpathTextDisabled
import com.everpath.ui.theme.EverpathTextPrimary
import com.everpath.ui.theme.EverpathTextSecondary
import com.everpath.ui.theme.EverpathWhite

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreateGoalDialog(
    onDismiss: () -> Unit,
    onSave: (
        title: String,
        description: String,
        lifeArea: LifeAreaType
    ) -> Unit
) {

    val title =
        remember {
            mutableStateOf("")
        }

    val description =
        remember {
            mutableStateOf("")
        }

    val selectedLifeArea =
        remember {
            mutableStateOf(LifeAreaType.HEALTH)
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

            Column {

                Row(
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Box(
                        modifier =
                            Modifier.size(72.dp),
                        contentAlignment =
                            Alignment.Center
                    ) {

                        Image(
                            painter =
                                painterResource(
                                    id = R.drawable.ic_goal_target
                                ),
                            contentDescription = "Nueva meta",
                            modifier =
                                Modifier.size(54.dp),
                            contentScale =
                                ContentScale.Fit
                        )

                    }

                    Spacer(
                        modifier =
                            Modifier.size(18.dp)
                    )

                    Column {

                        Text(
                            text = "Nueva Meta",
                            color = EverpathTextPrimary,
                            style =
                                MaterialTheme
                                    .typography
                                    .titleLarge,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Text(
                            text = "Define tu próximo sueño",
                            color = EverpathTextSecondary,
                            style =
                                MaterialTheme
                                    .typography
                                    .bodySmall
                        )

                    }

                }

            }

        },

        text = {

            Column(
                modifier =
                    Modifier
                        .verticalScroll(scrollState)
            ) {

                OutlinedTextField(
                    value = title.value,
                    onValueChange = {
                        title.value = it
                    },
                    label = {
                        Text("Título")
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(18.dp),
                    colors =
                        OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = EverpathPrimary,
                            unfocusedBorderColor = EverpathBorder,
                            focusedContainerColor = EverpathSurfaceSoft,
                            unfocusedContainerColor = EverpathSurfaceSoft,
                            focusedLabelColor = EverpathPrimary,
                            unfocusedLabelColor = EverpathTextSecondary,
                            cursorColor = EverpathPrimary
                        ),
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 6.dp)
                )

                OutlinedTextField(
                    value = description.value,
                    onValueChange = {
                        description.value = it
                    },
                    label = {
                        Text("Descripción")
                    },
                    minLines = 2,
                    maxLines = 3,
                    shape = RoundedCornerShape(18.dp),
                    colors =
                        OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = EverpathPrimary,
                            unfocusedBorderColor = EverpathBorder,
                            focusedContainerColor = EverpathSurfaceSoft,
                            unfocusedContainerColor = EverpathSurfaceSoft,
                            focusedLabelColor = EverpathPrimary,
                            unfocusedLabelColor = EverpathTextSecondary,
                            cursorColor = EverpathPrimary
                        ),
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                )

                Spacer(
                    modifier =
                        Modifier.height(18.dp)
                )

                SelectedAreaPreview(
                    lifeArea = selectedLifeArea.value
                )

                Spacer(
                    modifier =
                        Modifier.height(16.dp)
                )

                Text(
                    text = "Elige un área de vida",
                    color = EverpathTextPrimary,
                    style =
                        MaterialTheme
                            .typography
                            .titleSmall,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Esto define tu meta en tu mapa.",
                    color = EverpathTextSecondary,
                    style =
                        MaterialTheme
                            .typography
                            .bodySmall,
                    modifier =
                        Modifier.padding(top = 2.dp)
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

                    LifeAreaType.values().forEach { area ->

                        LifeAreaOption(
                            lifeArea = area,
                            selected =
                                selectedLifeArea.value == area,
                            onClick = {
                                selectedLifeArea.value = area
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
                        selectedLifeArea.value
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
                    Modifier
                        .height(46.dp)
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
private fun SelectedAreaPreview(
    lifeArea: LifeAreaType
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
        color = lifeArea.toContainerColor(),
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
                        .size(46.dp)
                        .background(
                            color =
                                lifeArea.toColor()
                                    .copy(alpha = 0.18f),
                            shape = CircleShape
                        ),
                contentAlignment =
                    Alignment.Center
            ) {

                Image(
                    painter =
                        painterResource(
                            id = lifeArea.toIconRes()
                        ),
                    contentDescription =
                        lifeArea.toSpanishName(),
                    modifier =
                        Modifier.size(28.dp),
                    contentScale =
                        ContentScale.Fit
                )

            }

            Spacer(
                modifier =
                    Modifier.size(12.dp)
            )

            Column {

                Text(
                    text = "Área seleccionada",
                    color = EverpathTextSecondary,
                    style =
                        MaterialTheme
                            .typography
                            .labelMedium
                )

                Text(
                    text = lifeArea.toSpanishName(),
                    color = lifeArea.toColor(),
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
private fun LifeAreaOption(
    lifeArea: LifeAreaType,
    selected: Boolean,
    onClick: () -> Unit
) {

    val backgroundColor =
        if (selected) {
            lifeArea.toContainerColor()
        } else {
            EverpathSurfaceSoft
        }

    val borderColor =
        if (selected) {
            lifeArea.toColor()
        } else {
            EverpathBorder
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
                    horizontal = 12.dp,
                    vertical = 8.dp
                ),
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Image(
                painter =
                    painterResource(
                        id = lifeArea.toIconRes()
                    ),
                contentDescription =
                    lifeArea.toSpanishName(),
                modifier =
                    Modifier.size(18.dp),
                contentScale =
                    ContentScale.Fit
            )

            Spacer(
                modifier =
                    Modifier.size(7.dp)
            )

            Text(
                text = lifeArea.toSpanishName(),
                color =
                    if (selected) {
                        lifeArea.toColor()
                    } else {
                        EverpathTextPrimary
                    },
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