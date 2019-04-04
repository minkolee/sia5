package cc.conyli.sia5.controller;

import cc.conyli.sia5.dao.IngredientRepo;
import cc.conyli.sia5.entity.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    private IngredientRepo ingredientRepo;

    @Autowired
    public IngredientController(IngredientRepo ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }


    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredient-form";
    }

    @PostMapping("/process")
    public String processForm(@ModelAttribute("ingredient") @Valid Ingredient ingredient, Errors errors) {
        if (errors.hasErrors()) {
            return "ingredient-form";
        }
        ingredientRepo.save(ingredient);
        log.info("成功保存Ingredient --> " + ingredient);
        return "redirect:/ingredient/form";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }



}
