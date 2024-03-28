package br.com.fiap.restaurantmanagement.adapter.inbound.controllers;

import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.request.CreateRestaurantCommentRequest;
import br.com.fiap.restaurantmanagement.adapter.inbound.controllers.dtos.response.CreateCommentResponse;
import br.com.fiap.restaurantmanagement.domain.ports.inbound.CreateCommentUseCasePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * This class represents the comment controller
 */
@RestController
@RequestMapping("/comment")
@Tag(name = "Comment Controller",
     description = "After the visit, the customer can rate the restaurant with a score from \"one to five\" and leave comments about their experience.")
public class CommentController {

    private final CreateCommentUseCasePort createCommentUseCasePort;

    public CommentController(CreateCommentUseCasePort createCommentUseCasePort) {

        this.createCommentUseCasePort = createCommentUseCasePort;

    }

    @Operation(summary = "Register comments and ratings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping
    public ResponseEntity<CreateCommentResponse> createComment(@RequestBody CreateRestaurantCommentRequest createCommentRequest) {

        try {
            createCommentUseCasePort.execute(createCommentRequest.toDomain());
        }

        catch (RuntimeException ex ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    CreateCommentResponse.builder()
                            .message(ex.getMessage())
                            .build());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(
                CreateCommentResponse.builder()
                        .message("Comment successfully registered")
                        .build());
    }
}


