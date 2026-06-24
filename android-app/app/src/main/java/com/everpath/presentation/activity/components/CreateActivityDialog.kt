package com.everpath.presentation.activity.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.everpath.R
import com.everpath.ui.theme.EverpathBorder
import com.everpath.ui.theme.EverpathDialogSurface
import com.everpath.ui.theme.EverpathPrimary
import com.everpath.ui.theme.EverpathSurfaceSoft
import com.everpath.ui.theme.EverpathSurfaceVariant
import com.everpath.ui.theme.EverpathTextDisabled
import com.everpath.ui.theme.EverpathTextPrimary
import com.everpath.ui.theme.EverpathTextSecondary
import com.everpath.ui.theme.EverpathWhite

@Composable
fun CreateActivityDialog(
    onDismiss: () -> Unit,
    onSave: (
        title: String,
        description: String
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

            Column(
                modifier =
                    Modifier.fillMaxWidth(),
                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                Box(
                    modifier =
                        Modifier
                            .size(86.dp)
                            .background(
                                color =
                                    EverpathPrimary.copy(
                                        alpha = 0.10f
                                    ),
                                shape = CircleShape
                            )
                            .border(
                                width = 1.dp,
                                color =
                                    EverpathPrimary.copy(
                                        alpha = 0.12f
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
                        contentDescription = "Nueva actividad",
                        modifier =
                            Modifier.size(54.dp),
                        contentScale =
                            ContentScale.Fit
                    )

                }

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                Text(
                    text = "Nueva Actividad",
                    color = EverpathTextPrimary,
                    style =
                        MaterialTheme
                            .typography
                            .headlineSmall,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "Agrega una acción para avanzar",
                    color = EverpathTextSecondary,
                    style =
                        MaterialTheme
                            .typography
                            .bodyMedium
                )

            }

        },

        text = {

            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .verticalScroll(scrollState)
            ) {

                Divider(
                    color =
                        EverpathBorder.copy(
                            alpha = 0.55f
                        ),
                    thickness = 1.dp,
                    modifier =
                        Modifier.padding(
                            bottom = 18.dp
                        )
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
                        Text("Escribe un título")
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
                            focusedPlaceholderColor =
                                EverpathTextSecondary.copy(
                                    alpha = 0.68f
                                ),
                            unfocusedPlaceholderColor =
                                EverpathTextSecondary.copy(
                                    alpha = 0.68f
                                ),
                            cursorColor = EverpathPrimary
                        ),
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                )

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                Row(
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Text(
                        text = "Descripción",
                        color = EverpathTextPrimary,
                        style =
                            MaterialTheme
                                .typography
                                .titleSmall,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.width(6.dp)
                    )

                    Text(
                        text = "(opcional)",
                        color = EverpathTextSecondary,
                        style =
                            MaterialTheme
                                .typography
                                .bodySmall,
                        fontWeight = FontWeight.Medium
                    )

                }

                OutlinedTextField(
                    value = description.value,
                    onValueChange = {
                        description.value = it
                    },
                    placeholder = {
                        Text("Describe tu actividad...")
                    },
                    minLines = 3,
                    maxLines = 4,
                    shape = RoundedCornerShape(18.dp),
                    colors =
                        OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = EverpathPrimary,
                            unfocusedBorderColor = EverpathBorder,
                            focusedContainerColor = EverpathSurfaceSoft,
                            unfocusedContainerColor = EverpathSurfaceSoft,
                            focusedLabelColor = EverpathPrimary,
                            unfocusedLabelColor = EverpathTextSecondary,
                            focusedPlaceholderColor =
                                EverpathTextSecondary.copy(
                                    alpha = 0.68f
                                ),
                            unfocusedPlaceholderColor =
                                EverpathTextSecondary.copy(
                                    alpha = 0.68f
                                ),
                            cursorColor = EverpathPrimary
                        ),
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
                        title.value.trim(),
                        description.value.trim()
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
                        .height(48.dp)
                        .shadow(
                            elevation =
                                if (isValid) {
                                    6.dp
                                } else {
                                    0.dp
                                },
                            shape = RoundedCornerShape(24.dp),
                            clip = false
                        )
            ) {

                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Guardar",
                    modifier =
                        Modifier.size(20.dp)
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

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