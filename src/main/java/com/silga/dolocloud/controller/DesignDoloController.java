package com.silga.dolocloud.controller;

import com.silga.dolocloud.model.Dolo;
import com.silga.dolocloud.model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("doloOrder")
public class DesignDoloController {

    @ModelAttribute
    public void addIngredientsToModel(Model model){
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

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type){
        return ingredients.stream()
                .filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }

}
