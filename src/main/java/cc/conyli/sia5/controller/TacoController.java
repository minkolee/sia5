package cc.conyli.sia5.controller;

import cc.conyli.sia5.dao.IngredientRepo;
import cc.conyli.sia5.dao.TacoRepo;
import cc.conyli.sia5.entity.Ingredient;
import cc.conyli.sia5.entity.Order;
import cc.conyli.sia5.entity.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
@RequestMapping("/taco")
@SessionAttributes(value = {"mapper", "order"})
public class TacoController {

    private TacoRepo tacoRepo;
    private IngredientRepo ingredientRepo;


    @Autowired
    public TacoController(IngredientRepo ingredientRepo, TacoRepo tacoRepo) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepo;
    }

    //访问/taco/form的时候，给Session设置上mapper
    @ModelAttribute("mapper")
    public Map<String, List<Ingredient>> setMapper() {
        List<Ingredient> ingredients = ingredientRepo.findAll();
        List<String> typeList = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            if (typeList.contains(ingredient.getType())) {
                continue;
            }
            typeList.add(ingredient.getType());
        }
        Map<String, List<Ingredient>> ingredientsByTypeMapper = new HashMap<>();

        for (String type : typeList) {
            ingredientsByTypeMapper.put(type, ingredientRepo.getIngredientsByType(type));
        }
        return ingredientsByTypeMapper;
    }

    //给Model设置新的Taco
    @ModelAttribute("taco")
    private Taco setNewTaco() {
        return new Taco();
    }

    //访问/taco/form的时候，给Session设置上order
    @ModelAttribute("order")
    private Order setNewOrder() {
        return new Order();
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        return "taco-form";
    }

    @PostMapping("/process")
    public String processForm(@ModelAttribute("taco") @Valid Taco taco, Errors errors, @ModelAttribute("order") Order order) {
        if (errors.hasErrors()) {
            return "taco-form";
        }
        tacoRepo.save(taco);
        log.info("成功提交的taco是：" + taco);
        order.addTaco(taco);
        log.info("现在的Order是：" + order);
        return "redirect:/order/form";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
}
