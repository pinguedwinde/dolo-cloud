package com.silga.dolocloud.repository;

import com.silga.dolocloud.model.Ingredient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    Ingredient save(Ingredient ingredient);
}
