package br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models;

import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the table model
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "restaurant_table")
public class TableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "number_of_seats")
    private Integer numberOfSeats;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private RestaurantModel restaurant;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public static List<TableModel> fromDomain(Restaurant restaurant) {

        List<TableModel> tableModels = new ArrayList<>();

        restaurant.getTables().forEach(table -> {
            TableModel tableModel = new TableModel();
            tableModel.setDescription(table.getDescription());
            tableModel.setNumberOfSeats(table.getCapacity());
            tableModels.add(tableModel);
        });

        return tableModels;

    }

}
