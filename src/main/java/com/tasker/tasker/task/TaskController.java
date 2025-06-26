package com.tasker.tasker.task;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/todos")
public class TaskController {

    // DEPENDENCY INJECTION ---->
    private final TaskService taskService;

    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    // <---- DEPENDENCY INJECTION

    // *** GET /todos (get all tasks OR filter by category)
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(required = false) String category) {
        List<Task> tasks;
        if (category != null && !category.isBlank()) {
            tasks = this.taskService.findByCategory(category);
        } else {
            tasks = this.taskService.findAll();
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // *** GET /todos/:id (get specific task)
    @GetMapping("/{id}")
    public ResponseEntity<Task> getMethodName(@PathVariable Long id) {
        Optional<Task> foundTask = this.taskService.findById(id);
        if (foundTask.isPresent()) {
            return new ResponseEntity<>(foundTask.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // *** POST /todos (create new task)
    @PostMapping
    public ResponseEntity<Task> postMethodName(@Valid @RequestBody CreateTaskDTO data) {
        // System.out.println(entity.getTask());
        Task savedTask = this.taskService.createTask(data);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    // *** DELETE /todos/:id (delete specific task)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMethodName(@PathVariable Long id) {
        Optional<Task> foundTask = this.taskService.findById(id);
        if (foundTask.isPresent()) {
            this.taskService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // *** PATCH /todos/:id (update specific task)
    @PatchMapping("/{id}")
    public ResponseEntity<Task> patchMethodName(@PathVariable Long id, @Valid @RequestBody UpdateTaskDTO data) {
        Optional<Task> result = this.taskService.updateById(id, data);

        Task updated = result.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

}
