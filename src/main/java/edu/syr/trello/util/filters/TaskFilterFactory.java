package edu.syr.trello.util.filters;

import edu.syr.trello.model.TaskFilterRequest;

public class TaskFilterFactory {
    public static TaskFilter getFilter(TaskFilterRequest filter) {

        TaskFilter taskFilter = new BaseTaskFilter();

        if (filter.getTitle() != null) {
            taskFilter = new TitleFilterDecorator(taskFilter, filter.getTitle());
        }

        if (filter.getDescription() != null) {
            taskFilter = new DescriptionFilterDecorator(taskFilter, filter.getDescription());
        }

        if (filter.getState() != null) {
            taskFilter = new StateFilterDecorator(taskFilter, filter.getState());
        }

        if (filter.getCreatedDateTimeFrom() != null) {
            taskFilter = new CreatedDateTimeFromFilterDecorator(taskFilter, filter.getCreatedDateTimeFrom());
        }


        if (filter.getAssignedUserIds() != null && !filter.getAssignedUserIds().isEmpty()) {
            taskFilter = new AssignedUserIdsFilterDecorator(taskFilter, filter.getAssignedUserIds());
        }

        return taskFilter;
    }
}
