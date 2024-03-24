package br.com.fiap.restaurantmanagement.domain.ports.outbound;

import br.com.fiap.restaurantmanagement.domain.exceptions.FoodTypeNotFoundException;

import java.util.Optional;

/**
 * This interface represents the port for searching an entity
 * @param <T> The entity to be searched
 */
public interface SearchAdapterPort<T> {
    T search(Optional<String>... args) throws FoodTypeNotFoundException;
}
