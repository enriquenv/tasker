package com.tasker.tasker.category;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    // DEPENDENCY INJECTION ---->
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    // <---- DEPENDENCY INJECTION

    // *** GET /categories (get all categories)
    @GetMapping
    public ResponseEntity<ArrayList<String>> getAllCategories() {
        ArrayList<String> categories = this.categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // *** POST /categories (create new category)
    @PostMapping
    public ResponseEntity<String> createCategory(@Valid @RequestBody CreateCategoryDTO category) {
        String savedCategory = this.categoryService.createCategory(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    // *** PUT /categories/:id (edit specific category)
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable int id, @RequestBody CreateCategoryDTO category) {
        boolean updated = this.categoryService.updateCategoryByIndex(id, category.getCategory());
        if (updated) {
            return new ResponseEntity<>("Category updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }
    }

    // *** DELETE /categories/:id (delete specific category)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
        boolean deleted = categoryService.deleteCategoryByIndex(id);
        if (deleted) {
            return new ResponseEntity<>("Category deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }
    }
}