package com.everpath.presentation.everpath.state

/**
 * Estado encargado de representar la transformación
 * aplicada al mapa.
 *
 * En el futuro permitirá implementar zoom, cámara,
 * desplazamiento del viewport y otras herramientas
 * avanzadas sin modificar las coordenadas reales
 * almacenadas en cada meta.
 */
data class MapViewportState(

    val offsetX: Float = 0f,
    val offsetY: Float = 0f,
    val scale: Float = 1f

)