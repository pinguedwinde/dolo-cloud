package com.silga.dolocloud.config;

import com.silga.dolocloud.model.Ingredient;
import com.silga.dolocloud.repository.IngredientRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    public ApplicationRunner loadIngredientData(IngredientRepository ingredientRepository){
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
        return args -> {
            ingredientRepository.saveAll(ingredients);
        };
    }
}
