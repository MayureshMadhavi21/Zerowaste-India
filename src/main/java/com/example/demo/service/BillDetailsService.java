package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BillingDetails;
import com.example.demo.repository.BillDetailsRepository;



@Service
public class BillDetailsService {

	@Autowired
	BillDetailsRepository bill;
	
	public  void addBillDetails(BillingDetails billingDetails) {
		bill.save(billingDetails);
	}
}

