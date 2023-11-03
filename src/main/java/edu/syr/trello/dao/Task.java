package edu.syr.trello.dao;

import edu.syr.trello.model.TaskStateEnum;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "tasks")
@Builder
public class Task {
    @Id
    private String id;

    private String title;
    private String description;
    private String userId;

    private TaskStateEnum state = TaskStateEnum.TODO;

    @CreatedDate
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    private LocalDateTime lastModifiedDateTime;

    private List<String> assignedUserIds;

}
