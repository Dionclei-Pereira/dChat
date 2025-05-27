package me.dionclei.dchat.documents;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import me.dionclei.dchat.enums.UserRole;

@Document
public class dUser implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String name;
	private String password;
	private UserRole role;
	
	public dUser() {}
	
	public dUser(String id, String name, String password, UserRole role) {
		super();
		this.id = id;
		this.name = name;
		this.password = new BCryptPasswordEncoder().encode(password);
		this.role = role;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (role == UserRole.ADMIN) return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
		
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getUsername() {
		return name;
	}
}
