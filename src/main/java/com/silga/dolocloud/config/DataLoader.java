package com.silga.dolocloud.config;

import com.silga.dolocloud.model.Ingredient;
import com.silga.dolocloud.model.User;
import com.silga.dolocloud.repository.IngredientRepository;
import com.silga.dolocloud.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    public ApplicationRunner loadIngredients(IngredientRepository ingredientRepository){
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
        return args -> ingredientRepository.saveAll(ingredients);
    }

    @Bean
    public ApplicationRunner loadUsers(UserRepository userRepository, PasswordEncoder passwordEncoder){
        List<User> users = Arrays.asList(
                new User("tiga", passwordEncoder.encode("pass"), "Tiga Bila", "12 avenue Tang zuugu",
                        "Tang Pooré", "Burkina Faso", "23455","01 23 43 55"),
                new User("Poko", passwordEncoder.encode("pass"), "Poko Koudbila", "2 rue Bankin",
                        "Tang Pooré", "Burkina Faso", "23455","01 00 43 95")
        );
        return args -> userRepository.saveAll(users);
    }
}
