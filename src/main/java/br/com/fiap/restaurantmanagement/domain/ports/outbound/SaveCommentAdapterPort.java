package br.com.fiap.restaurantmanagement.domain.ports.outbound;

public interface SaveCommentAdapterPort<T> {

    public T saveComment(T entity);
}
