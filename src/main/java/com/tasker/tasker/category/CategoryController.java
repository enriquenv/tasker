package com.tasker.tasker.category;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    // DEPENDENCY INJECTION ---->
    private final CategoryService categoryService;

    CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    // <---- DEPENDENCY INJECTION

    // *** GET /categories (get all categories)
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = this.categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // *** GET /categories/:id (get specific category)
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> foundCategory = this.categoryService.findById(id);
        if (foundCategory.isPresent()) {
            return new ResponseEntity<>(foundCategory.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // *** POST /categories (create new category)
    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CreateCategoryDTO data) {
        Category newCategory = this.categoryService.createCategory(data);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    // *** PUT /categories/:id (edit specific category)
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @Valid @RequestBody CreateCategoryDTO data) {
        Optional<Category> updatedCategory = this.categoryService.updateById(id, data);

        if (updatedCategory.isPresent()) {
            return new ResponseEntity<>(updatedCategory.get(), HttpStatus.OK);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
    }

    // *** DELETE /categories/:id (delete specific category)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        Optional<Category> foundCategory = this.categoryService.findById(id);
        if (foundCategory.isPresent()) {
            this.categoryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}