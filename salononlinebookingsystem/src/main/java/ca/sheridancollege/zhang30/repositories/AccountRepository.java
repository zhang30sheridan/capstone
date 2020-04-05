package ca.sheridancollege.zhang30.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.zhang30.beans.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	public Account findByEmail(String email);
	
}
