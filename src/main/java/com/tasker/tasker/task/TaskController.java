package com.tasker.tasker.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.NameNotFoundException;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
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

    // *** GET /todos (get all tasks)
    @GetMapping
    public ResponseEntity<List<Task>> getMethodName() {
        List<Task> allTasks = this.taskService.findAll();
        return new ResponseEntity<>(allTasks, HttpStatus.OK);
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
    public ResponseEntity<String> deleteMethodName(@PathVariable Long id) {
        Optional<Task> foundTask = this.taskService.findById(id);
        if (foundTask.isPresent()) {
            this.taskService.deleteById(id);
            return new ResponseEntity<String>("Task deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // *** PATCH /todos/:id (update specific task)
    @PatchMapping("/{id}")
    public ResponseEntity<Task> patchMethodName(@PathVariable Long id, @Valid @RequestBody UpdateTaskDTO data)
            throws NotFoundException {
        Optional<Task> result = this.taskService.updateById(id, data);

        Task updated = result.orElseThrow(() -> new NotFoundException());

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    /*
     * @GetMapping
     * public String getMethodName() {
     * // return "You have to do this.";
     * return this.taskService.defaultTask();
     * }
     */

    /*
     * @GetMapping("/{word}")
     * public String getMethodName2(@PathVariable String word) {
     * // return "You have to do this.";
     * return this.taskService.taskByWord(word);
     * }
     */

    // *** PUT /todos/:id (edit specific task)
    /*
     * @PutMapping("/{id}")
     * public ResponseEntity<String> putMethodName(@PathVariable int
     * id, @RequestBody CreateTaskDTO entity) {
     * boolean updated = this.taskService.updateTaskByIndex(id, entity.getTask());
     * if (updated) {
     * return new ResponseEntity<>("Task updated", HttpStatus.OK);
     * } else {
     * return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
     * }
     * }
     * 
     * 
     * @PutMapping("path/{id}")
     * public String putMethodName(@PathVariable String id, @RequestBody String
     * entity) {
     * //TODO: process PUT request
     * 
     * return entity;
     * }
     * 
     * 
     * @DeleteMapping("/{id}")
     * public ResponseEntity<String> deleteTask(@PathVariable int id) {
     * boolean deleted = this.taskService.deleteTaskByIndex(id);
     * if (deleted) {
     * return new ResponseEntity<>("Task deleted", HttpStatus.OK);
     * } else {
     * return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
     * }
     * }
     */

}
