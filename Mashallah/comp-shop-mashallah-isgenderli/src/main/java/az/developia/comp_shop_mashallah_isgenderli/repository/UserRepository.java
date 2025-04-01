package az.developia.comp_shop_mashallah_isgenderli.repository;


import az.developia.comp_shop_mashallah_isgenderli.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {




	UserEntity findByUsername(String sellerUsername);

	

	
	
}
