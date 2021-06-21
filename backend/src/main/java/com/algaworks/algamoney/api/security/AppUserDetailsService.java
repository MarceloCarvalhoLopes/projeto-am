package com.algaworks.algamoney.api.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.models.UserSystem;
import com.algaworks.algamoney.api.repositories.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<UserSystem> userOptional = userRepository.findByEmail(email);
		UserSystem userSystem =  userOptional.orElseThrow(()-> new UsernameNotFoundException("Username and/or password incorrect"));
		return new UserLogged(userSystem,getPermissions(userSystem));
	}

	private Collection<? extends GrantedAuthority> getPermissions(UserSystem userSystem) {
		Set<SimpleGrantedAuthority> authorities =  new HashSet<>();
		userSystem.getPermissions().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescription().toUpperCase())));
		
		return authorities;
	}

}
