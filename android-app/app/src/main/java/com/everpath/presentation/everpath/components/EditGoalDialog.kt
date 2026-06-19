package com.everpath.presentation.everpath.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import com.everpath.domain.enums.GoalStatus

/**
 * Diálogo utilizado para editar una Goal existente.
 */
@Composable
fun EditGoalDialog(
    initialTitle: String,
    initialDescription: String,
    initialStatus: GoalStatus,
    onDismiss: () -> Unit,
    onSave: (
        String,
        String,
        GoalStatus
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
            mutableStateOf(
                initialStatus
            )
        }

    val expanded =
        remember {
            mutableStateOf(false)
        }

    val isValid =
        title.value
            .trim()
            .isNotEmpty()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Editar Goal")
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

                Button(
                    onClick = {
                        expanded.value =
                            true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text(
                        text =
                            "Estado: ${status.value}"
                    )
                }

                DropdownMenu(
                    expanded =
                        expanded.value,
                    onDismissRequest = {
                        expanded.value =
                            false
                    }
                ) {
                    GoalStatus.entries
                        .forEach {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        it.name
                                    )
                                },
                                onClick = {
                                    status.value =
                                        it
                                    expanded.value =
                                        false
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
                        title.value,
                        description.value,
                        status.value
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