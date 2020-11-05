package ca.sheridancollege.zhang30.beans;

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

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime time;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private String note;
	private Long employeeId;
	private Long customerId;
	
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="appointments") 
	private List<Account> accounts = new ArrayList<Account>();
}
