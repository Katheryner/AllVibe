package com.allvibe.all_vibe.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotBlank(message = "The name of the user is required")
    private String username;
    @NotBlank(message = "The email is required")
    private String email;
    @NotBlank(message = "The password is required")
    private String password;
    
    private boolean isAdmin;
    
}
