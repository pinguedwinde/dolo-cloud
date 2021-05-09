package com.silga.dolocloud.repository;

import com.silga.dolocloud.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcIngredientRepository implements IngredientRepository{

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        String sql = "SELECT id, name, type FROM ingredients";
        return jdbcTemplate.query(sql,this::mapRowToIngredient);
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        String sql = "SELECT id, name, type FROM ingredients WHERE id=?";
        List<Ingredient> results = jdbcTemplate.query(sql, this::mapRowToIngredient, id);
        return results.size() == 0 ?  Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        String sql = "INSERT INTO ingredients (id, name, type) VALUES (?, ?, ?)";
        jdbcTemplate.update(
                sql,
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString()
        );
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet resultSet, int rowNum) throws SQLException {
        return new Ingredient(
                resultSet.getString("id"),
                resultSet.getString("name"),
                Ingredient.Type.valueOf(resultSet.getString("type"))
        );
    }
}
