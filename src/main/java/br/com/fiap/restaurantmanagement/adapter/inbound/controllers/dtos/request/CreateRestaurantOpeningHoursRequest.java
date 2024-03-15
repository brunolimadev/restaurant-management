package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request;

import br.com.fiap.restaurantmanagement.domain.entities.OpeningHours;
import br.com.fiap.restaurantmanagement.domain.enumerators.DaysOfWeek;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the create restaurant opening hours request
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRestaurantOpeningHoursRequest {

    private DaysOfWeek dayOfWeek;

    private String openingTime;

    private String closingTime;

    public OpeningHours toDomain() {
       return new OpeningHours(dayOfWeek, openingTime, closingTime);
    }

}
