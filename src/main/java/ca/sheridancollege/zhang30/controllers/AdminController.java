package ca.sheridancollege.zhang30.controllers;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.zhang30.beans.Account;
import ca.sheridancollege.zhang30.beans.Employee;
import ca.sheridancollege.zhang30.beans.Role;
import ca.sheridancollege.zhang30.repositories.AccountRepository;
import ca.sheridancollege.zhang30.repositories.EmployeeRepository;
import ca.sheridancollege.zhang30.repositories.RoleRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@ComponentScan(basePackages = {"ca.sheridancollege.zhang30.controllers"})
public class AdminController {

	private EmployeeRepository employeeRepository;
	private AccountRepository accountRepository;
	private RoleRepository roleRepository;
	
	private String encodePassword(String password) { 
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
		return encoder.encode(password); 
	}
	
	@GetMapping("/admin")
	public String adminIndex(Model model) {
		List<Employee> employeeList = employeeRepository.findByIsEnabledTrue();
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
			account.setEmployee(true);
			account.setCustomer(false);
			account.getRoles().add(roleRepository.findByRolename("ROLE_USER"));
			account.getRoles().add(roleRepository.findByRolename("ROLE_EMP"));
			if(roleAdmin) {
				account.setAdmin(true);
				account.getRoles().add(roleRepository.findByRolename("ROLE_ADMIN"));
			}
			accountRepository.save(account);
			
			Employee employee = Employee.builder()
					.name(name)
					.avatar("/images/avatars/newUser.png")
					.title(title)
					.service(services)
					.phone(phone)
					.dscr(dscr)
					.isEnabled(true)
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

		employeeRepository.findById(employeeEdited.getId()).get().setEnabled(employeeEdited.isEnabled());
		
		if(employeeEdited.isEnabled()) {
			employeeRepository.findById(employeeEdited.getId()).get().getAccount().setAdmin(employeeEdited.getAccount().isAdmin());
			if(employeeEdited.getAccount().isAdmin()) {
				List<Role> roleList = employeeRepository.findById(employeeEdited.getId()).get().getAccount().getRoles();
				if(roleList.size() == 2) {
					employeeRepository.findById(employeeEdited.getId()).get().getAccount().getRoles().add(roleRepository.findByRolename("ROLE_ADMIN"));
				}
			}else {
				List<Role> roleList = employeeRepository.findById(employeeEdited.getId()).get().getAccount().getRoles();
				if(roleList.size() == 3) {
					employeeRepository.findById(employeeEdited.getId()).get().getAccount().getRoles().remove(roleRepository.findByRolename("ROLE_ADMIN"));
				}
			}
		}

		employeeRepository.save(employeeRepository.findById(employeeEdited.getId()).get());

		model.addAttribute("employeeList", employeeRepository.findAllByOrderByIsEnabledDesc());
		model.addAttribute("employeeEdited", employeeEdited);
		return "/admin/index";
	}
	
	@GetMapping("/admin/deleteEmployee/{id}")
	public String deleteEmployee(Model model, @PathVariable Long id) {
		
		Employee employee = employeeRepository.findById(id).get();
		
		System.out.print(employee.getName());
		
		model.addAttribute("deleteEmployee", employee);
		model.addAttribute("employeeList", employeeRepository.findAll());
		
		return "/admin/index :: #deleteModal";
	}
	
	@GetMapping("/admin/doDeleteEmployee/{id}")
	public String doDeleteEmployee(Model model, @PathVariable Long id) {
		
		Employee employee = employeeRepository.findById(id).get();
		
		employeeRepository.deleteById(id);
		
		System.out.print(employee.getName());
		
		model.addAttribute("employeeList", employeeRepository.findByIsEnabledTrue());
		
		return "/admin/index";
	}
	
	@GetMapping("/admin/findAllEmployees") 
	public String allEmployees(Model model) {
		List<Employee> employeeList = employeeRepository.findAllByOrderByIsEnabledDesc();
		model.addAttribute("employeeList", employeeList);
		return "/admin/index";
	}
	
	@GetMapping("/admin/findDeactivatedEmployees") 
	public String deactivatedEmployees(Model model) {
		List<Employee> employeeList = employeeRepository.findByIsEnabledFalse();
		model.addAttribute("employeeList", employeeList);
		return "/admin/index";
	}
	
}
