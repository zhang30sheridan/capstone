package ca.sheridancollege.zhang30.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RequiredArgsConstructor
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NonNull
	private String name;
	@NonNull
	private String phone;
	
	@OneToMany
	private List<Appointment> appointmentList;
	
	@OneToOne
	@JoinTable(name="CUSTOMER_ACCOUNT", joinColumns = @JoinColumn(name="CUSTOMER_ID"), inverseJoinColumns=@JoinColumn(name="ACCOUNT_ID"))
	private Account account;

}
