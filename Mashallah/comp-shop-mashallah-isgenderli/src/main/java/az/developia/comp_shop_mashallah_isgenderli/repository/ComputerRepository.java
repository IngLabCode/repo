package az.developia.comp_shop_mashallah_isgenderli.repository;


import az.developia.comp_shop_mashallah_isgenderli.entity.Computer;
import az.developia.comp_shop_mashallah_isgenderli.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComputerRepository extends JpaRepository<Computer, Long> {
	List<Computer> findBySeller(UserEntity seller);

}