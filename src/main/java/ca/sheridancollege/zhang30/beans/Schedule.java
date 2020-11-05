package ca.sheridancollege.zhang30.beans;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@RequiredArgsConstructor
public class Schedule {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NonNull
	private LocalDate workDate;
	private DayOfWeek dayOfWeek;
	@NonNull
	private LocalTime startWorkTime;
	@NonNull
	private LocalTime endWorkTime;
	
	@ManyToMany(mappedBy="schedules") 
	private List<Employee> employees = new ArrayList<Employee>();
	
	public void setDayOfWeek() {
		this.dayOfWeek = this.workDate.getDayOfWeek();
	}
	
	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}
}
