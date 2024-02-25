package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request;

import br.com.fiap.restaurantmanagement.domain.entities.OpeningHours;
import br.com.fiap.restaurantmanagement.domain.enumerators.DaysOfWeek;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRestaurantOpeningHoursRequest {

    public DaysOfWeek dayOfWeek;

    public String openingTime;

    public String closingTime;

    public OpeningHours toDomain() {
       return new OpeningHours(dayOfWeek, openingTime, closingTime);
    }

}
