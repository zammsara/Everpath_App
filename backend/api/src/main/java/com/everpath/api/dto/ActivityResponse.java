package com.everpath.api.dto;

import com.everpath.api.domain.enums.ActivityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO encargado de devolver
 * información de actividades
 * hacia Android.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityResponse {

    private String id;

    private String goalId;

    private String title;

    private String description;

    private ActivityStatus status;

    private Boolean xpGranted;

    private LocalDateTime createdAt;
}