package com.tasker.tasker.task;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

@Service
public class TaskRepository {

    ArrayList<String> tasks = new ArrayList<>(Arrays.asList("Wake up", "Wash the dishes"));

    public ArrayList<String> findAll() {
        return this.tasks;
    }

    public String save(String task) {
        this.tasks.add(task);
        System.out.println("Tasks saved: " + tasks);
        return task;
    }

    public boolean updateByIndex(int index, String newTask) {
        if (index < 0 || index >= tasks.size()) {
            return false;
        }
        tasks.set(index, newTask);
        return true;
    }

    public boolean deleteByIndex(int index) {
        if (index < 0 || index >= tasks.size()) {
            return false;
        }
        tasks.remove(index);
        return true;
    }
}
