package br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models;

import br.com.fiap.restaurantmanagement.domain.enumerators.TypesOfFood;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * This class represents the food type model
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
