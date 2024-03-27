package br.com.fiap.restaurantmanagement.domain.usescases;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.CommentSaveAdapter;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.CommentRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.RestaurantRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.UserRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.RestaurantCommentModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.RestaurantModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.UserModel;
import br.com.fiap.restaurantmanagement.domain.entities.Comment;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;
import br.com.fiap.restaurantmanagement.domain.usecases.CreateCommentUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateCommentUseCaseTest {

    private CreateCommentUseCase createCommentUseCase;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private UserModel userModel;

    @Mock
    private RestaurantModel restaurantModel;

    private AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        SaveAdapterPort saveAdapterPort = new CommentSaveAdapter(commentRepository);
        createCommentUseCase = new CreateCommentUseCase(saveAdapterPort);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void shouldCreateComment() {
        // arrange
        var comment = createComment();
        var commentModel = createCommentModel();

        when(userRepository.getReferenceById(anyLong())).thenReturn(userModel);
        when(restaurantRepository.getReferenceById(anyLong())).thenReturn(restaurantModel);
        when(commentRepository.save(any(RestaurantCommentModel.class))).thenReturn(commentModel);

        var commentSaved = createCommentUseCase.execute(comment);

        // assert
        assertThat(commentSaved).isNotNull();
        assertThat(commentSaved.getComment()).isEqualTo(comment.getComment());
        verify(commentRepository, times(1)).save(any(RestaurantCommentModel.class));
    }

    private RestaurantCommentModel createCommentModel() {

        var commentModel = new RestaurantCommentModel();

        commentModel.setComment("Comment Exemple");
        commentModel.setCreatedAt(LocalDateTime.now());

        return commentModel;
    }

    private Comment createComment() {
        return new Comment(1L, "Comment Exemple", 1L, LocalDateTime.now(),5L);
    }

}
