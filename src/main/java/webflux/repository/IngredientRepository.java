package webflux.repository;

import com.silga.dolocloud.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface IngredientRepository  extends ReactiveCrudRepository<Ingredient, String> {

}
