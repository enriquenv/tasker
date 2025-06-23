package com.tasker.tasker.task;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

//import jakarta.validation.Valid;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public String defaultTask() {
        return "This is a default to do task.";
    }

    public String taskByWord(String word) {
        return "You need to do " + word + ".";
    }

    public String createTask(CreateTaskDTO entity) {
        String task = entity.getTask();
        return this.taskRepository.save(task);
        // throw new UnsupportedOperationException("Unimplemented method 'createTask'");
    }

    public ArrayList<String> getAllTasks() {
        return this.taskRepository.findAll();
    }

    public boolean updateTaskByIndex(int index, String newTask) {
        return this.taskRepository.updateByIndex(index, newTask);
    }

    public boolean deleteTaskByIndex(int index) {
        return this.taskRepository.deleteByIndex(index);
    }

}
