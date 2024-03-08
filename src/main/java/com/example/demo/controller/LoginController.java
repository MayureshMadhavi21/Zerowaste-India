package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.global.GlobalData;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmailService;

@Controller
public class LoginController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private EmailService emailService;

	@GetMapping("/login")
	public String login() {
		GlobalData.cart.clear();
		return "login";
	}

	@GetMapping("/register")
	public String registerGet() {
		return "register";
	}

	
	  @PostMapping("/register") public String registerPost(@ModelAttribute("user")
	  User user, HttpServletRequest request) throws ServletException { String
	  password = user.getPassword();
	  user.setPassword(bCryptPasswordEncoder.encode(password)); List<Role> roles =
	  new ArrayList<>(); roles.add(roleRepository.findById(2).get());
	  user.setRoles(roles); if (userRepository.save(user) != null) {
	  emailService.sendEmail("Zero Waste",
	  "<div style='border:1px solid #0d0d0d; padding:20px; background-image:" +
	  " url(https://images.unsplash.com/photo-1533656940951-019afcaa07b0?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80)'>"
	  + "<h3>"
	   
	  + "Welcome to E-Zero Waste" +
	  
	  "<br>" +
	  
	  "<br>" + "Dear " + user.getFirstName() + " " + user.getLastName() + ",\n\n" +
	  "<br>" + "You are successfully registered!!!!" + "<br>" + "<br>" + "<br>" +
	  
	  "</h3>" +
	  
	  "<h5>" +
	  
	  "To receive a personalised shopping experience, tell us about yourself" +
	  "<br>" + "<br>" + "<br>" + "\nWarm Regards," + "<br>" + "\nE-Zero-Waste" +
	  "<br>" +
	  
	  "from Rudresh Mahajan" + "</h5>" + "</div>", user.getEmail()); }
	  request.login(user.getEmail(), password); return "redirect:/"; }
	 
	
	/*
	 * @PostMapping("/register") public String registerPost(@ModelAttribute("user")
	 * User user,HttpServletRequest request) throws ServletException{ String
	 * password = user.getPassword();
	 * user.setPassword(bCryptPasswordEncoder.encode(password)); List<Role>
	 * roles=new ArrayList<>(); roles.add(roleRepository.findById(2).get());
	 * user.setRoles(roles); userRepository.save(user);
	 * request.login(user.getEmail(),password); return "redirect:/"; }
	 */

}
