package com.silga.dolocloud.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DoloOrder {

    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
    private List<Dolo> dolos = new ArrayList<>();

    public void addTaco(Dolo dolo) {
        this.dolos.add(dolo);
    }
}
