package az.developia.comp_shop_mashallah_isgenderli.repository;

import az.developia.comp_shop_mashallah_isgenderli.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

  User findByName(String name);




}

