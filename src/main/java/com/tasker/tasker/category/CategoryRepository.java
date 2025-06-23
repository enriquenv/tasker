package com.tasker.tasker.category;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

@Service
public class CategoryRepository {
    private ArrayList<String> categories = new ArrayList<>(Arrays.asList("school", "work"));

    public ArrayList<String> findAll() {
        return this.categories;
    }

    public String save(String category) {
        this.categories.add(category);
        return category;
    }

    public boolean updateByIndex(int index, String newCategory) {
        if (index < 0 || index >= categories.size()) {
            return false;
        }
        categories.set(index, newCategory);
        return true;
    }

    public boolean deleteByIndex(int index) {
        if (index < 0 || index >= categories.size()) {
            return false;
        }
        categories.remove(index);
        return true;
    }
}