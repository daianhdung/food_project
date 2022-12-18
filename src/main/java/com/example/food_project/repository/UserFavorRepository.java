package com.example.food_project.repository;

import com.example.food_project.entity.UserFavorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserFavorRepository extends JpaRepository<UserFavorEntity, Integer> {

    UserFavorEntity findByIdUserAndIdFood(int idUser, int idFood);


    @Transactional
    @Modifying
    @Query(value = "UPDATE user_favor set is_favor = ?3 WHERE id_user = ?1 AND id_food = ?2", nativeQuery = true)
    void updateFavor(int idUser, int idFood, String favor);
}
