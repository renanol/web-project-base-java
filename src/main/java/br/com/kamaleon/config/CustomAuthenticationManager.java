package br.com.kamaleon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import br.com.kamaleon.model.User;
import br.com.kamaleon.service.UserService;

public class CustomAuthenticationManager implements AuthenticationManager {

	@Autowired
	private UserService userService;

	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		User userTmp =  new User();
		userTmp.setUsername(username);
		userTmp.setPassword(password);

		User user = userService.getUser(userTmp);

		if (user == null) {
			throw new BadCredentialsException("Username not found.");
		}

		if (!password.equals(user.getPassword())) {
			throw new BadCredentialsException("Wrong password.");
		}

		//Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

		return new UsernamePasswordAuthenticationToken(username, password, null);
	}
}