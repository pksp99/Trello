package edu.syr.trello.model;

import lombok.Data;

import java.util.List;

@Data
public class TaskRequest {

    private String title;

    private String description;

    private String userId;

    private TaskStateEnum state = TaskStateEnum.TODO;

    private List<String> assignedUsers;

}
