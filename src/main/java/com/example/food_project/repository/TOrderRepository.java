package com.example.food_project.repository;

import com.example.food_project.entity.TOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TOrderRepository extends JpaRepository<TOrderEntity, Integer> {


}
