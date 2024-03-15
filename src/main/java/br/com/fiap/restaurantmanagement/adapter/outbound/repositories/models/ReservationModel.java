package br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models;

import jakarta.persistence.*;
import lombok.*;

/**
 * This class represents the reservation model
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "reservation")
public class ReservationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Column(name = "number_of_people")
    private Integer numberOfpeople;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantModel restaurant;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @OneToOne
    @JoinColumn(name = "restaraunt_table_id")
    private TableModel table;

}