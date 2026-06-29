package com.everpath.presentation.register.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.everpath.ui.theme.EverpathPrimary
import com.everpath.ui.theme.EverpathSurface
import com.everpath.ui.theme.EverpathTextSecondary
import com.everpath.ui.theme.EverpathWhite
import com.everpath.presentation.register.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit

) {

    val uiState by viewModel
        .uiState
        .collectAsState()

    var name by rememberSaveable {
        mutableStateOf("")
    }

    var email by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    var confirmPassword by rememberSaveable {
        mutableStateOf("")
    }

    LaunchedEffect(
        uiState.isRegistered
    ) {
        if (uiState.isRegistered) {
            onRegisterSuccess()
        }
    }

    Scaffold(

        containerColor =
            MaterialTheme.colorScheme.background

    ) { padding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(padding)
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Spacer(
                modifier = Modifier.height(40.dp)
            )

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(EverpathPrimary)
                    .padding(24.dp)

            ) {

                Icon(

                    imageVector = Icons.Outlined.Person,
                    contentDescription = null,
                    tint = EverpathWhite,
                    modifier = Modifier.height(60.dp)

                )
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(

                text = "Crear cuenta",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "Comienza tu aventura en Everpath",
                color = EverpathTextSecondary
            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Card(

                shape = RoundedCornerShape(28.dp),
                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            EverpathSurface
                    )

            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)

                ) {

                    OutlinedTextField(

                        value = name,
                        onValueChange = {
                            name = it
                        },
                        label = {
                            Text("Nombre")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(18.dp)

                    )

                    OutlinedTextField(

                        value = email,
                        onValueChange = {
                            email = it
                        },
                        label = {
                            Text("Correo")
                        },
                        modifier =  Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(18.dp)

                    )

                    OutlinedTextField(

                        value = password,
                        onValueChange = {
                            password = it
                        },
                        visualTransformation =
                            PasswordVisualTransformation(),
                        label = {
                            Text("Contraseña")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(18.dp)

                    )

                    OutlinedTextField(

                        value = confirmPassword,
                        onValueChange = {
                            confirmPassword = it
                        },
                        visualTransformation =
                            PasswordVisualTransformation(),
                        label = {
                            Text("Confirmar contraseña")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(18.dp)

                    )

                    uiState.errorMessage?.let {
                        Text(
                            text = it,
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Button(
                        onClick = {
                            viewModel.register(
                                name = name,
                                email = email,
                                password = password,
                                confirmPassword = confirmPassword
                            )
                        },

                        modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                                containerColor = EverpathPrimary
                            )
                    ) {

                        if (uiState.isLoading) {
                            CircularProgressIndicator(
                                color = EverpathWhite
                            )

                        } else {
                            Text(
                                "Crear cuenta"
                            )
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            TextButton(
                onClick =
                    onNavigateToLogin

            ) {
                Text(
                    "¿Ya tienes cuenta? Iniciar sesión"
                )
            }

            Spacer(
                modifier = Modifier.height(40.dp)
            )
        }
    }
}