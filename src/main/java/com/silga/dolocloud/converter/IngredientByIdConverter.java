package com.silga.dolocloud.converter;

import com.silga.dolocloud.model.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final Map<String, Ingredient> ingredientMap;
    List<Ingredient> ingredients = Arrays.asList(
            new Ingredient("RAMI", "Raan Miisga", Ingredient.Type.SOUR),
            new Ingredient("RMPA", "Raan Miisg Paale", Ingredient.Type.SOUR),
            new Ingredient("RMMA", "Raan Miisg Maasga", Ingredient.Type.SOUR),
            new Ingredient("RANO", "Raan Noodo", Ingredient.Type.SUGAR),
            new Ingredient("RNPA", "Raan Nood Paale", Ingredient.Type.SUGAR),
            new Ingredient("RNMA", "Raan Nood Maasga", Ingredient.Type.SUGAR),
            new Ingredient("RATO", "Raan Toodo", Ingredient.Type.ALCOHOLIC),
            new Ingredient("RTPA", "Raan Tood Paale", Ingredient.Type.ALCOHOLIC),
            new Ingredient("RTMA", "Raan Tood Maasga", Ingredient.Type.ALCOHOLIC),
            new Ingredient("RAKO", "Raan Koom", Ingredient.Type.NON_ALCOHOLIC),
            new Ingredient("RKPA", "Raan Koom Paale", Ingredient.Type.NON_ALCOHOLIC),
            new Ingredient("RKMA", "Raan Koom Maasga", Ingredient.Type.NON_ALCOHOLIC)
    );

    public IngredientByIdConverter() {
        ingredientMap = ingredients.stream()
                .collect(Collectors.toMap(Ingredient::getId, Function.identity()));
    }

    @Override
    public Ingredient convert(String ingredientId) {
        return ingredientMap.get(ingredientId);
    }

}
