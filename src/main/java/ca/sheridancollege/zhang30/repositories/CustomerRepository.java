package ca.sheridancollege.zhang30.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.zhang30.beans.Account;
import ca.sheridancollege.zhang30.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer findByAccount(Account account);
	
	public Customer findByAccountEmail(String email);
}
