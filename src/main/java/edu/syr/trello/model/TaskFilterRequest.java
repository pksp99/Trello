package edu.syr.trello.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TaskFilterRequest {
    private String title;
    private String description;
    private TaskStateEnum state;
    private LocalDateTime createdDateTimeFrom;
    private List<String> assignedUserIds;

}