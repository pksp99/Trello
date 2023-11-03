package edu.syr.trello.util.filters;

import edu.syr.trello.dao.Task;

import java.util.List;

public class AssignedUserIdsFilterDecorator implements TaskFilter {
    private final TaskFilter taskFilter;
    private final List<String> assignedUserIds;

    public AssignedUserIdsFilterDecorator(TaskFilter taskFilter, List<String> assignedUserIds) {
        this.taskFilter = taskFilter;
        this.assignedUserIds = assignedUserIds;
    }

    @Override
    public List<Task> filterTasks(List<Task> tasks) {
        List<Task> filteredTasks = taskFilter.filterTasks(tasks);
        return filteredTasks.stream()
                .filter(task -> task.getAssignedUserIds() != null &&
                        task.getAssignedUserIds().containsAll(assignedUserIds))
                .toList();
    }
}

