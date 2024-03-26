package br.com.fiap.restaurantmanagement.adapter.outbound.repositories.interfaces;

import br.com.fiap.restaurantmanagement.adapter.outbound.repositories.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {

  UserModel findByEmail(String email);

}