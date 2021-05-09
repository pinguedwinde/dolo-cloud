package com.silga.dolocloud.controller;

import com.silga.dolocloud.model.Dolo;
import com.silga.dolocloud.model.Ingredient;
import com.silga.dolocloud.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("doloOrder")
public class DesignDoloController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignDoloController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model){
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        Ingredient.Type[] types = Ingredient.Type.values();
        for(Ingredient.Type type : types){
            model.addAttribute(
                    type.name().toLowerCase(Locale.ROOT), filterByType(ingredients, type)
            );
        }
    }

    @GetMapping
    public String showDesignDoloForm(Model model){
        model.addAttribute("dolo", new Dolo());
        return "design";
    }

    @PostMapping
    public String processDolo(@Valid @ModelAttribute("dolo") Dolo dolo, Errors errors){
        if (errors.hasErrors()) {
            return "design";
        }
        log.info("Processing dolo: " + dolo);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Ingredient.Type type){

        return StreamSupport.stream(ingredients.spliterator(), false)
                .filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }

}
