package com.silga.dolocloud.api.hateoas;

import com.silga.dolocloud.model.Ingredient;
import lombok.Getter;
import org.springframework.hateoas.EntityModel;

public class IngredientEntityModel extends EntityModel<IngredientEntityModel> {

    @Getter
    private String name;
    @Getter
    private Ingredient.Type type;
    public IngredientEntityModel(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }
}
