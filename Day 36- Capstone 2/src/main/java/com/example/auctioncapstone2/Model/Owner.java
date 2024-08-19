package com.example.auctioncapstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "name can not be null")
    @Column(columnDefinition = "varchar(35) not null unique")
    @Size(max = 35,message = "max length for email is 35")
    private String name;
    @Email(message = "Enter valid email")
    @Column(columnDefinition = "varchar(45) not null unique")
    @Size(max = 45,message = "max length for email is 45")
    private String email;
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message = "enter valid password")
    private String password;
    @Pattern(regexp = "^05\\d{8}$",message = "phone number most start 05XXXXXXXX")
    @Column(columnDefinition = "varchar(10) not null")
    private String phone;
}
