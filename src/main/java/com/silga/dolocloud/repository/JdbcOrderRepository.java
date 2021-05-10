package com.silga.dolocloud.repository;

import com.silga.dolocloud.model.Dolo;
import com.silga.dolocloud.model.DoloOrder;
import com.silga.dolocloud.model.Ingredient;
import org.springframework.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class JdbcOrderRepository implements OrderRepository{

    private final JdbcOperations jdbcOperations;
    @Autowired
    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Transactional
    @Override
    public DoloOrder save(DoloOrder order) {
        String sql = "INSERT INTO dolo_orders "
                + "(delivery_name, delivery_street, delivery_city, "
                + "delivery_state, delivery_zip, cc_number, "
                + "cc_expiration, cc_cvv, placed_at) "
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatementCreatorFactory psCreatorFactory = new PreparedStatementCreatorFactory(
                sql,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );
        psCreatorFactory.setReturnGeneratedKeys(true);
        order.setPlacedAt(new Date());
        PreparedStatementCreator psCreator = psCreatorFactory.newPreparedStatementCreator(
                Arrays.asList(
                        order.getDeliveryName(),
                        order.getDeliveryStreet(),
                        order.getDeliveryCity(),
                        order.getDeliveryState(),
                        order.getDeliveryZip(),
                        order.getCcNumber(),
                        order.getCcExpiration(),
                        order.getCcCVV(),
                        order.getPlacedAt()
                )
        );
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psCreator, keyHolder);
        Long orderId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        order.setId(orderId);
        List<Dolo> dolos = order.getDolos();
        AtomicInteger i= new AtomicInteger();
        dolos.forEach(dolo -> saveDolo(orderId, i.getAndIncrement(), dolo));
        return order;
    }

    private Dolo saveDolo(Long orderId, int orderKey, Dolo dolo) {
        String sql = "INSERT INTO dolos "
                + "(name, created_at, dolo_order, taco_order_key) "
                + "values (?, ?, ?, ?)";
        PreparedStatementCreatorFactory psCreatorFactory = new PreparedStatementCreatorFactory(
                sql,  Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
        );
        psCreatorFactory.setReturnGeneratedKeys(true);
        dolo.setCreatedAt(new Date());
        PreparedStatementCreator psCreator = psCreatorFactory.newPreparedStatementCreator(
                Arrays.asList(
                        dolo.getName(),
                        dolo.getCreatedAt(),
                        orderId,
                        orderKey
                )
        );
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psCreator, keyHolder);
        Long doloId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        dolo.setId(doloId);
        saveIngredientRef(doloId, dolo.getIngredients());
        return dolo;
    }

    private void saveIngredientRef(Long doloId, List<Ingredient> ingredients) {
        AtomicInteger key = new AtomicInteger();
        String sql = "INSERT INTO ingredient_refs (ingredient, dolo, dolo_key) VALUES (?, ?, ?)";
        ingredients.stream()
                .map(Ingredient::getId)
                .forEach(
                        ingredientRef ->  jdbcOperations.update(sql, ingredientRef, doloId, key.getAndIncrement())
                );

    }
}
