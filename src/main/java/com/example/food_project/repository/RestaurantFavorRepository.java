package com.example.food_project.repository;

import com.example.food_project.entity.RestaurantFavorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RestaurantFavorRepository extends JpaRepository<RestaurantFavorEntity, Integer> {

    RestaurantFavorEntity findByIdUserAndIdRestaurant(int idUser, int idRestaurant);

    @Transactional
    @Modifying
    @Query(value = "UPDATE restaurant_favor set is_favor = ?3 WHERE id_user = ?1 AND id_restaurant = ?2", nativeQuery = true)
    void updateFavorRes(int idUser, int idRestaurant, String favor);

    @Query(value = "SELECT id_restaurant FROM restaurant_favor as rf WHERE id_user = ?1 AND is_favor = 'true'", nativeQuery = true)
    List<Integer> getFavorRestaurant(int idUser);
}
