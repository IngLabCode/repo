package az.developia.comp_shop_mashallah_isgenderli.controller;

import az.developia.comp_shop_mashallah_isgenderli.DTO.ComputerDTO;
import az.developia.comp_shop_mashallah_isgenderli.entity.Computer;
import az.developia.comp_shop_mashallah_isgenderli.repository.ComputerRepository;
import az.developia.comp_shop_mashallah_isgenderli.entity.User;
import az.developia.comp_shop_mashallah_isgenderli.repository.UserRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/computer")
@CrossOrigin(origins = "*")
public class ComputerRestController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ComputerRepository compRepository;


	@PreAuthorize("hasRole('USER')")
	@GetMapping("/seller-computers")
	public ResponseEntity<List<Computer>> getSellerComputers() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || !auth.isAuthenticated()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		String sellerUsername = auth.getName();
		User seller = userRepository.findByName(sellerUsername);
		if (seller == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		List<Computer> computers = compRepository.findBySeller(seller);
		return ResponseEntity.ok(computers);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/add")
	public ResponseEntity<String> add(@Valid @RequestBody ComputerDTO computer, BindingResult br) {

		if (br.hasErrors()) {
			return ResponseEntity.badRequest().body("Invalid input data");
		}

		String sellerEmail = computer.getSellerEmail(); // Seller-in email-i DTO-dan alırıq


		User seller = userRepository.findByName(sellerEmail); // Email əsasında istifadəçi tapılır
		if (seller == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}

		Computer entity = new Computer();
		entity.setBrand(computer.getBrand());
		entity.setPrice(computer.getPrice());
		entity.setDescription(computer.getDescription());
		entity.setModel(computer.getModel());
		entity.setRam(computer.getRam());
		entity.setRom(computer.getRom());
		entity.setOs(computer.getOs());
		entity.setPhoto(computer.getPhoto());
		entity.setSeller(seller);

		compRepository.save(entity);
		return ResponseEntity.ok("Computer successfully added");
	}

	@GetMapping("/findAll")
	public List<ComputerDTO> findAll() {
		List<Computer> computers = compRepository.findAll();

		List<ComputerDTO> dtos = new ArrayList<>();
		for (Computer entity : computers) {
			ComputerDTO dto = new ComputerDTO();
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
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/findById/{id}")
	public ResponseEntity<Computer> findById(@PathVariable Long id) {
		return compRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<Computer> update(@PathVariable long id, @RequestBody Computer computer) {
		return compRepository.findById(id)
				.map(existing -> {
					existing.setBrand(computer.getBrand());
					existing.setModel(computer.getModel());
					existing.setPrice(computer.getPrice());
					existing.setDescription(computer.getDescription());
					existing.setPhoto(computer.getPhoto());
					existing.setRam(computer.getRam());
					existing.setOs(computer.getOs());
					return ResponseEntity.ok(compRepository.save(existing));
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		if (!compRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		compRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}