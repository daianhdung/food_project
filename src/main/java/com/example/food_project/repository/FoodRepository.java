package com.example.food_project.repository;

import com.example.food_project.entity.FoodEntity;
import com.example.food_project.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Integer> {

    @Query(value = "select f.* from food as f order by f.id DESC limit 6", nativeQuery = true)
    List<FoodEntity> get6Food();

    long countAllByCategoryId(int categoryId);

    List<FoodEntity> findAllByCategoryId(int categoryId);

    FoodEntity findById(Optional<Integer> id);

    List<FoodEntity> findByIdIsIn(List<Integer> id);

    @Query(value = "SELECT * FROM food WHERE name LIKE ?1 ", nativeQuery = true)
    List<FoodEntity> findByKeyword(String name);
}
