package com.baitapnhom.courseweb.controller;

import com.baitapnhom.courseweb.dto.request.CategoryRequest;
import com.baitapnhom.courseweb.dto.request.CategoryRequest;
import com.baitapnhom.courseweb.entity.Category;
import com.baitapnhom.courseweb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

    @RestController
    @RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    public Category createCategory(@RequestBody CategoryRequest request){
        return categoryService.createRequest(request);
    }
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable String id){
        return categoryService.getCategoryById(id);
    }
    @DeleteMapping("/{id}")
        public String deleteCategory(@PathVariable String id) {
        return categoryService.deleteCategory(id);
    }
    @PutMapping("/{id}")
        public Category updateCategory(@PathVariable String id, @RequestBody CategoryRequest request) {
        return categoryService.updateCategory(id, request);
    }
}
