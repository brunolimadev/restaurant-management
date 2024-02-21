package br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.enumarators.FoodTypes;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "food_type")
public class FoodTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private FoodTypes name;
}
