package com.allvibe.all_vibe.api.dto.request;

import java.time.LocalDateTime;

import com.allvibe.all_vibe.util.enums.Status;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {
    @NotBlank(message = "El nombre del evento es requerido")
    private String name;
    // @NotBlank(message = "El estado es requerido.")
    private Status status;
    @NotNull
    @FutureOrPresent(message = "La fecha no puede ser del pasado.")
    private LocalDateTime date;
    @NotNull
    @DecimalMin(value = "0.01", message = "El valor de la capacidad debe ser mayor a 0.")
    private int capacity;
    @NotBlank(message = "El nombre es requerido.")
    private String place;
    @NotBlank(message = "La descripcion es Requerida.")
    private String description;
    @NotBlank(message = "El tipo de evento es requerido.")
    private String eventType;

}
