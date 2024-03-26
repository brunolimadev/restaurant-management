package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.CommentRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.RestaurantRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.RestaurantCommentModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.RestaurantModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CommentRepositoryTest {


    @Mock
    private CommentRepository commentRepository;



    AutoCloseable openMocks;

    @BeforeEach
    public void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void shouldPermitRegisterAComment() {

        // arrange
        var comment = createCommentModel();
        when(commentRepository.save(any(RestaurantCommentModel.class))).thenReturn(comment);

        // act
        var result = commentRepository.save(comment);

        // assert
        assertThat(result).isNotNull();
        assertThat(result.getComment()).isEqualTo(comment.getComment());

        verify(commentRepository, times(1)).save(any(RestaurantCommentModel.class));

    }

    private RestaurantCommentModel createCommentModel() {
        var commentModel = new RestaurantCommentModel();
        commentModel.setComment("Comment exemple");
        commentModel.setCreatedAt(LocalDateTime.now());

        return commentModel;
    }

}
