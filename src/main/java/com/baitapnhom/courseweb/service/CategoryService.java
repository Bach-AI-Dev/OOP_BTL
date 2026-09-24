package com.baitapnhom.courseweb.service;

import com.baitapnhom.courseweb.dto.request.CategoryRequest;
import com.baitapnhom.courseweb.entity.Category;
import com.baitapnhom.courseweb.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createRequest(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("KHÔNG TÌM THẤY Category VỚI ID: " + id));
    }

    public String deleteCategory(String id) {
        categoryRepository.deleteById(id);
        return "Category ĐÃ ĐƯỢC XOÁ THÀNH CÔNG!";
    }

    public Category updateCategory(String id, CategoryRequest request) {
        Category category = getCategoryById(id);
        category.setName(request.getName());
        if (request.getDescription() != null) {
            category.setDescription(request.getDescription());
        }
        return categoryRepository.save(category);
    }
}