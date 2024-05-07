package com.allvibe.all_vibe.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpleUserResponse {
    private Long id;
    private String username;
    private String email;
    private String password;
    private boolean isAdmin;

}