package com.tasker.tasker.task;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<ArrayList<String>> getAllTasks() {
        ArrayList<String> tasks = this.taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
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

    // *** POST /todos (create new task)
    @PostMapping
    public ResponseEntity<String> postMethodName(@Valid @RequestBody CreateTaskDTO entity) {
        System.out.println(entity.getTask());
        String savedTask = this.taskService.createTask(entity);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    // *** PUT /todos/:id (edit specific task)
    @PutMapping("/{id}")
    public ResponseEntity<String> putMethodName(@PathVariable int id, @RequestBody CreateTaskDTO entity) {
        boolean updated = this.taskService.updateTaskByIndex(id, entity.getTask());
        if (updated) {
            return new ResponseEntity<>("Task updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
        }
    }

    /*
     * @PutMapping("path/{id}")
     * public String putMethodName(@PathVariable String id, @RequestBody String
     * entity) {
     * //TODO: process PUT request
     * 
     * return entity;
     * }
     */

    // *** DELETE /todos/:id (delete specific task)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        boolean deleted = this.taskService.deleteTaskByIndex(id);
        if (deleted) {
            return new ResponseEntity<>("Task deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
        }
    }

}
