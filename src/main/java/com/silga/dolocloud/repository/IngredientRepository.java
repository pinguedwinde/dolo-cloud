package com.silga.dolocloud.repository;

import com.silga.dolocloud.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IngredientRepository  extends JpaRepository<Ingredient, String> {

}
