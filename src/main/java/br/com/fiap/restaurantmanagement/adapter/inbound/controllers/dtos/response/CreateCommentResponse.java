package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCommentResponse {

    private String mensagemRetorno;

}
