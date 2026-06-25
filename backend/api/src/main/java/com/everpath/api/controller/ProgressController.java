package com.everpath.api.controller;

import com.everpath.api.dto.progress.AchievementResponse;
import com.everpath.api.dto.progress.UserProgressResponse;
import com.everpath.api.service.progress.ProgressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST encargado
 * de exponer información relacionada
 * con progreso global y achievements.
 *
 * Actúa como punto de entrada
 * para Android.
 */
@RestController
@RequestMapping("/api/v1/progress")
@RequiredArgsConstructor
@Tag(
        name = "Progress",
        description = "Operaciones relacionadas con progreso y achievements"
)
public class ProgressController {

    private final ProgressService progressService;


    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Obtener progreso",
            description = "Obtiene XP, nivel y progreso actual de un usuario"
    )
    public UserProgressResponse getUserProgress(

            @PathVariable
            Long userId

    ) {

        return progressService.getUserProgress(
                userId
        );
    }


    @GetMapping("/{userId}/achievements")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Obtener logros",
            description = "Obtiene todos los logros desbloqueados por un usuario"
    )
    public List<AchievementResponse> getAchievements(

            @PathVariable
            Long userId

    ) {

        return progressService.getAchievements(
                userId
        );
    }
}