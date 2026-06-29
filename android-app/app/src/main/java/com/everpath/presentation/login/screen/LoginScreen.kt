package com.everpath.presentation.login.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.everpath.presentation.login.viewmodel.LoginViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.everpath.R
import com.everpath.ui.theme.*

/**
 * Pantalla encargada del inicio
 * de sesión del usuario.
 *
 * Su responsabilidad consiste en:
 *
 * - observar el estado del ViewModel;
 * - reaccionar al resultado del login;
 * - delegar la representación visual
 *   a la interfaz.
 */
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit

) {

    val uiState by
    viewModel
        .uiState
        .collectAsState()

    LaunchedEffect(
        uiState.isLoggedIn
    ) {

        if (
            uiState.isLoggedIn
        ) {
            onLoginSuccess()
        }
    }

    LoginScreenContent(
        email = uiState.email,
        password = uiState.password,
        isLoading = uiState.isLoading,
        errorMessage = uiState.errorMessage,
        onEmailChanged =
            viewModel::onEmailChanged,
        onPasswordChanged =
            viewModel::onPasswordChanged,
        onLoginClicked = {

            viewModel.login(
                email = uiState.email,
                password = uiState.password
            )
        },

        onRegisterClicked =
            onNavigateToRegister

    )
}

@Composable
private fun LoginScreenContent(
    email: String,
    password: String,
    isLoading: Boolean,
    errorMessage: String?,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit

) {

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(

                Brush.verticalGradient(

                    colors = listOf(
                        EverpathBackground,
                        EverpathSurfaceSoft,
                        EverpathBackground
                    )
                )
            )

    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(
                    horizontal = 28.dp,
                    vertical = 24.dp
                ),
            horizontalAlignment =
                Alignment.CenterHorizontally

        ) {

            Spacer(
                modifier = Modifier.height(36.dp)
            )

            Image(
                painter = painterResource(
                    R.drawable.everpath_logo
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(180.dp)
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = "Tu camino hacia tus metas",
                style = MaterialTheme.typography.bodyLarge,
                color = EverpathTextSecondary

            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Card(

                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = EverpathWhite
                ),
                elevation =
                    CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    )

            ) {

                Column(
                    modifier = Modifier
                        .padding(24.dp)
                ) {

                    OutlinedTextField(
                        value = email,
                        onValueChange =
                            onEmailChanged,
                        modifier =
                            Modifier.fillMaxWidth(),
                        singleLine = true,
                        leadingIcon = {

                            Icon(
                                Icons.Default.Email,
                                null
                            )
                        },

                        placeholder = {
                            Text(
                                "Correo electrónico"
                            )
                        },

                        keyboardOptions =
                            KeyboardOptions(
                                keyboardType =
                                    KeyboardType.Email
                            )
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = onPasswordChanged,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                Icons.Default.Lock,
                                null
                            )
                        },

                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    passwordVisible =
                                        !passwordVisible
                                }

                            ) {
                                Icon(
                                    if (passwordVisible)
                                        Icons.Default.VisibilityOff

                                    else
                                        Icons.Default.Visibility,
                                    null
                                )
                            }
                        },

                        placeholder = {
                            Text(
                                "Contraseña"
                            )
                        },

                        visualTransformation =
                            if (passwordVisible)
                                VisualTransformation.None

                            else
                                PasswordVisualTransformation()
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "¿Olvidaste tu contraseña?",
                        modifier = Modifier
                            .align(Alignment.End),

                        color = EverpathPrimary,
                        fontSize = 13.sp

                    )

                    if (
                        errorMessage != null
                    ) {

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        Text(
                            text = errorMessage,
                            color = EverpathError,
                            style =
                                MaterialTheme.typography.bodyMedium
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )

                    Button(
                        onClick =
                            onLoginClicked,

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),

                        enabled = !isLoading,

                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor =
                                    EverpathPrimary

                            )

                    ) {

                        if (
                            isLoading
                        ) {

                            CircularProgressIndicator(
                                modifier = Modifier.size(22.dp),
                                strokeWidth = 2.dp,
                                color = EverpathWhite
                            )
                        }

                        else {
                            Text(
                                "Iniciar sesión"
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(22.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Divider(
                            modifier = Modifier.weight(1f)
                        )

                        Text(
                            "  o crea tu cuenta  ",
                            color =
                                EverpathTextSecondary
                        )

                        Divider(
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(22.dp)
                    )

                    OutlinedButton(
                        onClick = onRegisterClicked,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp)
                    ) {

                        Text(
                            text = "Crear cuenta",
                            color = EverpathPrimary
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Text(
                text =
                    "Pequeños pasos hoy,\n" +
                            "grandes cambios mañana.",

                textAlign = TextAlign.Center,
                color = EverpathPrimary,
                fontWeight = FontWeight.Medium

            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Image(
                painter =
                    painterResource(
                        R.drawable.ic_mapa
                    ),

                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }
    }
}