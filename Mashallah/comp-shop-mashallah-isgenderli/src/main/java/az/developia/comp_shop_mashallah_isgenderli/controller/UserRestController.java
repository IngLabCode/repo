package az.developia.comp_shop_mashallah_isgenderli.controller;


import az.developia.comp_shop_mashallah_isgenderli.DTO.UserDTO;
import az.developia.comp_shop_mashallah_isgenderli.entity.Authority;
import az.developia.comp_shop_mashallah_isgenderli.entity.LoginRequest;
import az.developia.comp_shop_mashallah_isgenderli.entity.UserEntity;
import az.developia.comp_shop_mashallah_isgenderli.repository.AuthorityRepository;
import az.developia.comp_shop_mashallah_isgenderli.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserRestController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping(path = "/save")
	public UserEntity saveUser(@RequestBody UserDTO userDTO) {

		UserEntity entity = new UserEntity();
		entity.setUsername(userDTO.getUsername());
		entity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		entity.setPhone(userDTO.getPhone());
		entity.setFullName(userDTO.getFullName());
		entity.setEnabled(1);

		userRepository.save(entity);

		Authority authority = new Authority();
		authority.setUsername(userDTO.getUsername());
		authority.setAuthority("SELLER");
		authorityRepository.save(authority);

		return entity;
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest login) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		Map<String, String> response = new HashMap<>();
		response.put("message", "User login success");
		response.put("username", login.getUsername());
		response.put("password", login.getPassword());
		return ResponseEntity.ok(response);
	}

}