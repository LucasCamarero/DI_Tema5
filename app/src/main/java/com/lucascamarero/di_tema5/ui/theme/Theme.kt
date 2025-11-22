package com.lucascamarero.di_tema5.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val lightScheme = lightColorScheme(

    // Color principal de la marca: botones destacados, iconos activos, switches, FABs, etc.
    primary = primaryLight,
    // Color del texto/iconos encima del primary (por ejemplo, texto blanco sobre botón azul).
    onPrimary = onPrimaryLight,
    // Fondo de contenedores que usan el color principal (cards, chips, etc. de tipo destacado).
    primaryContainer = primaryContainerLight,
    // Color del texto/iconos encima del primaryContainer.
    onPrimaryContainer = onPrimaryContainerLight,

    // Color de acento secundario: para elementos menos prominentes (chips, badges).
    secondary = secondaryLight,
    // Color del contenido sobre secondary.
    onSecondary = onSecondaryLight,
    // Fondo de contenedores con tono secundario.
    secondaryContainer = secondaryContainerLight,
    // Color del texto sobre secondaryContainer.
    onSecondaryContainer = onSecondaryContainerLight,

    // Color adicional para distinguir secciones o estados (ej. gráficos, ilustraciones, widgets).
    tertiary = tertiaryLight,
    // Color del texto/iconos sobre tertiary.
    onTertiary = onTertiaryLight,
    // Fondo de contenedores con color tertiary.
    tertiaryContainer = tertiaryContainerLight,
    // Color del texto sobre tertiaryContainer.
    onTertiaryContainer = onTertiaryContainerLight,

    // Color para errores (por ejemplo, textos o iconos de error).
    error = errorLight,
    // Color del texto sobre error (ej. blanco sobre rojo).
    onError = onErrorLight,
    // Fondo de contenedores con color de error (alertas, inputs con error).
    errorContainer = errorContainerLight,
    // Color del texto sobre errorContainer.
    onErrorContainer = onErrorContainerLight,

    // Color de fondo general de la app.
    background = backgroundLight,
    // Color del texto/iconos encima del fondo.
    onBackground = onBackgroundLight,

    // Color base para superficies (cards, sheets, app bars, etc.).
    surface = surfaceLight,
    // Color del texto/iconos sobre superficies normales.
    onSurface = onSurfaceLight,

    // Variante de surface usada para distinguir zonas secundarias (listas, secciones).
    surfaceVariant = surfaceVariantLight,
    // Color del texto/iconos sobre surfaceVariant.
    onSurfaceVariant = onSurfaceVariantLight,

    // Color de bordes, divisores o contornos sutiles.
    outline = outlineLight,
    // Variante más suave del outline para divisores menos prominentes.
    outlineVariant = outlineVariantLight,

    // Color semitransparente para overlays (diálogos, modales, menús desplegados).
    scrim = scrimLight,

    // Superficie inversa (por ejemplo, snackbar o bottom bar en modo light).
    inverseSurface = inverseSurfaceLight,
    // Color del contenido encima de inverseSurface.
    inverseOnSurface = inverseOnSurfaceLight,
    // Color alternativo de primary cuando el fondo es oscuro (snackbar sobre fondo claro).
    inversePrimary = inversePrimaryLight,

    // Versión más oscura del surface, usada para jerarquías profundas (modal, bottom sheet).
    surfaceDim = surfaceDimLight,
    // Versión más clara del surface, usada en zonas elevadas (app bar, top card).
    surfaceBright = surfaceBrightLight,

    // Niveles jerárquicos de superficie según la elevación o profundidad visual.
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

@Composable
fun DI_Tema5Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Ponemos el color dinámico a false para que se usen los colores de mi theme
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkScheme
        else -> lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}