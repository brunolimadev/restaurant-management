package br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models;

import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class represents the restaurant model
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant")
public class RestaurantModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "food_type_id")
    private FoodTypeModel foodType;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public static RestaurantModel fromDomain(RestaurantModel restaurantModel, Restaurant restaurant, Long foodTypeId) {
        restaurantModel.setName(restaurant.getName());
        restaurantModel.setFoodType(FoodTypeModel.fromDomain(restaurant.getTypeOfFood(), foodTypeId));
        return restaurantModel;
    }


    public Restaurant toDomain() {
        return new Restaurant(this.id, this.name, this.foodType.toDomain());
    }

}
