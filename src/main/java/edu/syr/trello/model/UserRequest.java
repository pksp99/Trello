package edu.syr.trello.model;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Validated
public class UserRequest {
    @NotBlank
    private String username;

    @Email
    private String email;
}
