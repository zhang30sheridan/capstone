package ca.sheridancollege.zhang30.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.zhang30.beans.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public Employee findByAccountEmail(String email);
	
	public List<Employee> findByIsEnabledTrue();
	
	public List<Employee> findByIsEnabledFalse();
	
	public List<Employee> findAllByOrderByIsEnabledDesc();
	
	public List<Employee> findAllByOrderByIsEnabledAsc();
}
