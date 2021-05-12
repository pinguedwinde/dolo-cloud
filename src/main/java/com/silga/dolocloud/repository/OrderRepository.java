package com.silga.dolocloud.repository;

import com.silga.dolocloud.model.DoloOrder;
import com.silga.dolocloud.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<DoloOrder, Long> {

    List<DoloOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
