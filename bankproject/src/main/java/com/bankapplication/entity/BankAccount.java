package com.bankapplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {
	
	 	 @Id	 	
		 private Long Account_no;
		 private String name;
	     private String password;
	     private double amount;
	     private String address;
	     private Long mobileno;
	

}
