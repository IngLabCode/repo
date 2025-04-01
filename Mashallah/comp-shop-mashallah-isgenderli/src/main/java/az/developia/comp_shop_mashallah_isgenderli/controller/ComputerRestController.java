package az.developia.comp_shop_mashallah_isgenderli.controller;

import az.developia.comp_shop_mashallah_isgenderli.DTO.ComputerDTO;
import az.developia.comp_shop_mashallah_isgenderli.entity.Computer;
import az.developia.comp_shop_mashallah_isgenderli.entity.UserEntity;
import az.developia.comp_shop_mashallah_isgenderli.repository.ComputerRepository;
import az.developia.comp_shop_mashallah_isgenderli.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/computers")
@CrossOrigin(origins = "*")
public class ComputerRestController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private ComputerRepository compRepository;
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/seller-computers")
	public ResponseEntity<List<Computer>> getSellerComputers() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()
				|| authentication.getPrincipal() instanceof String) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}

		String sellerUsername = authentication.getName();
		UserEntity seller = userRepository.findByUsername(sellerUsername);

		if (seller == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		List<Computer> computers = compRepository.findBySeller(seller);

		return ResponseEntity.ok(computers);
	}

	@PostMapping(path = "/add")
	public ResponseEntity<String> add(@Valid @RequestBody ComputerDTO computer, BindingResult br) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String sellerUsername = authentication.getName();
		UserEntity seller = userRepository.findByUsername(sellerUsername);
		System.out.println("Aktiv istifadəçi: " + sellerUsername);

		if (seller == null) {
			throw new IllegalStateException("İstifadəçi tapılmadı");
		}

		Computer entity = new Computer();
		entity.setBrand(computer.getBrand());
		entity.setPrice(computer.getPrice());
		entity.setDescription(computer.getDescription());
		entity.setModel(computer.getModel());
		entity.setRam(computer.getRam());
		entity.setOs(computer.getOs());
		entity.setSeller(seller);

		entity.setPhoto(computer.getPhoto());

		compRepository.save(entity);
		return ResponseEntity.ok("Komputer uğurla əlavə edildi");
	}

	@GetMapping(path = "/findAll")
	public List<ComputerDTO> findAll() {
		List<Computer> list = compRepository.findAll();
		List<ComputerDTO> dtos = new ArrayList<>();

		for (Computer entity : list) {
			ComputerDTO dto = new ComputerDTO();
			dto.setId(entity.getId());
			dto.setBrand(entity.getBrand());
			dto.setModel(entity.getModel());
			dto.setPrice(entity.getPrice());
			dto.setDescription(entity.getDescription());
			dto.setPhoto(entity.getPhoto());
			dto.setRam(entity.getRam());
			dto.setOs(entity.getOs());

			dtos.add(dto);
		}

		return dtos;
	}

	@GetMapping(path = "/{id}")
	public Computer findById(@PathVariable Long id) {
		return compRepository.findById(id).get();
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Computer> update(@PathVariable long id, @RequestBody Computer computer) {
		Computer updateComp = compRepository.findById(id).get();
		updateComp.setBrand(computer.getBrand());
		updateComp.setModel(computer.getModel());
		updateComp.setPrice(computer.getPrice());
		updateComp.setDescription(computer.getDescription());
		updateComp.setPhoto(computer.getPhoto());
		updateComp.setRam(computer.getRam());
		updateComp.setOs(computer.getOs());

		compRepository.save(updateComp);
		return ResponseEntity.ok(updateComp);
	}

	@DeleteMapping(path = "/{id}")
	public void deleteCompById(@PathVariable Long id) {
		compRepository.deleteById(id);
	}

}
