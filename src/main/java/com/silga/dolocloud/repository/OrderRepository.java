package com.silga.dolocloud.repository;

import com.silga.dolocloud.model.DoloOrder;
import com.silga.dolocloud.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<DoloOrder, Long> {

    List<DoloOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
