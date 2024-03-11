package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.TableRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.TableModel;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.SaveAdapterPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TableSaveAdapter implements SaveAdapterPort<List<TableModel>> {

    private final TableRepository tableRepository;

    public TableSaveAdapter(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public List<TableModel> save(List<TableModel> entity) {
        return tableRepository.saveAll(entity);
    }

}