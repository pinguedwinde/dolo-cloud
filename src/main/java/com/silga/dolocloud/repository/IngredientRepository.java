package com.silga.dolocloud.repository;

import com.silga.dolocloud.model.Ingredient;
import org.springframework.data.repository.CrudRepository;


public interface IngredientRepository  extends CrudRepository<Ingredient, String> {

}
