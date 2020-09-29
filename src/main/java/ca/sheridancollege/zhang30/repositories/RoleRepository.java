package ca.sheridancollege.zhang30.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.zhang30.beans.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	public Role findByRolename(String rolename);

}
