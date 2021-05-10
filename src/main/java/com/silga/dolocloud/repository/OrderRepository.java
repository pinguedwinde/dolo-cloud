package com.silga.dolocloud.repository;

import com.silga.dolocloud.model.DoloOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<DoloOrder, Long> {

}
