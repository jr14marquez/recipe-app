package com.marquezr.recipeapp.repositories;

import com.marquezr.recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
