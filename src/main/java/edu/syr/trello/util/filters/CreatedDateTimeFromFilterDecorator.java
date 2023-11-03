package edu.syr.trello.util.filters;

import edu.syr.trello.dao.Task;

import java.time.LocalDateTime;
import java.util.List;

public class CreatedDateTimeFromFilterDecorator implements TaskFilter {
    private final TaskFilter taskFilter;
    private final LocalDateTime createdDateTimeFrom;

    public CreatedDateTimeFromFilterDecorator(TaskFilter taskFilter, LocalDateTime createdDateTimeFrom) {
        this.taskFilter = taskFilter;
        this.createdDateTimeFrom = createdDateTimeFrom;
    }

    @Override
    public List<Task> filterTasks(List<Task> tasks) {
        List<Task> filteredTasks = taskFilter.filterTasks(tasks);
        return filteredTasks.stream()
                .filter(task -> task.getCreatedDateTime() != null &&
                        task.getCreatedDateTime().isAfter(createdDateTimeFrom))
                .toList();
    }
}
