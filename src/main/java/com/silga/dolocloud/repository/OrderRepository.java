package com.silga.dolocloud.repository;

import com.silga.dolocloud.model.DoloOrder;

public interface OrderRepository {
    DoloOrder save(DoloOrder order);
}
