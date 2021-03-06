package com.feliphecosta.sistemarpgmongodb.user.services;

import java.util.Optional;

import com.feliphecosta.sistemarpgmongodb.charactersheet.dto.CharacterSheetDTO;
import com.feliphecosta.sistemarpgmongodb.charactersheet.service.CharacterSheetService;
import com.feliphecosta.sistemarpgmongodb.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.feliphecosta.sistemarpgmongodb.user.domain.User;
import com.feliphecosta.sistemarpgmongodb.user.dto.UserNewDTO;
import com.feliphecosta.sistemarpgmongodb.user.repository.UserRepository;
import com.feliphecosta.sistemarpgmongodb.util.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private CharacterSheetService characterSheetService;
	
	public User findById(String id) {
		
		Optional<User> optional = userRepo.findById(id);
		
		return optional.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado!"));
	}
	
	public User insert(User user) {
		return userRepo.insert(user);
	}

	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	public UserDTO updateCharacterSheet(String email, String idCharacterSheet) {

		User user = this.findByEmail(email);
		user.setCharacterSheet(idCharacterSheet);

		return fromEntity(userRepo.save(user));
	}

	public User fromDTO(UserNewDTO userNewDTO) {
		return new User(null, userNewDTO.getEmail(), pe.encode(userNewDTO.getPassword()), null);
	}

	public UserDTO fromEntity(User user) {
		UserDTO userDTO = new UserDTO();

		userDTO.setEmail(user.getEmail());

		if (user.getCharacterSheet() != null) {
			userDTO.setCharacterSheet(new CharacterSheetDTO(characterSheetService.findById(user.getCharacterSheet())));
		}

		return userDTO;
	}
	
}
