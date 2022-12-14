package me.nothing.login_.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;




public class _StaffDetails implements UserDetails {

	@Autowired
	private Staff staff;
	
	public _StaffDetails(Staff staff) {
		this.staff = staff;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Set<Role> roles = staff.getRoles();

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for(Role role : roles){
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return authorities;
	}

	@Override
	public String getPassword() {
		if(staff.isOTPRequired()){
			return staff.getOtp();
		}
		
		return staff.getPassword();
	}

	@Override
	public String getUsername() {
		return staff.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public String getFullName() {
		return staff.getUsername();
	}

	public Staff getStaff(){
		return this.staff;
	}


	// Set<Role> roles = staff.getRoles();
	// public boolean hasRole(String roleName){
	// 	Iterator<Role> iterator = roles.iterator();

	// 	while(iterator.hasNext()){
	// 		Role role = iterator.next();
	// 		if(role.getName().equals(roleName)){
	// 			return true;
	
	// 		}
	// 	}

	// 	return false;
 	// }


}
