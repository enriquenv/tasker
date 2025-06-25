package com.tasker.tasker.task;

import jakarta.validation.constraints.Pattern;

public class UpdateTaskDTO {

    @Pattern(regexp = ".*\\S.*", message = "Cannot be replaced with empty value")
    private String task;

    private String dueDate;

    private Boolean isCompleted;

    private Long categoryId;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

}
