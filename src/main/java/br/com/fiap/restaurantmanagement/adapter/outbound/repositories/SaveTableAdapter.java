package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.TableRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.TableModel;
import br.com.fiap.restaurantmanagement.domain.entities.Restaurant;
import br.com.fiap.restaurantmanagement.domain.entities.Table;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SaveTableAdapter {

    private final TableRepository tableRepository;

    public SaveTableAdapter(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<TableModel> save(Restaurant restaurant) {

        List<TableModel> tableModels = TableModel.fromDomain(restaurant);

        return tableRepository.saveAll(tableModels);
    }

}
