package ca.sheridancollege.zhang30.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.zhang30.beans.Role;
import ca.sheridancollege.zhang30.repositories.AccountRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired 
	private AccountRepository accountRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		// Find the user based on the user name 
		ca.sheridancollege.zhang30.beans.Account account = accountRepo.findByEmail(email); 
		// If the user doesn't exist throw an exception 
		if (account == null) { 
			System.out.println("User not found:" + email); 
			throw new UsernameNotFoundException("User " + email + " was not found in the database"); 
		}
		
		// Change the list of the user's roles into a list of GrantedAuthority 
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>(); 
		for (Role role : account.getRoles()) { 
			grantList.add(new SimpleGrantedAuthority(role.getRolename())); 
		}
		
		// Create a user based on the information above. 
		// This is SpringBoot user, not the user beans
		UserDetails userDetails = (UserDetails) new User(account.getEmail(), account.getEncryptedPassword(), grantList); 
		return userDetails;

	}

}
