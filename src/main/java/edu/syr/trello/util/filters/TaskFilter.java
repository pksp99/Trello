package edu.syr.trello.util.filters;

import edu.syr.trello.dao.Task;

import java.util.List;

public interface TaskFilter {
    List<Task> filterTasks(List<Task> tasks);
}
