package com.silga.dolocloud.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "dolos")
public class Dolo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min=1, message="You must choose at least 1 ingredient")
    @ManyToMany
    private List<Ingredient> ingredients;

    @Column(name = "created_at")
    private Date createdAt;

    @PrePersist
    private void createAt(){
        this.createdAt = new Date();
    }

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    public List<Ingredient> getCopyOfIngredients(){
        return List.copyOf(ingredients);
    }
}
