package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.*;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.FoodTypeModel;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.RestaurantModel;
import br.com.fiap.restaurantmanagement.domain.enumerators.TypesOfFood;
import br.com.fiap.restaurantmanagement.utils.RestaurantHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SaveRestaurantAdapterTest {

    private RestaurantSaveAdapter saveRestaurantAdapter;

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

        saveRestaurantAdapter = new RestaurantSaveAdapter(
                restaurantRepository,
                new FoodTypeSaveAdapter(foodTypeRepository),
                new AddressSaveAdapter(addressRepository),
                new TableSaveAdapter(tableRepository),
                new OpeningHourSaveAdapter(openingHourRepository)
        );
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void shouldCreateRestaurant() {
        // arrange
        var restaurant = RestaurantHelper.createRestaurant();
        var restaurantModel = RestaurantHelper.createRestaurantModel();

        when(addressRepository.saveAll(any(List.class))).thenReturn(List.of(RestaurantHelper.createAddressModel()));
        when(tableRepository.saveAll(any(List.class))).thenReturn(List.of(RestaurantHelper.createTableModel()));
        when(openingHourRepository.saveAll(any(List.class))).thenReturn(List.of(RestaurantHelper.createOpeningHourModel()));
        when(foodTypeRepository.save(any(FoodTypeModel.class))).thenReturn(FoodTypeModel.fromDomain(TypesOfFood.BRAZILIAN, null));
        when(restaurantRepository.save(any(RestaurantModel.class))).thenReturn(restaurantModel);

        // act
        var restaurantSaved = saveRestaurantAdapter.save(restaurant);

        // assert
        assertThat(restaurantSaved).isNotNull();
        assertThat(restaurantSaved.getName()).isEqualTo(restaurant.getName());
        verify(restaurantRepository, times(1)).save(restaurantModel);
    }




}
