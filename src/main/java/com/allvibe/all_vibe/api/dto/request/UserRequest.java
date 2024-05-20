package com.allvibe.all_vibe.api.dto.request;

import com.allvibe.all_vibe.util.enums.RoleUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Rol is required.")
    private RoleUser role;

}
