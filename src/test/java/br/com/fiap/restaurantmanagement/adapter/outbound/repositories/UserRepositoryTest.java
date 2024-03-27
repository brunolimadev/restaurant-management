package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.CommentRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.UserRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.RestaurantCommentModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.UserModel;
import br.com.fiap.restaurantmanagement.domain.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserRepositoryTest {


    @Mock
    private UserRepository userRepository;

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
    void shouldPermitFindAUser() {
        // Arrange
        var user = createUser();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        // Act
        var userResult = userRepository.findById(anyLong());

        // Assert
        assertThat(userResult)
                .isPresent()
                .containsSame(user);

        userResult.ifPresent( u ->{
            assertThat(u.getId()).isEqualTo(user.getId());
            assertThat(u.getName()).isEqualTo(user.getName());
            assertThat(u.getEmail()).isEqualTo(user.getEmail());
        });
        verify(userRepository, times(1)).findById(anyLong());
    }

    private UserModel createUser() {
        return UserModel.builder()
                .id(1L)
                .name("Eric Rangel")
                .email("ericlsrangel@gmail.com")
                .build();
    }

}
