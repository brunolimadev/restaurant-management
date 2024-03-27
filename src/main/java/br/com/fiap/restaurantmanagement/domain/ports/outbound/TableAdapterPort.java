package br.com.fiap.restaurantmanagement.domain.ports.outbound;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.TableModel;

import java.util.List;

public interface TableAdapterPort extends SaveAdapterPort<List<TableModel>> {

  List<TableModel> findTablesByRestaurant(Long id);

  List<TableModel> findTablesNotReservation(Long id);

}
