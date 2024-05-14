package com.bankapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapplication.entity.BankAccount;
import com.bankapplication.repository.BankRepository;

@Service
public class BankService implements bankServiceImp {
    
    @Autowired
    BankRepository bankRepository;
//
//    public void saveAccount(BankAccount account) {
//        bankRepository.save(account);        
//    }
    
    public BankAccount getAccount(Long account_no) {
        return bankRepository.findById(account_no).get();
    }
    
    public BankAccount depositeAmount(long acc, String name, String password, double amount) {
        BankAccount account = bankRepository.findById(acc).get();
        Long acno = account.getAccount_no();
        String Pass = account.getPassword();
        String Name = account.getName();
        double amt = account.getAmount();
        
        if (acc == acno && Pass.equals(password) && name.equals(Name)) {
            amt = account.getAmount() + amount;
        }
        
        account.setAmount(amt);
        bankRepository.save(account);
        return account;
    }

    public BankAccount withDrawDetails(Long Account_no, String name, String password, double amount) {
        BankAccount a  = bankRepository.findById(Account_no).get();
        Long accountinfo = a.getAccount_no();
        String pass = a.getPassword();
        String Name = a.getName();
        double amt = a.getAmount();
        
        if (accountinfo == Account_no && name.equals(Name) && pass.equals(password)) {
            if (amt < amount) {
                return null;
            } else {
                amt = amt - amount;
            }
        }
        a.setAmount(amt);
        bankRepository.save(a);
        return a;
    }
    
    public BankAccount transferAmount(Long account_no, Long tacc_no, String name, String password, double amount) {
    	    System.out.println("acc:no"+account_no);
    	    System.out.println("acc:no"+tacc_no);

    	    BankAccount acc = bankRepository.findById(account_no).orElse(null);
        BankAccount acc1 = bankRepository.findById(tacc_no).orElse(null);
        
        if (acc != null && acc1 != null && acc.getPassword().equals(password) && acc.getName().equals(name)) 
           {
                double currentBalance = acc.getAmount();
                System.out.println("current bal::::"+currentBalance);
                if (currentBalance >= amount) {
                    double targetBalance = acc1.getAmount() + amount;
                    System.out.println("targetbalance::::::"+targetBalance);
                    acc.setAmount(currentBalance - amount);
                    acc1.setAmount(targetBalance);
                    System.out.println("ddddddddddddddddddd");
                    bankRepository.save(acc);
                    System.out.println("dddddddddrrrrrrrrrrr");
                    bankRepository.save(acc1);
                    System.err.println("wwwwwwwwwwwwwwwwwwwwwwww");
                    return acc1;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
    
    
    public String closeAcount(Long account_no,String name,String password)
    {
    	BankAccount account = bankRepository.findById(account_no).get();
    	if(account!=null)
    	{
    		if(account_no == account.getAccount_no() && account.getPassword().equals(password) && account.getName().equals(name))
    		{
    			account.setAmount(0);
    			bankRepository.delete(account);
    			return "account closed successfully";
    		}
    		else
    		{
    			return "wrong details ,please enter right one ";
    		}
    	}
    	else
    	{
    		return "doesn't exists";
    	}   	
    			
    	
    	
    }
    public BankAccount saveAccount(BankAccount acc)
    {
    	BankAccount account = bankRepository.save(acc);
    	return account;
    }
    
}
