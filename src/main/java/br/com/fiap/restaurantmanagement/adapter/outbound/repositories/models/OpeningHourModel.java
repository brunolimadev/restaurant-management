package br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models;

import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "opening_hour")
public class OpeningHourModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day_of_week")
    private String dayOfWeek;

    @Column(name = "opening_time")
    private String openingTime;

    @Column(name = "closing_time")
    private String closingTime;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantModel restaurant;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public static List<OpeningHourModel> fromDomain(Restaurant restaurant) {
        List<OpeningHourModel> openingHourModels = new ArrayList<>();

        restaurant.getOpeningHours().forEach(openingHour -> {
            OpeningHourModel openingHourModel = new OpeningHourModel();
            openingHourModel.setDayOfWeek(openingHour.getDayOfWeek().name());
            openingHourModel.setOpeningTime(openingHour.getOpeningTime());
            openingHourModel.setClosingTime(openingHour.getClosingTime());
            openingHourModels.add(openingHourModel);
        });

        return openingHourModels;
    }


}
