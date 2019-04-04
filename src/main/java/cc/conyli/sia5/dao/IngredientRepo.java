package cc.conyli.sia5.dao;

import cc.conyli.sia5.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepo extends JpaRepository<Ingredient, Integer> {

    List<Ingredient> getIngredientsByType(String type);

}
