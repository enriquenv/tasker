package com.tasker.tasker.category;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    // DEPENDENCY INJECTION ---->
    private CategoryRepository categoryRepository;

    CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    // <---- DEPENDENCY INJECTION

    // *** For GET /categories (get all categories)
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    // *** For GET /categories/:id (get specific category)
    public Optional<Category> findById(Long id) {
        Optional<Category> foundCategory = this.categoryRepository.findById(id);
        return foundCategory;
    }

    // *** For POST /categories (create new category)
    public Category createCategory(CreateCategoryDTO data) {
        Category newCategory = new Category();
        newCategory.setCategory(data.getCategory().trim());
        Category savedCategory = this.categoryRepository.save(newCategory);
        return savedCategory;
    }

    // *** For DELETE /categories/:id (delete specific category)
    public void deleteById(Long id) {
        this.categoryRepository.deleteById(id);
    }

    // *** For PUT /categories/:id (update specific category)
    public Optional<Category> updateById(Long id, CreateCategoryDTO data) {
        Optional<Category> foundCategory = this.findById(id);
        if (foundCategory.isEmpty()) {
            return Optional.empty();
        }

        Category categoryToUpdate = foundCategory.get();
        categoryToUpdate.setCategory(data.getCategory());
        Category updatedCategory = this.categoryRepository.save(categoryToUpdate);
        return Optional.of(updatedCategory);
    }

}