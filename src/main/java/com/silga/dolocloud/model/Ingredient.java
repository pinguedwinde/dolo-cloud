package com.silga.dolocloud.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
@Table(name = "ingredients")
public class Ingredient {
    @Id
    private String id;
    private String name;
    private Type type;

    public enum Type{
        ALCOHOLIC, NON_ALCOHOLIC, SUGAR, SOUR
    }
}
