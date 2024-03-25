package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private String title;
    private String message;
}
