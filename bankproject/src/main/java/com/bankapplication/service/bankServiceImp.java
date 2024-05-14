package com.bankapplication.service;

import com.bankapplication.entity.BankAccount;

public interface bankServiceImp {
	
	BankAccount saveAccount(BankAccount account);
	BankAccount getAccount(Long account_no);
	BankAccount withDrawDetails(Long account_no,String name, String password, double amount);
	BankAccount transferAmount(Long account_no,Long tacc_no,String name,String password,double amount);
	BankAccount depositeAmount(long acc,String name, String password,double amount);
	String closeAcount(Long account_no,String name,String password);
	

}
