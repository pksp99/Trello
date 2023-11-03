package edu.syr.trello.util.filters;

import edu.syr.trello.dao.Task;
import edu.syr.trello.model.TaskStateEnum;

import java.util.List;

public class StateFilterDecorator implements TaskFilter {
    private final TaskFilter taskFilter;
    private final TaskStateEnum state;

    public StateFilterDecorator(TaskFilter taskFilter, TaskStateEnum state) {
        this.taskFilter = taskFilter;
        this.state = state;
    }

    @Override
    public List<Task> filterTasks(List<Task> tasks) {
        List<Task> filteredTasks = taskFilter.filterTasks(tasks);
        return filteredTasks.stream()
                .filter(task -> task.getState() == state)
                .toList();
    }
}
