package com.everpath.presentation.activity.components

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
fun EditActivityDialog(
    initialTitle: String,
    initialDescription: String,
    onDismiss: () -> Unit,
    onSave: (
        title: String,
        description: String
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

    AlertDialog(

        onDismissRequest = onDismiss,

        title = {
            Text("Editar Actividad")
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