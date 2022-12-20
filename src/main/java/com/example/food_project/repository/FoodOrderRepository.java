package com.example.food_project.repository;

import com.example.food_project.entity.FoodOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodOrderRepository extends JpaRepository<FoodOrderEntity, Integer>{


}
