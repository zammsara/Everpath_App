package com.everpath.api.controller;

import com.everpath.api.dto.CreateGoalRequest;
import com.everpath.api.dto.GoalResponse;
import com.everpath.api.dto.UpdateGoalRequest;
import com.everpath.api.service.GoalService;
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
 * con metas dentro de Everpath.
 *
 * Actúa como punto de entrada para
 * el cliente Android.
 */
@RestController
@RequestMapping("/api/v1/goals")
@RequiredArgsConstructor
@Tag(
        name = "Goals",
        description = "Operaciones relacionadas con metas"
)
public class GoalController {

    private final GoalService goalService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Crear meta",
            description = "Permite registrar una nueva meta"
    )
    public GoalResponse createGoal(

            @Valid
            @RequestBody
            CreateGoalRequest request

    ) {

        return goalService.createGoal(
                request
        );
    }



    @GetMapping("/{goalId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Obtener meta",
            description = "Obtiene una meta mediante su identificador"
    )
    public GoalResponse getGoalById(

            @PathVariable
            String goalId

    ) {

        return goalService.getGoalById(
                goalId
        );
    }



    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Listar metas por usuario",
            description = "Obtiene todas las metas de un usuario"
    )
    public List<GoalResponse> getGoalsByUser(

            @PathVariable
            Long userId

    ) {

        return goalService.getGoalsByUser(
                userId
        );
    }



    @PutMapping("/{goalId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Actualizar meta",
            description = "Actualiza la información de una meta"
    )
    public GoalResponse updateGoal(

            @PathVariable
            String goalId,

            @Valid
            @RequestBody
            UpdateGoalRequest request

    ) {

        return goalService.updateGoal(
                goalId,
                request
        );
    }


    @DeleteMapping("/{goalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Eliminar meta",
            description = "Elimina una meta del sistema"
    )
    public void deleteGoal(

            @PathVariable
            String goalId

    ) {

        goalService.deleteGoal(
                goalId
        );
    }
}