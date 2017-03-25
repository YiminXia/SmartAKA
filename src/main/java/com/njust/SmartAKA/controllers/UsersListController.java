package com.njust.SmartAKA.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;


import com.njust.SmartAKA.dao.UserDao;
import com.njust.SmartAKA.model.User;

//导入JPBC相应的jar包
//import it.unisa.dia.gas.jpbc.*;  
//import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;  
//import java.lang.reflect.Proxy;  
//import java.util.Calendar;  
//import java.text.SimpleDateFormat;  
//import java.util.Date;
@Controller
public class UsersListController{
	
	@Autowired
	private UserDao ud;
	@RequestMapping("users/news/selectednews.json")
	public @ResponseBody List<HashMap> getUsersList() {
	     System.out.println("获取全部用户");
		 List<HashMap> listone = new ArrayList();
		  // HashMap<String, String> newsMap = new HashMap<String, String>();
		 //  newsMap.put("P",TU.toString() );
		 //  listone.add(newsMap);
		 //  return listone;   
			
			for(User tmp:ud.selectAllUsers())
			{
				HashMap<String, String> newsMap = new HashMap<String, String>();
				newsMap.put("username", tmp.getUsername());
			
				listone.add(newsMap);
				
			}
			
	
		return listone;
    }  
	
	
}

