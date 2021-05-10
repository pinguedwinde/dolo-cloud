package com.silga.dolocloud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table("ingredients")
public class Ingredient {
    @Id
    private String id;
    private String name;
    private Type type;

    public enum Type{
        ALCOHOLIC, NON_ALCOHOLIC, SUGAR, SOUR
    }
}
