package br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models;

import br.com.fiap.restaurantmanagement.domain.enumerators.TypesOfFood;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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
    private TypesOfFood name;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public static FoodTypeModel fromDomain(TypesOfFood foodType, Long foodTypeId) {
        FoodTypeModel foodTypeModel = new FoodTypeModel();
        foodTypeModel.setName(foodType);
        foodTypeModel.setId(foodTypeId);
        return foodTypeModel;
    }

    public TypesOfFood toDomain() {
        return this.name;
    }
}
