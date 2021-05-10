package com.silga.dolocloud.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Table("dolo_orders")
public class DoloOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column("delivery_name")
    @NotBlank(message="Delivery name is required")
    private String deliveryName;

    @Column("delivery_street")
    @NotBlank(message="Street is required")
    private String deliveryStreet;

    @Column("delivery_city")
    @NotBlank(message="City is required")
    private String deliveryCity;

    @Column("delivery_state")
    @NotBlank(message="State is required")
    private String deliveryState;

    @Column("delivery_zip")
    @NotBlank(message="Zip code is required")
    private String deliveryZip;

    @Column("cc_number")
    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;

    @Column("cc_expiration")
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;

    @Column("cc_cvv")
    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    @Column("placed_at")
    private Date placedAt;

    private List<Dolo> dolos = new ArrayList<>();

    public void addTaco(Dolo dolo) {
        this.dolos.add(dolo);
    }

}
