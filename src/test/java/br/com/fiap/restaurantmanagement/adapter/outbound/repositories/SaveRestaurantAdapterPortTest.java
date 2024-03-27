package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.*;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.FoodTypeModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.RestaurantModel;
import br.com.fiap.restaurantmanagement.domain.enumerators.TypesOfFood;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.RestaurantAdapterPort;
import br.com.fiap.restaurantmanagement.utils.RestaurantHelper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SaveRestaurantAdapterPortTest {

    private RestaurantAdapterPort restaurantAdapterPort;

    @Mock
    private EntityManager entityManager;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private FoodTypeRepository foodTypeRepository;

    @Mock
    private OpeningHourRepository openingHourRepository;

    @Mock
    private TableRepository tableRepository;

    private AutoCloseable openMocks;

    @BeforeEach
    void setup() {

        openMocks = MockitoAnnotations.openMocks(this);

        restaurantAdapterPort = new RestaurantAdapter(
                entityManager,
                restaurantRepository,
                new FoodTypeAdapter(foodTypeRepository),
                new AddressAdapter(addressRepository),
                new TableAdapter(tableRepository),
                new OpeningHourAdapter(openingHourRepository)
        );

    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void shouldCreateRestaurant() {

        //Arrange
        var restaurant = RestaurantHelper.createRestaurant();
        var restaurantModel = RestaurantHelper.createRestaurantModel();

        when(addressRepository.saveAll(any(List.class))).thenReturn(List.of(RestaurantHelper.createAddressModel()));
        when(tableRepository.saveAll(any(List.class))).thenReturn(List.of(RestaurantHelper.createTableModel()));
        when(openingHourRepository.saveAll(any(List.class))).thenReturn(List.of(RestaurantHelper.createOpeningHourModel()));
        when(foodTypeRepository.save(any(FoodTypeModel.class))).thenReturn(FoodTypeModel.fromDomain(TypesOfFood.BRAZILIAN, null));
        when(restaurantRepository.save(any(RestaurantModel.class))).thenReturn(restaurantModel);

        //Act
        var restaurantSaved = restaurantAdapterPort.save(restaurant);

        //Assert
        assertThat(restaurantSaved).isNotNull();
        assertThat(restaurantSaved.getName()).isEqualTo(restaurant.getName());
        verify(restaurantRepository, times(1)).save(restaurantModel);

    }

}