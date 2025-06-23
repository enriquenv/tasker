package com.tasker.tasker.category;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ArrayList<String> getAllCategories() {
        return categoryRepository.findAll();
    }

    public String createCategory(CreateCategoryDTO entity) {
        String category = entity.getCategory();
        return this.categoryRepository.save(category);
    }

    public boolean updateCategoryByIndex(int index, String category) {
        return categoryRepository.updateByIndex(index, category);
    }

    public boolean deleteCategoryByIndex(int index) {
        return categoryRepository.deleteByIndex(index);
    }
}