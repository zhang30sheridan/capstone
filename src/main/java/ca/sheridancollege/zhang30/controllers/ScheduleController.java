package ca.sheridancollege.zhang30.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.zhang30.beans.Employee;
import ca.sheridancollege.zhang30.beans.Schedule;
import ca.sheridancollege.zhang30.repositories.EmployeeRepository;
import ca.sheridancollege.zhang30.repositories.ScheduleRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@ComponentScan(basePackages = {"ca.sheridancollege.zhang30.controllers"})
public class ScheduleController {
	
	private EmployeeRepository employeeRepository;
	
	private ScheduleRepository scheduleRepository;
	

	@GetMapping("/schedule")
	public String scheduleIndex(Model model, Authentication authentication) {
		
		String email = authentication.getName(); 
		
		List<Schedule> scheduleList;
		Employee employee = new Employee();
		
		List<String> roles = new ArrayList<String>(); 
		for (GrantedAuthority ga: authentication.getAuthorities()) { 
			roles.add(ga.getAuthority()); 
		}
		
		int rolesSize = roles.size();
		if (rolesSize == 3) {
			employee = employeeRepository.findByAccountEmail(email);
			model.addAttribute("admin", employee); 
		} else if (rolesSize == 2) {
			employee = employeeRepository.findByAccountEmail(email);
			model.addAttribute("employee", employee);
		}
		
		
		
		scheduleList = scheduleRepository.findByEmployeesIdAndWorkDateAfterOrderByWorkDateAsc(employee.getId(), LocalDate.now());
		
		for(Schedule s : scheduleList) {
			s.setDayOfWeek();
			scheduleRepository.save(s);
			System.out.println(s.getWorkDate());
			System.out.println(s.getDayOfWeek());
		}
		
		model.addAttribute("name", employee.getName());
		model.addAttribute("scheduleList", scheduleList);
		
		return "/schedule/index";
	}
	
	@PostMapping("/addSchedule")
	public String addSchedule(Model model, Authentication authentication, 
			@RequestParam(name="workDate") @DateTimeFormat(iso = ISO.DATE) LocalDate workDate, 
			@RequestParam(name="startWorkTime") @DateTimeFormat(iso = ISO.TIME) LocalTime startWorkTime, 
			@RequestParam(name="endWorkTime") @DateTimeFormat(iso = ISO.TIME) LocalTime endWorkTime) {
		
		List<Schedule> scheduleList;
		
		Employee employee = new Employee();
		
		Schedule schedule = new Schedule(workDate, startWorkTime, endWorkTime);
		
		String email = authentication.getName(); 
		
		List<String> roles = new ArrayList<String>(); 
		for (GrantedAuthority ga: authentication.getAuthorities()) { 
			roles.add(ga.getAuthority()); 
		}
		
		int rolesSize = roles.size();
		if (rolesSize == 3) {
			employee = employeeRepository.findByAccountEmail(email);
			model.addAttribute("admin", employee); 
		} else if (rolesSize == 2) {
			employee = employeeRepository.findByAccountEmail(email);
			model.addAttribute("employee", employee);
		}
		
		employee.getSchedules().add(schedule);
		
		scheduleRepository.save(schedule);
		
		scheduleList = scheduleRepository.findByEmployeesIdAndWorkDateAfterOrderByWorkDateAsc(employee.getId(), LocalDate.now());
		
		for(Schedule s : scheduleList) {
			s.setDayOfWeek();
			scheduleRepository.save(s);
			System.out.println(s.getWorkDate());
			System.out.println(s.getDayOfWeek());
		}

		model.addAttribute("name", employee.getName());
		model.addAttribute("scheduleList", scheduleList);
		
		return "/schedule/index"; 
	}
	
	@GetMapping("/deleteSchedule/{id}")
	public String deleteSchedule(Model model, Authentication authentication, @PathVariable Long id) {
		
		String email = authentication.getName(); 
		
		List<Schedule> scheduleList;
		Employee employee = new Employee();
		
		List<String> roles = new ArrayList<String>(); 
		for (GrantedAuthority ga: authentication.getAuthorities()) { 
			roles.add(ga.getAuthority()); 
		}
		
		int rolesSize = roles.size();
		if (rolesSize == 3) {
			employee = employeeRepository.findByAccountEmail(email);
			model.addAttribute("admin", employee); 
		} else if (rolesSize == 2) {
			employee = employeeRepository.findByAccountEmail(email);
			model.addAttribute("employee", employee);
		}
		
		Schedule schedule = scheduleRepository.findById(id).get();
		
		employee.getSchedules().remove(schedule);
		
		scheduleRepository.deleteById(id);
		
		scheduleList = scheduleRepository.findByEmployeesIdAndWorkDateAfterOrderByWorkDateAsc(employee.getId(), LocalDate.now());
		
		for(Schedule s : scheduleList) {
			s.setDayOfWeek();
			scheduleRepository.save(s);
			System.out.println(s.getWorkDate());
			System.out.println(s.getDayOfWeek());
		}

		model.addAttribute("name", employee.getName());
		model.addAttribute("scheduleList", scheduleList);
		
		return "/schedule/index"; 
	}
	
	@GetMapping("/schedule/all")
	public String allSchedules(Model model, Authentication authentication) {
		
		String email = authentication.getName(); 
		
		List<Schedule> scheduleList;
		Employee employee = new Employee();
		
		List<String> roles = new ArrayList<String>(); 
		for (GrantedAuthority ga: authentication.getAuthorities()) { 
			roles.add(ga.getAuthority()); 
		}
		
		int rolesSize = roles.size();
		if (rolesSize == 3) {
			employee = employeeRepository.findByAccountEmail(email);
			model.addAttribute("admin", employee); 
		} else if (rolesSize == 2) {
			employee = employeeRepository.findByAccountEmail(email);
			model.addAttribute("employee", employee);
		}
		
		
		
		scheduleList = scheduleRepository.findByEmployeesId(employee.getId());
		
		for(Schedule s : scheduleList) {
			s.setDayOfWeek();
			scheduleRepository.save(s);
			System.out.println(s.getWorkDate());
			System.out.println(s.getDayOfWeek());
		}
		
		model.addAttribute("name", employee.getName());
		model.addAttribute("scheduleList", scheduleList);
		
		return "/schedule/index";
	}
	
	@GetMapping("/schedule/past")
	public String pastSchedules(Model model, Authentication authentication) {
		
		String email = authentication.getName(); 
		
		List<Schedule> scheduleList;
		Employee employee = new Employee();
		
		List<String> roles = new ArrayList<String>(); 
		for (GrantedAuthority ga: authentication.getAuthorities()) { 
			roles.add(ga.getAuthority()); 
		}
		
		int rolesSize = roles.size();
		if (rolesSize == 3) {
			employee = employeeRepository.findByAccountEmail(email);
			model.addAttribute("admin", employee); 
		} else if (rolesSize == 2) {
			employee = employeeRepository.findByAccountEmail(email);
			model.addAttribute("employee", employee);
		}
		
		
		
		scheduleList = scheduleRepository.findByEmployeesIdAndWorkDateBeforeOrderByWorkDateAsc(employee.getId(), LocalDate.now());
		
		for(Schedule s : scheduleList) {
			s.setDayOfWeek();
			scheduleRepository.save(s);
			System.out.println(s.getWorkDate());
			System.out.println(s.getDayOfWeek());
		}
		
		model.addAttribute("name", employee.getName());
		model.addAttribute("scheduleList", scheduleList);
		
		return "/schedule/index";
	}
}
