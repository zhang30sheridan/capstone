package ca.sheridancollege.zhang30.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.zhang30.beans.Appointment;
import ca.sheridancollege.zhang30.beans.Customer;
import ca.sheridancollege.zhang30.beans.Employee;
import ca.sheridancollege.zhang30.beans.Account;
import ca.sheridancollege.zhang30.repositories.AccountRepository;
import ca.sheridancollege.zhang30.repositories.AppointmentRepository;
import ca.sheridancollege.zhang30.repositories.CustomerRepository;
import ca.sheridancollege.zhang30.repositories.EmployeeRepository;
import ca.sheridancollege.zhang30.repositories.RoleRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	private EmployeeRepository employeeRepository;
	private CustomerRepository customerRepository;
	private AppointmentRepository appointmentRepository;
	
	private AccountRepository accountRepository;
	
	private RoleRepository roleRepository;
	
	private String encodePassword(String password) { 
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
		return encoder.encode(password); 
	}

	@GetMapping("/")
	public String index(Model model) {
		List<Employee> employeeList = employeeRepository.findAll();
		model.addAttribute("employeeList", employeeList);
		return "index";
	}
	
	@GetMapping("/user/fillAppointment/{id}")
	public String fillAppointment(Model model, @PathVariable Long id) {
		Employee employee = employeeRepository.findById(id).get();
		model.addAttribute("employee", employee);
		model.addAttribute("appointment", new Appointment());
		return "/user/fillAppointment";
	}
	
	@PostMapping("addAppointment")
	public String addAppointment(Model model, @ModelAttribute Appointment appointment, @RequestParam Long employeeId) {
		Appointment newApp = appointmentRepository.save(appointment);
		newApp.setEmployeeId(employeeId);
		Employee employee = employeeRepository.findById(employeeId).get();
		model.addAttribute("employee", employee);
		model.addAttribute("newAppointment", newApp);
		model.addAttribute("appointment", new Appointment());
		return "/user/fillAppointment";
	}
	
	@PostMapping("/user/changeAppointment")
	public String changeAppointment(Model model, @ModelAttribute Appointment appointment) {
		Appointment newApp = appointment;
		appointmentRepository.findById(newApp.getId()).get().setDate(newApp.getDate());
		appointmentRepository.findById(newApp.getId()).get().setTime(newApp.getTime());
		model.addAttribute("newAppointment", newApp);
		model.addAttribute("appList", appointmentRepository.findAll());
		return "/user/index";
	}
	
	@GetMapping("/login") 
	public String login() { 
		return "login"; 
	}
	
	@GetMapping("/user") 
	public String userIndex(Model model) {
		
//		appointmentRepository.deleteAll();
		
//		if(appointmentRepository.count() == 0) {
//			
//			Appointment appointment = Appointment.builder().date(LocalDate.of(2020, 5, 1)).time(LocalTime.of(5, 30)).note("This is test. This is test. This is test.").build();
//			appointmentRepository.save(appointment);
//		}
//		
		
		
		List<Appointment> appList = appointmentRepository.findAll();
		
		model.addAttribute("appList", appList);
		
		return "/user/index"; 
	}
	
	@GetMapping("/user/editApp/{id}")
	public String editApp(Model model, @PathVariable Long id) {
		
		model.addAttribute("editApp", appointmentRepository.findById(id));
//		appointmentRepository.deleteById(id);
		model.addAttribute("appList", appointmentRepository.findAll());
		
		
		return "/user/index";
	}
	
	@GetMapping("/register") 
	public String goRegistration () { 
		return "register"; 
	} 
	
	@PostMapping("/register") 
	public String doRegistration(Model model, @RequestParam String email, @RequestParam String name, @RequestParam String phone, @RequestParam String password) {
		
		Account checkAccount = accountRepository.findByEmail(email);
		
		if(checkAccount == null) {
			Account account = new Account(email, encodePassword(password), Byte.valueOf("1")); 
			account.getRoles().add(roleRepository.findByRolename("ROLE_USER"));
			accountRepository.save(account);
			
			Customer customer = Customer.builder()
					.name(name)
					.phone(phone)
					.build();
			customer.setAccount(account);
			
			customerRepository.save(customer);
			
			return "redirect:/";
		}else {
			model.addAttribute("emailExist", checkAccount);
		}
		return "register"; 
	}
	
	// test
	
}
