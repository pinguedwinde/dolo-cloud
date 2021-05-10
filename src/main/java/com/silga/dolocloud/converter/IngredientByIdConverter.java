package com.silga.dolocloud.converter;

import com.silga.dolocloud.model.Ingredient;
import com.silga.dolocloud.repository.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private final IngredientRepository ingredientRepository;

    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String ingredientId) {
        return this.ingredientRepository.findById(ingredientId).orElseThrow();
    }

}
