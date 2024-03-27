package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request;

import br.com.fiap.restaurantmanagement.domain.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRestaurantCommentRequest {

    private Long restaurantId;

    private Long userId;

    private String comment;

    public Comment toDomain(){
        return new Comment(this.userId,this.comment,this.restaurantId,null);
    }
}
