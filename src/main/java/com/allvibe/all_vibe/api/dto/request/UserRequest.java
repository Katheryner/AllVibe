package com.allvibe.all_vibe.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Long id;
    private String username;
    private String email;
    private String password;
    private boolean isAdmin;
    
}