package com.everpath.api.service.progress;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Catálogo oficial de achievements
 * disponibles dentro de Everpath.
 *
 * Debe mantenerse sincronizado
 * con Android.
 */
public final class AchievementCatalog {

    private AchievementCatalog() {
    }

    @Getter
    @AllArgsConstructor
    public static class Definition {

        private String id;
        private String title;
        private String description;
    }

    public static final List<Definition> ACHIEVEMENTS =
            List.of(

                    new Definition(
                            "ACTIVITY_1",
                            "Primer Paso",
                            "Completa tu primera actividad."
                    ),

                    new Definition(
                            "ACTIVITY_10",
                            "Explorador",
                            "Completa 10 actividades."
                    ),

                    new Definition(
                            "ACTIVITY_50",
                            "Maestro de la Acción",
                            "Completa 50 actividades."
                    ),

                    new Definition(
                            "GOAL_1",
                            "Arquitecto",
                            "Completa tu primera meta."
                    ),

                    new Definition(
                            "GOAL_10",
                            "Constructor de Destinos",
                            "Completa 10 metas."
                    ),

                    new Definition(
                            "XP_100",
                            "Aprendiz",
                            "Alcanza 100 XP."
                    ),

                    new Definition(
                            "XP_500",
                            "Experimentado",
                            "Alcanza 500 XP."
                    ),

                    new Definition(
                            "XP_1000",
                            "Leyenda Emergente",
                            "Alcanza 1000 XP."
                    ),

                    new Definition(
                            "LEVEL_5",
                            "Veterano",
                            "Alcanza el nivel 5."
                    )
            );
}