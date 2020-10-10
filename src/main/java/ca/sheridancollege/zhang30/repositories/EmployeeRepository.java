package ca.sheridancollege.zhang30.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.zhang30.beans.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public Employee findByAccountEmail(String email);
}
