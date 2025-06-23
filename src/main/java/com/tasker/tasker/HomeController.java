package com.tasker.tasker;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class HomeController {

    /*
     * @GetMapping("path")
     * public String getMethodName(@RequestParam String param) {
     * return new String();
     * }
     */

    @GetMapping()
    public String getMethodName() {
        return "Welcome to Tasker!";
    }

    @PostMapping()
    public String postMethodName() {
        return "Goodbye from Tasker!";
    }

    /*
     * @PostMapping("path")
     * public String postMethodName(@RequestBody String entity) {
     * //TODO: process POST request
     * 
     * return entity;
     * }
     */

}
