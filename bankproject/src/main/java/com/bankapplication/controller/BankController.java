package com.bankapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;

import com.bankapplication.entity.BankAccount;
import com.bankapplication.service.BankService;

@Controller
public class BankController {
	
	@Autowired
	BankService bankService;
	
	@RequestMapping("/")
	public String homepage()
	{
		return "home";
	}
	
	
	
	@PostMapping("/register")
	public String registerit(@ModelAttribute BankAccount bank)
	{
		bankService.saveAccount(bank);
		return "success";
	}
	
	
	
	
	@RequestMapping("/newAccount")
	public String newAccount()
	{
		return "newer";
	}
	
	

	@RequestMapping("/balance")
	public String Balance()
	{
		return "balance";
	}
	
	@RequestMapping("/getbalance")
	public String balanceEnquiry(@RequestParam("account_no") Long account_no, ModelMap m)
	{
		m.addAttribute("account", bankService.getAccount(account_no));
		return "accountbalance";
	}
	
	@RequestMapping("/deposit")
	public String Deposit()
	{
		return "depositform";
	}
	
	@RequestMapping("/getdeposit")
	public String depositDetails(@RequestParam("account_no") Long acc,@RequestParam String name,@RequestParam String password,@RequestParam double amount,Model m  )
	{
		m.addAttribute("account", bankService.depositeAmount(acc,name,password,amount));
		m.addAttribute("present", amount);
		return "depositResult";
	}
	
	@RequestMapping("/withdraw")
	public String withDraw()
	{
		return "withdrawform";
	}
	
	@RequestMapping("/getwithdraw")
	public String withdrawDetails(@RequestParam Long account_no,@RequestParam String name,@RequestParam String password, @RequestParam double amount,Model m)
	{
		BankAccount acc = bankService.withDrawDetails(account_no,name,password,amount);
		if(acc != null)
		{
			m.addAttribute("account", acc);
			m.addAttribute("Amt",amount);
			return "withdrawresult";
			
		}
		else
		{
			return "insufficient balance";
		}
		
	}
	
	@RequestMapping("/transfer")
	public String Transfer()
	{
		return "transfer";
	}
	
	@GetMapping("/gettransfer")
	public String transferDetails(@RequestParam Long account_no,@RequestParam Long tacc_no,@RequestParam String name,@RequestParam String password,@RequestParam double amount,Model m)
	{
		BankAccount acc = bankService.transferAmount(account_no,tacc_no,name,password,amount);
		System.out.println("acc:nollllllllll"+acc);
		if(acc!= null)
		{
			System.out.println("acc:no"+acc.toString());
			m.addAttribute("amount",bankService.getAccount(account_no));
			m.addAttribute("a", amount);
			m.addAttribute("acc1", bankService.getAccount(tacc_no));
			return "transferResult";
		}
		else
		{
			return "details are invalid....";
		}
	}
	
	@RequestMapping("/closeaccount")
	public String closeAccount()
	{
		return "closeAccountForm";
	}
		
	@RequestMapping("/closeA/C")
	public String closeNow(@RequestParam Long account_no,@RequestParam String name,@RequestParam String password,Model m)
	{
		String result = bankService.closeAcount(account_no,name,password);
		m.addAttribute("result", result);
		return "closeAcResult";
	}
	
	@RequestMapping("/aboutus")
	public String aboutUs()
	{
		return "aboutus";
	}


	
}
