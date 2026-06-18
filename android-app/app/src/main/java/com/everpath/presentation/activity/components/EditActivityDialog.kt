package com.everpath.presentation.activity.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.everpath.domain.enums.ActivityStatus

@Composable
fun EditActivityDialog(
    initialTitle: String,
    initialDescription: String,
    initialStatus: ActivityStatus,
    onDismiss: () -> Unit,
    onSave: (
        title: String,
        description: String,
        status: ActivityStatus
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

    val expanded =
        remember {
            mutableStateOf(false)
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

                Button(

                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),

                    onClick = {

                        expanded.value = true

                    }

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

                        expanded.value = false

                    }

                ) {

                    ActivityStatus.entries.forEach {

                        DropdownMenuItem(

                            text = {

                                Text(
                                    it.name
                                )

                            },

                            onClick = {

                                status.value = it

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