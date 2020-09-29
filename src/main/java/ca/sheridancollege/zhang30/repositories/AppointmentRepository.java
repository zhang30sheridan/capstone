package ca.sheridancollege.zhang30.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.zhang30.beans.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
