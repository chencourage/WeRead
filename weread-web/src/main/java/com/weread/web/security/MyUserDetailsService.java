package com.weread.web.security;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private ICustomerService customerService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EntityWrapper<Customer> wrapper = new EntityWrapper<>();
		wrapper.eq("login_no", username);
		Customer user = customerService.selectOne(wrapper);
		if(user==null) {
			throw new UsernameNotFoundException("user not exists!");
		}
		return  new org.springframework.security.core.userdetails.User(user.getId().toString(),
				user.getPwd(), new HashSet<>());
	}

}
