package com.everpath.api.controller;

import com.everpath.api.dto.activity.ActivityResponse;
import com.everpath.api.dto.activity.CreateActivityRequest;
import com.everpath.api.dto.activity.UpdateActivityRequest;
import com.everpath.api.service.activity.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST encargado de exponer
 * todas las operaciones relacionadas
 * con actividades dentro de Everpath.
 *
 * Actúa como punto de entrada para
 * el cliente Android.
 */
@RestController
@RequestMapping("/api/v1/activities")
@RequiredArgsConstructor
@Tag(
        name = "Activities",
        description = "Operaciones relacionadas con actividades"
)
public class ActivityController {

    private final ActivityService activityService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Crear actividad",
            description = "Permite registrar una nueva actividad"
    )
    public ActivityResponse createActivity(

            @Valid
            @RequestBody
            CreateActivityRequest request

    ) {

        return activityService.createActivity(
                request
        );
    }


    @GetMapping("/{activityId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Obtener actividad",
            description = "Obtiene una actividad mediante su identificador"
    )
    public ActivityResponse getActivityById(

            @PathVariable
            String activityId

    ) {

        return activityService.getActivityById(
                activityId
        );
    }


    @GetMapping("/goal/{goalId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Listar actividades por meta",
            description = "Obtiene todas las actividades de una meta"
    )
    public List<ActivityResponse> getActivitiesByGoal(

            @PathVariable
            String goalId

    ) {

        return activityService.getActivitiesByGoal(
                goalId
        );
    }


    @PutMapping("/{activityId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Actualizar actividad",
            description = "Actualiza la información de una actividad"
    )
    public ActivityResponse updateActivity(

            @PathVariable
            String activityId,

            @Valid
            @RequestBody
            UpdateActivityRequest request

    ) {

        return activityService.updateActivity(
                activityId,
                request
        );
    }


    @DeleteMapping("/{activityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Eliminar actividad",
            description = "Elimina una actividad del sistema"
    )
    public void deleteActivity(

            @PathVariable
            String activityId

    ) {

        activityService.deleteActivity(
                activityId
        );
    }
}