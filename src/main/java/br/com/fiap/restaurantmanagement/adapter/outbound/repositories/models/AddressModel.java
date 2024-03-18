package br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models;

import br.com.fiap.restaurantmanagement.domain.entities.Address;
import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the address model
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "address")
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private String number;

    @Column(name = "complement")
    private String complement;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "country")
    private String country;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private RestaurantModel restaurant;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public static List<AddressModel> fromDomain(Restaurant restaurant) {
        List<AddressModel> addressModels = new ArrayList<>();

        restaurant.getAddress().forEach(address -> {
            AddressModel addressModel = new AddressModel();
            addressModel.setStreet(address.getStreet());
            addressModel.setNumber(address.getNumber());
            addressModel.setComplement(address.getComplement());
            addressModel.setNeighborhood(address.getNeighborhood());
            addressModel.setCity(address.getCity());
            addressModel.setState(address.getState());
            addressModel.setZipCode(address.getZipCode());
            addressModel.setCountry(address.getCountry());
            addressModels.add(addressModel);
        });

        return addressModels;
    }

    public Address toDomain() {
        return new Address(this.street, this.number, this.complement, this.neighborhood, this.city, this.state, this.zipCode, this.country);
    }

}
