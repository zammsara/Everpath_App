package com.everpath.presentation.everpath.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.everpath.R
import com.everpath.domain.enums.LifeAreaType

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

    val isValid =
        title.value
            .trim()
            .isNotEmpty()

    AlertDialog(
        onDismissRequest = onDismiss,

        title = {
            Text("Nueva Meta")
        },

        text = {

            Column {

                OutlinedTextField(
                    value = title.value,
                    onValueChange = {
                        title.value = it
                    },
                    label = {
                        Text("Título")
                    },
                    modifier =
                        Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = description.value,
                    onValueChange = {
                        description.value = it
                    },
                    label = {
                        Text("Descripción")
                    },
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                )

                Text(
                    text = "Área",
                    modifier =
                        Modifier.padding(
                            top = 16.dp,
                            bottom = 8.dp
                        )
                )

                FlowRow(
                    horizontalArrangement =
                        Arrangement.spacedBy(8.dp),
                    verticalArrangement =
                        Arrangement.spacedBy(8.dp)
                ) {

                    LifeAreaType.values().forEach { area ->

                        FilterChip(
                            selected =
                                selectedLifeArea.value == area,
                            onClick = {
                                selectedLifeArea.value = area
                            },
                            leadingIcon = {
                                Image(
                                    painter =
                                        painterResource(
                                            id = area.toIconRes()
                                        ),
                                    contentDescription =
                                        area.toSpanishName(),
                                    modifier =
                                        Modifier.size(20.dp),
                                    contentScale =
                                        ContentScale.Fit
                                )
                            },
                            label = {
                                Text(area.toSpanishName())
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

                }
            ) {

                Text("Guardar")

            }

        },

        dismissButton = {

            TextButton(
                onClick = onDismiss
            ) {

                Text("Cancelar")

            }

        }
    )
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