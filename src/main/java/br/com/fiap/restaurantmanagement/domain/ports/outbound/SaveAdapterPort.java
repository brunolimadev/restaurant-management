package br.com.fiap.restaurantmanagement.domain.ports.outbound;


public interface SaveAdapterPort<T> {

    T save(T entity);

}