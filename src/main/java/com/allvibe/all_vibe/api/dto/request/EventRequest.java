package com.allvibe.all_vibe.api.dto.request;

import java.time.LocalDateTime;

import com.allvibe.all_vibe.util.enums.Status;
import com.allvibe.all_vibe.util.enums.TypeEvent;

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
    @NotBlank(message = "The name of the event is required")
    private String name;
    @NotNull(message = "Status is required.")
    private Status status;
    @NotNull(message = "Date is required")
    @FutureOrPresent(message = "The date cannot be in the past.")
    private LocalDateTime date;
    @NotNull(message = "Capacity required.")
    @DecimalMin(value = "0.01", message = "The value of capacity must be greater than 0.")
    private int capacity;
    @NotBlank(message = "Name required.")
    private String place;
    @NotBlank(message = "Description is required.")
    private String description;
    @NotNull(message = "Event type is required.")
    private TypeEvent eventType;
}
