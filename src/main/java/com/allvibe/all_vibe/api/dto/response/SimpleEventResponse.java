package com.allvibe.all_vibe.api.dto.response;

import java.time.LocalDateTime;

import com.allvibe.all_vibe.util.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleEventResponse {

    private Long id;
    private String name;
    private Status status;
    private LocalDateTime date;
    private int capacity;
    private String place;
    private String description;
    private String eventType;
}
