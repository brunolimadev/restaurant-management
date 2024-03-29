package br.com.fiap.restaurantmanagement.domain.ports.outbound;

/**
 * This interface represents the port for saving an entity
 * @param <T> The entity to be saved
 */
public interface SaveAdapterPort<T> {

    T save(T entity);

}