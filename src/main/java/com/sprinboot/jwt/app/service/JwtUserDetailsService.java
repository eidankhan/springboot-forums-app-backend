package com.sprinboot.jwt.app.service;

import com.sprinboot.jwt.app.model.User;
import com.sprinboot.jwt.app.dto.UserDTO;
import com.sprinboot.jwt.app.transformer.UserTransformer;
import com.sprinboot.jwt.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Autowired
	private UserTransformer userTransformer;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public UserDTO save(UserDTO userDTO) {
		User user = userTransformer.userDTOToUser(userDTO);
		// Encoding password before saving to database
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		User savedUser = userDao.save(user);
		return userTransformer.userToUserDTO(savedUser);
	}
}