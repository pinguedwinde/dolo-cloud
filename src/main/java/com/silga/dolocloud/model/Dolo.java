package com.silga.dolocloud.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Dolo {

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

    @Field(name = "created_at")
    private Date createdAt = new Date();

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    public List<Ingredient> getCopyOfIngredients(){
        return List.copyOf(ingredients);
    }
}
