package ca.sheridancollege.zhang30.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.zhang30.beans.Account;
import ca.sheridancollege.zhang30.beans.Appointment;
import ca.sheridancollege.zhang30.beans.Customer;
import ca.sheridancollege.zhang30.beans.Employee;
import ca.sheridancollege.zhang30.repositories.AccountRepository;
import ca.sheridancollege.zhang30.repositories.AppointmentRepository;
import ca.sheridancollege.zhang30.repositories.CustomerRepository;
import ca.sheridancollege.zhang30.repositories.EmployeeRepository;
import ca.sheridancollege.zhang30.repositories.RoleRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@ComponentScan(basePackages = {"ca.sheridancollege.zhang30.controllers"})
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
	public String userIndex(Model model, Authentication authentication) {
		
//		appointmentRepository.deleteAll();
		
//		if(appointmentRepository.count() == 0) {
//			
//			Appointment appointment = Appointment.builder().date(LocalDate.of(2020, 5, 1)).time(LocalTime.of(5, 30)).note("This is test. This is test. This is test.").build();
//			appointmentRepository.save(appointment);
//		}
//		
		String email = authentication.getName(); 
		
		List<String> roles = new ArrayList<String>(); 
		for (GrantedAuthority ga: authentication.getAuthorities()) { 
			roles.add(ga.getAuthority()); 
		}
		
		int rolesSize = roles.size();
		if (rolesSize == 3) {
			Employee employee = employeeRepository.findByAccountEmail(email);
			model.addAttribute("admin", employee);
			model.addAttribute("name", employee.getName()); 
			model.addAttribute("appList", appointmentRepository.findByEmployeeId(employee.getId()));
		} else if (rolesSize == 2) {
			Employee employee = employeeRepository.findByAccountEmail(email);
			model.addAttribute("employee", employee);
			model.addAttribute("name", employee.getName());
			model.addAttribute("appList", appointmentRepository.findByEmployeeId(employee.getId()));
		} else {
			Customer customer = customerRepository.findByAccountEmail(email);
			model.addAttribute("customer", customer);
			model.addAttribute("name", customer.getName());
			model.addAttribute("appList", appointmentRepository.findByCustomerId(customer.getId()));
		}
		
		
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
	
	@GetMapping("/admin")
	public String adminIndex(Model model) {
		List<Employee> employeeList = employeeRepository.findAll();
		model.addAttribute("employeeList", employeeList);
		return "/admin/index";
	}
	
	@GetMapping("/admin/newEmployee")
	public String newEmployee(Model model) {
		return "/admin/newEmployee";
	}
	
	@PostMapping("/admin/newEmployee") 
	public String doNewEmployee(Model model, @RequestParam String email, @RequestParam String name, @RequestParam String title, @RequestParam String services, 
			@RequestParam String phone, @RequestParam String dscr, @RequestParam String password, @RequestParam(defaultValue = "false") boolean roleAdmin) {
		
		Account checkAccount = accountRepository.findByEmail(email);
		
		if(checkAccount == null) {
			Account account = new Account(email, encodePassword(password), Byte.valueOf("1")); 
			account.getRoles().add(roleRepository.findByRolename("ROLE_USER"));
			account.getRoles().add(roleRepository.findByRolename("ROLE_EMP"));
			if(roleAdmin) {
				account.getRoles().add(roleRepository.findByRolename("ROLE_ADMIN"));
			}
			accountRepository.save(account);
			
			Employee employee = Employee.builder()
					.name(name)
					.avatar("/images/avatars/newUser.png")
					.title(title)
					.services(services)
					.phone(phone)
					.dscr(dscr)
					.enabled(Byte.valueOf("1"))
					.build();
			employee.setAccount(account);
			
			employeeRepository.save(employee);

			model.addAttribute("newEmployee", employee);
		}else {
			model.addAttribute("emailExist", checkAccount);
		}
		return "/admin/newEmployee"; 
	}
	
	@GetMapping("/admin/editEmployee/{id}")
	public String editEmployee(Model model, @PathVariable Long id) {
		
		Employee employee = employeeRepository.findById(id).get();
		
		System.out.print(employee.getName());
		
		model.addAttribute("editEmployee", employee);
		model.addAttribute("employeeList", employeeRepository.findAll());
		
		return "/admin/index :: #editModal";
	}
	
	@PostMapping("/admin/doEditEmployee")
	public String doEditEmployee(Model model, @ModelAttribute Employee employee) {
		Employee employeeEdited = employee;
		
		employeeRepository.findById(employeeEdited.getId()).get().setName(employeeEdited.getName());
		employeeRepository.findById(employeeEdited.getId()).get().setTitle(employeeEdited.getTitle());
		employeeRepository.findById(employeeEdited.getId()).get().setServices(employeeEdited.getServices());
		employeeRepository.findById(employeeEdited.getId()).get().setPhone(employeeEdited.getPhone());
		employeeRepository.findById(employeeEdited.getId()).get().setDscr(employeeEdited.getDscr());
		
		model.addAttribute("employeeList", employeeRepository.findAll());
		model.addAttribute("employeeEdited", employeeEdited);
		return "/admin/index";
	}
	
	// push to github
	
	
}
