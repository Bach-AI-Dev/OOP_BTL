package OOP_BTL_develop.demo.controller;

import OOP_BTL_develop.demo.dto.request.CategoryCreationRequest;
import OOP_BTL_develop.demo.entity.Category;
import OOP_BTL_develop.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    public Category createCategory(@RequestBody CategoryCreationRequest request){
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
        public Category updateCategory(@PathVariable String id, @RequestBody CategoryCreationRequest request) {
        return categoryService.updateCategory(id, request);
    }
}
