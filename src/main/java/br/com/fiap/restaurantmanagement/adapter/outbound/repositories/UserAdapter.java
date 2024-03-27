package br.com.fiap.restaurantmanagement.adapter.outbound.repositories;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces.UserRepository;
import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.UserModel;
import br.com.fiap.restaurantmanagement.domain.ports.outbound.UserAdapterPort;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter implements UserAdapterPort {

  private final UserRepository userRepository;

  public UserAdapter(UserRepository userRepository) {

    this.userRepository = userRepository;

  }

  @Override
  public UserModel save(UserModel userModel) {

    return userRepository.save(userModel);

  }

  @Override
  public UserModel findByEmail(String email) {

    return userRepository.findByEmail(email);

  }

}