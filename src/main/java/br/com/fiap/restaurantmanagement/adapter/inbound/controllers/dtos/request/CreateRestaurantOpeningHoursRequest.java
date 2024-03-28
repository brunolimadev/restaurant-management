package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request;

import br.com.fiap.restaurantmanagement.domain.entities.OpeningHours;
import br.com.fiap.restaurantmanagement.domain.enumerators.DaysOfWeek;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "day of week", example = "SATURDAY")
    private DaysOfWeek dayOfWeek;

    @Schema(description = "opening time", example = "10:00")
    private String openingTime;

    @Schema(description = "closing time", example = "22:00")
    private String closingTime;

    public OpeningHours toDomain() {
       return new OpeningHours(dayOfWeek, openingTime, closingTime);
    }

}
