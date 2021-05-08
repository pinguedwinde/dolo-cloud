package com.silga.dolocloud.model;

import lombok.Data;

import java.util.List;

@Data
public class Dolo {
    private String id;
    private String name;
    private List<Ingredient> ingredients;
}
