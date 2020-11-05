package ca.sheridancollege.zhang30.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String avatar;
	private String title;
	private String service;
	private String phone;
	private String dscr;
	@NonNull
	private boolean isEnabled;
	
	@OneToOne
	@JoinTable(name="EMPLOYEE_ACCOUNT", joinColumns = @JoinColumn(name="EMPLOYEE_ID"), inverseJoinColumns=@JoinColumn(name="ACCOUNT_ID"))
	private Account account;
	
	@ManyToMany
	private List<Service> services = new ArrayList<Service>();
	
	@ManyToMany(cascade=CascadeType.ALL) 
	private List<Schedule> schedules = new ArrayList<Schedule>();
}
