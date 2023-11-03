package edu.syr.trello.util.filters;

import edu.syr.trello.dao.Task;

import java.util.List;

public class DescriptionFilterDecorator implements TaskFilter {
    private final TaskFilter taskFilter;
    private final String description;

    public DescriptionFilterDecorator(TaskFilter taskFilter, String description) {
        this.taskFilter = taskFilter;
        this.description = description;
    }

    @Override
    public List<Task> filterTasks(List<Task> tasks) {
        List<Task> filteredTasks = taskFilter.filterTasks(tasks);
        return filteredTasks.stream()
                .filter(task -> task.getDescription() != null && task.getDescription().toLowerCase().contains(description.toLowerCase()))
                .toList();
    }
}
