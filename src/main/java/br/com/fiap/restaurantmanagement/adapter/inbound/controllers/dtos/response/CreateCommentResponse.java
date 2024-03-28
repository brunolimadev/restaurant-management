package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCommentResponse {

    @Schema(description = "message", example = "return message")
    private String message;

}
