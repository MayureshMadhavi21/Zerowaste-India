package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.BillingDetails;





public interface BillDetailsRepository extends JpaRepository<BillingDetails, Integer> {
	

}
