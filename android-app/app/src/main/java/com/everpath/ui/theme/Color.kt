package com.everpath.ui.theme

import androidx.compose.ui.graphics.Color

/*
 * Paleta principal Everpath
 * Base visual: lila claro, lavanda suave y morado elegante.
 */

val EverpathPrimary = Color(0xFF6C4DF4)
val EverpathSecondary = Color(0xFF9B87F5)
val EverpathAccent = Color(0xFFF2C66D)
val EverpathLavender = Color(0xFFE6DDFB)

/*
 * Fondos y superficies
 * Fondos en gama lila clara.
 */

val EverpathBackground = Color(0xFFFDF7FF)

/*
 * Fondo del mapa.
 * Lila perlado muy claro para que no se mezcle con las áreas moradas.
 */
val EverpathMapBackground = Color(0xFFDFDFDF)

val EverpathSurface = Color(0xFFF4ECFF)
val EverpathSurfaceSoft = Color(0xFFF8F3FF)
val EverpathSurfaceVariant = Color(0xFFE9DFFF)
val EverpathDialogSurface = Color(0xFFF3ECFF)
val EverpathBottomBar = Color(0xFFF0E8FF)
val EverpathBorder = Color(0xFFD9C9F2)

/*
 * Textos
 */

val EverpathTextPrimary = Color(0xFF1D1B2E)
val EverpathTextSecondary = Color(0xFF6B6280)
val EverpathTextDisabled = Color(0xFFA7A0B8)

/*
 * Estados generales
 */

val EverpathSuccess = Color(0xFF5B9F6E)
val EverpathWarning = Color(0xFFD9A85F)
val EverpathError = Color(0xFFD76A5E)

/*
 * Blanco y negro.
 * EverpathWhite NO debe usarse como fondo.
 * Úsalo solo para texto o iconos sobre colores oscuros.
 */

val EverpathWhite = Color(0xFFFFFFFF)
val EverpathBlack = Color(0xFF000000)

/*
 * Estados de metas
 */

val EverpathStatusActive = Color(0xFF6C4DF4)
val EverpathStatusCompleted = Color(0xFF5B9F6E)
val EverpathStatusLocked = Color(0xFF8D879C)
val EverpathStatusArchived = Color(0xFF6B7280)

/*
 * Fondos de estados
 */

val EverpathStatusActiveContainer = Color(0xFFE9DFFF)
val EverpathStatusCompletedContainer = Color(0xFFE4F2E8)
val EverpathStatusLockedContainer = Color(0xFFECE7F3)
val EverpathStatusArchivedContainer = Color(0xFFE7E5EF)

/*
 * Colores por área de vida
 * Basados en los iconos que enviaste, pero suavizados.
 */

val EverpathAreaHealth = Color(0xFF4E6F98)
val EverpathAreaStudies = Color(0xFF6FA06F)
val EverpathAreaCareer = Color(0xFF47687B)
val EverpathAreaFinance = Color(0xFF9C8A57)
val EverpathAreaRelationships = Color(0xFF94667E)
val EverpathAreaCreativity = Color(0xFFA06A4F)
val EverpathAreaTravel = Color(0xFF6C5A84)

/*
 * Fondos suaves por área
 * Mantienen identidad del área, pero combinan con el fondo lila claro.
 */

val EverpathAreaHealthContainer = Color(0xFFEAF2FF)
val EverpathAreaStudiesContainer = Color(0xFFEAF4EA)
val EverpathAreaCareerContainer = Color(0xFFEAF1F5)
val EverpathAreaFinanceContainer = Color(0xFFF1EBD8)
val EverpathAreaRelationshipsContainer = Color(0xFFF0E3EA)
val EverpathAreaCreativityContainer = Color(0xFFF0E2DA)
val EverpathAreaTravelContainer = Color(0xFFE2D7F0)

/*
 * Fondos especiales para acciones visuales
 * Útiles para botones o cards como Editar, Agregar y Eliminar.
 */

val EverpathEditContainer = Color(0xFFE9DFFF)
val EverpathAddContainer = Color(0xFFEDE5FF)
val EverpathDeleteContainer = Color(0xFFF2DDD8)

/*
 * Compatibilidad con nombres antiguos del template.
 */

val Purple80 = EverpathSecondary
val PurpleGrey80 = EverpathLavender
val Pink80 = EverpathAccent

val Purple40 = EverpathPrimary
val PurpleGrey40 = EverpathTextSecondary
val Pink40 = EverpathError