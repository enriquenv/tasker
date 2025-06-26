package com.tasker.tasker.task;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tasker.tasker.category.Category;
import com.tasker.tasker.category.CategoryRepository;

@Service
public class TaskService {

    // DEPENDENCY INJECTION ---->
    private TaskRepository taskRepository;
    private CategoryRepository categoryRepository;

    TaskService(TaskRepository taskRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
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
        newTask.setIsCompleted(data.getIsCompleted());

        if (data.getCategoryId() != null) {
            Optional<Category> category = this.categoryRepository.findById(data.getCategoryId());
            if (category.isPresent()) {
                newTask.setCategory(category.get());
            }
        }

        return this.taskRepository.save(newTask);
    }

    // *** For DELETE /todos/:id (delete specific task)
    public void deleteById(Long id) {
        this.taskRepository.deleteById(id);
    }

    // *** For PATCH /todos/:id (update specific task)
    public Optional<Task> updateById(Long id, UpdateTaskDTO data) {
        Optional<Task> foundTask = this.findById(id);

        if (foundTask.isEmpty()) {
            return Optional.empty();
        }

        Task taskFromDB = foundTask.get();

        if (data.getTask() != null) {
            taskFromDB.setTask(data.getTask().trim());
        }

        if (data.getDueDate() != null) {
            taskFromDB.setDueDate(data.getDueDate().trim());
        }

        if (data.getIsCompleted() != null) {
            taskFromDB.setIsCompleted(data.getIsCompleted());
        }

        if (data.getCategoryId() != null) {
            Optional<Category> category = this.categoryRepository.findById(data.getCategoryId());
            if (category.isPresent()) {
                taskFromDB.setCategory(category.get());
            }
        }

        this.taskRepository.save(taskFromDB);
        return Optional.of(taskFromDB);
    }

    // *** For GET /todos?category={} (get all tasks with a specific category)
    public List<Task> findByCategory(String categoryName) {
        return this.taskRepository.findByCategory_Category(categoryName);
    }

}
