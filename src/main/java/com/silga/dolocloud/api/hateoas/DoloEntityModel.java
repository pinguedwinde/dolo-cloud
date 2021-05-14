package com.silga.dolocloud.api.hateoas;

import com.silga.dolocloud.model.Dolo;
import com.silga.dolocloud.model.Ingredient;
import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Date;
import java.util.List;

@Relation(value="dolo", collectionRelation="dolos")
public class DoloEntityModel extends EntityModel<DoloEntityModel> {

    private static final IngredientEntityModelAssembler ingredientAssembler = new IngredientEntityModelAssembler();

    @Getter
    private final String name;
    @Getter
    private final Date createdAt;
    @Getter
    private final CollectionModel<IngredientEntityModel> ingredients;

    public DoloEntityModel(Dolo dolo) {
        this.name = dolo.getName();
        this.createdAt = dolo.getCreatedAt();
        this.ingredients = ingredientAssembler.toCollectionModel(dolo.getIngredients());
    }
}
