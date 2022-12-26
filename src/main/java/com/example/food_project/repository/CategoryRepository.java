package com.example.food_project.repository;

import com.example.food_project.entity.CategoryEntity;
import com.example.food_project.entity.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    @Query(value = "select c.* from category as c order by c.id ASC limit 6", nativeQuery = true)
    List<CategoryEntity> getExplorer();

    List<CategoryEntity> findAll();

    List<CategoryEntity> findByFoodsIsIn(List<FoodEntity> foods);

    List<CategoryEntity> findByIdIsIn(List<Integer> listId);
}
