package com.example.auctioncapstone2.Service;

import com.example.auctioncapstone2.Api.ApiException;
import com.example.auctioncapstone2.Model.Category;
import com.example.auctioncapstone2.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public List<Category> getAllCategories() {
        if (categoryRepository.findAll().isEmpty()){
            throw new ApiException("categories DB is empty");
        }
        return categoryRepository.findAll();
    }
    public void addNewCategory(Category category) {
        categoryRepository.save(category);
    }
    public void updateCategory(Category category,Integer id) {
        if (categoryRepository.findCategoryById(id)==null){
            throw new ApiException("category does not exist by this ID");
        }
        Category oldCategory = categoryRepository.findCategoryById(id);
        oldCategory.setName(category.getName());
        categoryRepository.save(oldCategory);
    }
    public void deleteCategory(Integer id) {
        if (categoryRepository.findCategoryById(id)==null){
            throw new ApiException("category does not exist by this ID");
        }
        categoryRepository.delete(categoryRepository.findCategoryById(id));
    }
}
