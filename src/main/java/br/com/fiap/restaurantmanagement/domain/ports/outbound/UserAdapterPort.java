package br.com.fiap.restaurantmanagement.domain.ports.outbound;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.UserModel;

public interface UserAdapterPort extends  SaveAdapterPort<UserModel> {

  UserModel findByEmail(String email);

}
