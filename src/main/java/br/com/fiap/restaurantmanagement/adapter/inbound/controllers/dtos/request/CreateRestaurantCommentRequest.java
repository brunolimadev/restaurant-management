package br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request;

import br.com.fiap.restaurantmanagement.domain.entities.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRestaurantCommentRequest {

    @Schema(description = "Id to Restaurant", example = "1",minimum = "1")

    private Long restaurantId;

    @Schema(description = "Id to User", example = "1",minimum = "1")
    private Long userId;

    @Schema(description = "Comment", example = "Beatyful",minimum = "1",requiredMode = Schema.RequiredMode.REQUIRED)
    private String comment;

    @Schema(description = "Rating", example = "5", minimum = "1")
    private Long rating;

    public Comment toDomain(){
        return new Comment(this.userId,this.comment,this.restaurantId,null, this.rating);
    }
}
