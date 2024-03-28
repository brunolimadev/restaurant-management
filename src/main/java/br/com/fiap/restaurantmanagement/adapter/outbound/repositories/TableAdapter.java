package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.TableRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.TableModel;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.TableAdapterPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TableAdapter implements TableAdapterPort {

  private final TableRepository tableRepository;

  public TableAdapter(TableRepository tableRepository) {

    this.tableRepository = tableRepository;

  }

  @Override
  public List<TableModel> save(List<TableModel> tableModelList) {

    return tableRepository.saveAll(tableModelList);

  }

  @Override
  public List<TableModel> findTablesByRestaurant(Long id) {

    return tableRepository.findTablesByRestaurant(id);

  }

  @Override
  public List<TableModel> findTablesNotReservation(Long id) {

    return tableRepository.findTablesNotReservation(id);

  }

}