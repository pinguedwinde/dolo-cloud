package com.silga.dolocloud.api.client;

import com.silga.dolocloud.model.Ingredient;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.Traverson;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RestClient {
    private final RestTemplate restTemplate;
    private final Traverson traverson;

    public RestClient(RestTemplate restTemplate, Traverson traverson) {
        this.restTemplate = restTemplate;
        this.traverson = traverson;
    }

    public Ingredient getIngredientById(String ingredientId) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);
        URI url = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/ingredients/{id}")
                .build(urlVariables);
        return restTemplate.getForObject(url, Ingredient.class);
    }

    public void updateIngredient(Ingredient ingredient){
        String url = "http://localhost:8080/ingredients/{id}";
        restTemplate.put(url, ingredient, ingredient.getId());
    }

    public void deleteIngredient(Ingredient ingredient) {
        restTemplate.delete("http://localhost:8080/ingredients/{id}", ingredient.getId());
    }
    public Ingredient createIngredient(Ingredient ingredient) {
        return restTemplate.postForObject("http://localhost:8080/ingredients",
                ingredient, Ingredient.class);
    }

    public Ingredient addIngredient(Ingredient ingredient){
        String url = traverson.follow("ingredients")
                            .asLink()
                            .getHref();
        return restTemplate.postForObject(url, ingredient, Ingredient.class);
    }
}
