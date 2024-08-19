package com.example.auctioncapstone2.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "owner id can not be null")
    @Column(columnDefinition = "int not null")
    private Integer ownerId;
    @NotNull(message = "start price can not be null")
    @Column(columnDefinition = "decimal(10,2) not null")
    private double startPrice;
    @NotNull(message = "user price can not be null")
    @Column(columnDefinition = "int")
    private int user_id;
    @Column(columnDefinition = "decimal(10,2)")
    private double userPrice;
    @NotNull(message = "title can not be null")
    @Column(columnDefinition = "varchar(25) not null")
    @Size(max = 25,message = "max length for title 25")
    private String title;
    @NotNull(message = "description can not be null")
    @Column(columnDefinition = "varchar(200) not null")
    @Size(max = 200,message = "max length for description 200")
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "date")
    private LocalDate end_date=LocalDate.now();
    @NotNull(message = "category_id can not be null")
    @Column(columnDefinition = "int not null")
    private Integer categoryId;
    @Pattern(regexp = "^(active|buy|canceled)+$",message = "one of these ('active'|'buy'|'canceled') ")
    @Column(columnDefinition = "varchar(8) not null check(status='active' or status='buy' or status='canceled')")
    private String status;
}
