package com.example.food_project.repository;

import com.example.food_project.entity.OrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatusEntity, Integer> {

    OrderStatusEntity findByIdOrderAndIdStatus(int idOrder, int idStatus);

    @Transactional
    @Modifying
    @Query(value = "UPDATE order_status set active = ?3 WHERE id_order = ?1 AND id_status = ?2", nativeQuery = true)
    void updateStatus(int idOrder, int idStatus, String active);

    @Query(value = "SELECT id_order FROM order_status WHERE id_order IN (?1) AND id_status = ?2 AND active = 'true'", nativeQuery = true)
    List<Integer> findIdOrderByStatus(List<Integer> idOrder, int idStatus);
}
