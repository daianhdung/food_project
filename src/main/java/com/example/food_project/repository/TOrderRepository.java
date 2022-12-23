package com.example.food_project.repository;

import com.example.food_project.entity.TOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TOrderRepository extends JpaRepository<TOrderEntity, Integer> {

    TOrderEntity findByUserId(int userId);

    @Query(value = "SELECT id FROM t_order WHERE id_user = ?1", nativeQuery = true)
    List<Integer> getTOrder(int idUser);

    @Query(value = "SELECT * from t_order WHERE id_user = ?1", nativeQuery = true)
    List<TOrderEntity> getListIdOrderByIdUser(int userId);

    List<TOrderEntity> findByIdIsIn(List<Integer> tOrdersId);
}
