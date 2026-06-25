package com.everpath.api.service.progress;

import org.springframework.stereotype.Component;

/**
 * Componente encargado de centralizar
 * toda la lógica de cálculo relacionada
 * con niveles y progreso del usuario.
 *
 * Permite reutilizar reglas de negocio
 * sin duplicarlas en múltiples servicios.
 */
@Component
public class LevelCalculator {

    /**
     * Obtiene el nivel actual
     * a partir de la XP acumulada.
     */
    public int calculateLevel(
            int xp
    ) {

        if (xp >= 1000) {
            return 5;
        }

        if (xp >= 500) {
            return 4;
        }

        if (xp >= 250) {
            return 3;
        }

        if (xp >= 100) {
            return 2;
        }

        return 1;
    }

    /**
     * XP obtenida dentro
     * del nivel actual.
     */
    public int calculateCurrentLevelXp(
            int xp
    ) {

        if (xp >= 1000) {
            return xp - 1000;
        }

        if (xp >= 500) {
            return xp - 500;
        }

        if (xp >= 250) {
            return xp - 250;
        }

        if (xp >= 100) {
            return xp - 100;
        }

        return xp;
    }

    /**
     * XP requerida para
     * alcanzar el siguiente nivel.
     */
    public int calculateRequiredXpForNextLevel(
            int xp
    ) {

        if (xp >= 1000) {
            return 1000;
        }

        if (xp >= 500) {
            return 500;
        }

        if (xp >= 250) {
            return 250;
        }

        if (xp >= 100) {
            return 150;
        }

        return 100;
    }

    /**
     * Porcentaje visual
     * del progreso actual.
     */
    public float calculateProgress(
            int xp
    ) {

        if (xp >= 1000) {
            return 1f;
        }

        if (xp >= 500) {
            return (xp - 500) / 500f;
        }

        if (xp >= 250) {
            return (xp - 250) / 250f;
        }

        if (xp >= 100) {
            return (xp - 100) / 150f;
        }

        return xp / 100f;
    }
}