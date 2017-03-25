package com.njust.SmartAKA.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njust.SmartAKA.dao.UserDao;
import com.njust.SmartAKA.model.Administrator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.njust.SmartAKA.dao.AdminDao;
import com.njust.SmartAKA.dao.UserDao;
import com.njust.SmartAKA.model.Administrator;
import com.njust.SmartAKA.model.User;
import com.njust.SmartAKA.model.Users;
import com.njust.SmartAKA.controllers.*;






@Controller
public class RegisterController {

	UserDao ud=new UserDao();
	
	@RequestMapping("/logins/login/register")
	   public String register(HttpServletRequest request,HttpServletResponse response,Administrator entity,HttpSession httpSession )
		{      
			  System.out.println("输入的用户名："+request.getParameter("usernamee"));
			  System.out.println("输入的密码："+request.getParameter("passwd"));   
			  String username=request.getParameter("usernamee");
			  String password=request.getParameter("passwd");
			  System.out.println(username.length());
			  System.out.println(password.length());
		
		  // String username="xiaoli";
		   //String password="555555";
		if((2<username.length()&username.length()<13)&&(2<password.length()&password.length()<13)){   
			if(ud.rigiest(username, password)==1)
			 return "redirect:/";
		}
		else{
			 System.out.println("注册的用户名密码不符合长短空");
			 return "redirect:/logins/login";
		}
		 return "redirect:/logins/login";
		
   }
}