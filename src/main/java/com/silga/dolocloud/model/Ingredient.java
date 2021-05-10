package com.silga.dolocloud.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "ingredients")
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
public class Ingredient {
    @Id
    private String id;
    private String name;
    private Type type;

    public enum Type{
        ALCOHOLIC, NON_ALCOHOLIC, SUGAR, SOUR
    }
}
