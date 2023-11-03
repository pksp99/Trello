package edu.syr.trello.util.filters;

import edu.syr.trello.dao.Task;

import java.util.List;

public class BaseTaskFilter implements TaskFilter {

    @Override
    public List<Task> filterTasks(List<Task> tasks) {
        return tasks;
    }
}