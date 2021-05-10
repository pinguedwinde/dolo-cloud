package com.silga.dolocloud.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "dolo_orders")
public class DoloOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field(name = "delivery_name")
    @NotBlank(message="Delivery name is required")
    private String deliveryName;

    @Field(name = "delivery_street")
    @NotBlank(message="Street is required")
    private String deliveryStreet;

    @Field(name = "delivery_city")
    @NotBlank(message="City is required")
    private String deliveryCity;

    @Field(name = "delivery_state")
    @NotBlank(message="State is required")
    private String deliveryState;

    @Field(name = "delivery_zip")
    @NotBlank(message="Zip code is required")
    private String deliveryZip;

    @Field(name = "cc_number")
    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;

    @Field(name = "cc_expiration")
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;

    @Field(name = "cc_cvv")
    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    @Field(name = "placed_at")
    private Date placedAt = new Date();

    private List<Dolo> dolos = new ArrayList<>();

    public void addDolo(Dolo dolo) {
        this.dolos.add(dolo);
    }

    public List<Dolo> getCopyOfDolos(){
        return List.copyOf(dolos);
    }

}
