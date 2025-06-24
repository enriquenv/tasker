package com.tasker.tasker.task;

//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

//import jakarta.validation.Valid;

@Service
public class TaskService {

    // DEPENDENCY INJECTION ---->
    private TaskRepository taskRepository;

    TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    // <---- DEPENDENCY INJECTION

    // *** For GET /todos (get all tasks)
    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    // *** For GET /todos/:id (get specific task)
    public Optional<Task> findById(Long id) {
        Optional<Task> foundTask = this.taskRepository.findById(id);
        return foundTask;
    }

    // *** For POST /todos (create new task)
    public Task createTask(CreateTaskDTO data) {
        Task newTask = new Task();
        newTask.setTask(data.getTask().trim());
        newTask.setDueDate(data.getDueDate());
        newTask.setIsCompleted(data.isCompleted());
        Task savedTask = this.taskRepository.save(newTask);
        return savedTask;
    }

    // *** For DELETE /todos/:id (delete specific task)
    public void deleteById(Long id) {
        this.taskRepository.deleteById(id);
    }

    // *** For PATCH /todos/:id (update specific task)
    public Optional<Task> updateById(Long id, UpdateTaskDTO data) {
        Optional<Task> foundTask = this.findById(id);

        if (foundTask.isEmpty()) {
            return foundTask;
        }

        Task taskFromDB = foundTask.get();

        if (data.getTask() != null) {
            taskFromDB.setTask(data.getTask());
        }

        if (data.getDueDate() != null) {
            taskFromDB.setDueDate(data.getDueDate());
        }

        if (data.getIsCompleted() != null) {
            taskFromDB.setIsCompleted(data.getIsCompleted());
        }

        this.taskRepository.save(taskFromDB);
        return Optional.of(taskFromDB);
    }

    /*
     * public String defaultTask() {
     * return "This is a default to do task.";
     * }
     * 
     * public String taskByWord(String word) {
     * return "You need to do " + word + ".";
     * }
     * 
     * 
     * public String createTask(CreateTaskDTO entity) {
     * String task = entity.getTask();
     * return this.taskRepository.save(task);
     * // throw new
     * UnsupportedOperationException("Unimplemented method 'createTask'");
     * }
     * 
     * 
     * public boolean updateTaskByIndex(int index, String newTask) {
     * return this.taskRepository.updateByIndex(index, newTask);
     * }
     * 
     * public boolean deleteTaskByIndex(int index) {
     * return this.taskRepository.deleteByIndex(index);
     * }
     */

}
