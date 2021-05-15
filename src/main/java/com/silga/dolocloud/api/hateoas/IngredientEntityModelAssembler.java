package com.silga.dolocloud.api.hateoas;

import com.silga.dolocloud.model.Dolo;
import com.silga.dolocloud.model.Ingredient;
import com.silga.dolocloud.restcontroller.DesignDoloRestController;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import java.util.List;
import java.util.stream.Collectors;

public class IngredientEntityModelAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngredientEntityModel> {

    public IngredientEntityModelAssembler() {
        super(DesignDoloRestController.class, IngredientEntityModel.class);
    }

    @Override
    public IngredientEntityModel instantiateModel(Ingredient ingredient) {
        return new IngredientEntityModel (ingredient);
    }

    @Override
    public IngredientEntityModel toModel(Ingredient ingredient) {
        return createModelWithId (ingredient.getId(),ingredient);
    }

    public List<IngredientEntityModel> toModels(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
