package com.example.demo.controller;

import javax.servlet.http.HttpSession; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.demo.global.GlobalData;
import com.example.demo.model.BillingDetails;
import com.example.demo.model.Product;
import com.example.demo.service.BillDetailsService;
import com.example.demo.service.EmailService;
import com.example.demo.service.ProductService;

@Controller
public class CartController {
	@Autowired
	ProductService productService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	BillDetailsService billDetailsService;
	
	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id) {
		GlobalData.cart.add(productService.getProductById(id).get());
		return "redirect:/shop";
	}
	
	@GetMapping("/cart")
	public String cartGet(Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("cart", GlobalData.cart);
		return "cart";
	}
	@GetMapping("/cart/removeItem/{index}")
	public String cartItemRemove(@PathVariable int index) {
		GlobalData.cart.remove(index);
		return "redirect:/cart";
	}
	@GetMapping("/Invoice")
	public String checkout(Model model) {
	
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		
		if( GlobalData.cart.stream().mapToDouble(Product::getPrice).sum()==0)
			return "/cart";
		else
		{
			BillingDetails bd=new BillingDetails();
			model.addAttribute("billDetails",bd);
			return "Invoice";
		}
		
	
	}
	
	@PostMapping("/savebillingdetails")
	public String billdetails(@ModelAttribute BillingDetails bdt,@RequestParam("total") String total,@RequestParam("ftotal") String ftotal,HttpSession session) {
		double totalT = Double.parseDouble(total);                       // Long.parseLong(total);
		bdt.settAmount(totalT);
		double totalF = Double.parseDouble(ftotal);
		bdt.setpAmount(totalF);
		billDetailsService.addBillDetails(bdt);

		
		
		
		String email=bdt.getEmail();
		String fname=bdt.getFirstName();
		String lname=bdt.getLastName();
		String addp=bdt.getAdrressp1();
		String addpt=bdt.getAdrressp2();
		String city=bdt.getCity();
		double tAmount=bdt.gettAmount();
		
		String subject="Message From E-Zero-Waste Shopping ";
		String message=""
				+ "<div style='border:1px solid #0d0d0d; padding:20px; background-image:"
				+ " url(https://images.unsplash.com/photo-1582623081787-05843bd8947a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=507&q=80)'>"
		        + "<h4>"
				+"Dear,"+ fname +" "+ lname+" "+"<br>"
				+"Your order has been sucessfully placed"+"<br>"
				+ "Address : "+addp+","+addpt+"<br>"+"City :"+city+"<br>"+"Amount :"+tAmount
				+ "</h3> "
				+ "<h5>"
				+"Regards,\n"+"<br>"
				+"E-Zero-Waste group"+"<br>"
				+"from Rudresh Mahajan"+"<br>"
				+ "<h5>"
				+ "</div>";
		String to=email;
		
		boolean flag = this.emailService.sendEmail(subject, message, to);
		
		if(flag)
		{
			System.out.println("Mail Sent");
		}else
		{	
			
			System.out.println("Mail Not Sent");
		}
		
	
		return "index";
		
	}
	
	
	
	
	
	
	
}