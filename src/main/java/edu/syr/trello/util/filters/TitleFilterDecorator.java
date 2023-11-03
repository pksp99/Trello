package edu.syr.trello.util.filters;

import edu.syr.trello.dao.Task;

import java.util.List;

public class TitleFilterDecorator implements TaskFilter {
    private final TaskFilter taskFilter;
    private final String title;

    public TitleFilterDecorator(TaskFilter taskFilter, String title) {
        this.taskFilter = taskFilter;
        this.title = title;
    }

    @Override
    public List<Task> filterTasks(List<Task> tasks) {
        List<Task> filteredTasks = taskFilter.filterTasks(tasks);
        return filteredTasks.stream()
                .filter(task -> task.getTitle() != null && task.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }
}
