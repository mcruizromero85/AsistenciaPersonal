package com.asistp.assistance.service;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asistp.domain.Roles;
import com.asistp.domain.Worker;


@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Worker worker=null;
		try{
			worker=Worker.findByField("username", username);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return new User(worker.getUsername(), worker.getPassword(), true, true, true, true, getGrantedAuthorities( worker.getId() +""));
	}
	
	/**
	 * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
	 * @param roles {@link String} of roles
	 * @return list of granted authorities
	 */
	public static List<GrantedAuthority> getGrantedAuthorities(String idWorker) {
		
		List listRoles= Roles.findByField("worker.id", idWorker+"");
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		try{
			for (int i=0;i<listRoles.size();i++) {
				Roles role=(Roles)listRoles.get(i);
				authorities.add(new SimpleGrantedAuthority(role.getName()));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return authorities;
		
	}

}
