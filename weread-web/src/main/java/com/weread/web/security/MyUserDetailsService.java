package com.weread.web.security;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.weread.service.sys.entity.SysUser;
import com.weread.service.sys.service.ISysUserService;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private ISysUserService sysUserService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EntityWrapper<SysUser> wrapper = new EntityWrapper<>();
		wrapper.eq("login_name", username);
		SysUser user = sysUserService.selectOne(wrapper);
		if(user==null) {
			throw new UsernameNotFoundException("user not exists!");
		}
		return  new org.springframework.security.core.userdetails.User(user.getId().toString(),
				user.getPassword(), new HashSet<>());
	}

}
