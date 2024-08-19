package com.example.auctioncapstone2.Controller;

import com.example.auctioncapstone2.Model.Category;
import com.example.auctioncapstone2.Service.CategoryService;
import com.example.auctioncapstone2.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/getall")
    public ResponseEntity getAllCategory() {
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            categoryService.addNewCategory(category);
            return ResponseEntity.status(201).body("category added successfully");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(200).body("category deleted successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            categoryService.updateCategory(category, id);
            return ResponseEntity.status(201).body("category updated successfully");
        }
    }

    @GetMapping("/get/pro/by/category/{cat}")
    public ResponseEntity getUserByCategory(@PathVariable String cat){
        return ResponseEntity.status(200).body(productService.getProductsByCategory(cat));
    }
}
