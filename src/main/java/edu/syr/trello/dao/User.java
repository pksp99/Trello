package edu.syr.trello.dao;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Document(collection = "users")
@Builder
public class User {
    @Id
    private String id;

    @NotBlank
    private String username;

    @Email
    private String email;

    private List<String> assignedTaskIds;
}
