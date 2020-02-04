package co.yabx.admin.portal.app.connect.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtUserDetailsService {

	UserDetails loadUserByUsername(String username) throws Throwable;

}
