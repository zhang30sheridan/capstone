package ca.sheridancollege.zhang30.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.zhang30.beans.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	
	public List<Schedule> findByEmployeesId(Long id);
	
	public List<Schedule> findByEmployeesIdAndWorkDateAfterOrderByWorkDateAsc(Long id, LocalDate date);
	
	public List<Schedule> findByEmployeesIdAndWorkDateBeforeOrderByWorkDateAsc(Long id, LocalDate date);
	
	public List<Schedule> findByEmployeesIdAndWorkDate(Long id, LocalDate date);

}
