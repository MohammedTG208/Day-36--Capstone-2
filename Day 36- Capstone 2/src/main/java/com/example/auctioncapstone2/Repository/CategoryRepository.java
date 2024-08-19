package com.example.auctioncapstone2.Repository;

import com.example.auctioncapstone2.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findCategoryById(Integer id);

    @Query("select cat from Category cat where cat.name=?1")
    Category findCategoryByName(String name);
}
