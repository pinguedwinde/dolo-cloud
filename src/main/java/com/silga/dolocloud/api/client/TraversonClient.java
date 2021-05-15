package com.silga.dolocloud.api.client;

import com.silga.dolocloud.model.Dolo;
import com.silga.dolocloud.model.Ingredient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.client.Traverson;

import java.util.Collection;

public class TraversonClient {

    private final Traverson traverson;

    public TraversonClient(Traverson traverson) {
        this.traverson = traverson;
    }

    public Collection<Ingredient> getIngredients(){
        ParameterizedTypeReference<CollectionModel<Ingredient>> ingredientType =
                                                    new ParameterizedTypeReference<CollectionModel<Ingredient>>() {};
        CollectionModel<Ingredient> ingredientRes = traverson.follow("ingredients")
                                                            .toObject(ingredientType);
        return ingredientRes.getContent();
    }

    public Collection<Dolo> getRecentDolos(){
        ParameterizedTypeReference<CollectionModel<Dolo>> doloType =
                                                    new ParameterizedTypeReference<CollectionModel<Dolo>>() {};
        CollectionModel<Dolo> doloRes = traverson.follow("dolos", "recent")
                                                .toObject(doloType);
        return doloRes.getContent();
    }
}
