package com.algaworks.algamoney.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.algaworks.algamoney.api.models.UserSystem; 
public class UserLogged extends User {

	
	private static final long serialVersionUID = 1L;
	private UserSystem userSystem;

	public UserLogged(UserSystem userSystem, Collection<? extends GrantedAuthority> authorities) {
		super(userSystem.getEmail(), userSystem.getPassword(), authorities);
		this.userSystem = userSystem;
	}

	public UserSystem getUserSystem() {
		return userSystem;
	}

}
