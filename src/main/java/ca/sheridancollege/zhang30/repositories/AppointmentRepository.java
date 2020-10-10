package ca.sheridancollege.zhang30.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.zhang30.beans.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	public List<Appointment> findByEmployeeId (Long employeeId);
	
	public List<Appointment> findByCustomerId (Long customerId);
}
