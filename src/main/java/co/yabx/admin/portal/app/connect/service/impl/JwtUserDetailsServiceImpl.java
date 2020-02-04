package co.yabx.admin.portal.app.connect.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.yabx.admin.portal.app.connect.service.JwtUserDetailsService;
import co.yabx.admin.portal.app.kyc.service.AuthInfoService;

@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {

	@Autowired
	private AuthInfoService authInfoService;

	@Override
	public UserDetails loadUserByUsername(String username) throws Throwable {
		return (UserDetails) Optional.ofNullable(username).map(String::valueOf).flatMap(authInfoService::findByUsername)
				.orElseThrow(() -> new UsernameNotFoundException("Cannot find user with username=" + username));
	}

}
