package com.example.auctioncapstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "user_id can not be null")
    @Column(columnDefinition = "int not null")
    @Positive(message = "Enter valid number")
    private Integer userId;
    @Positive(message = "Enter valid number")
    @NotNull(message = "product_id can not be null")
    private Integer productId;
    @Positive(message = "Enter valid value")
    @Column(columnDefinition = "decimal(10,2) not null")
    private double price;

}
