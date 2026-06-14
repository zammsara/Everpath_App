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

@Composable
fun EditGoalDialog(
    initialTitle: String,
    initialDescription: String,
    onDismiss: () -> Unit,
    onSave: (
        String,
        String
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

            }

        },

        confirmButton = {

            Button(

                enabled = isValid,

                onClick = {

                    onSave(
                        title.value,
                        description.value
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