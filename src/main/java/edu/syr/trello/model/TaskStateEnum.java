package edu.syr.trello.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskStateEnum {
    TODO("TODO"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE");

    private final String value;

    TaskStateEnum(String value) {
        this.value = value;
    }

    public static TaskStateEnum fromString(String text) {
        for (TaskStateEnum state : TaskStateEnum.values()) {
            if (state.value.equals(text)) {
                return state;
            }
        }
        return TODO;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }
}