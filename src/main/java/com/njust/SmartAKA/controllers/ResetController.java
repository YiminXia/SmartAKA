package com.njust.SmartAKA.controllers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.njust.SmartAKA.dao.AdminDao;
import com.njust.SmartAKA.dao.UserDao;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;




@Controller
public class ResetController {
	
	@Autowired
	private UserDao ud=new UserDao();
	
	
	@RequestMapping(value = "users/keys/resetSecretKeyOf{username}" ,method = {RequestMethod.GET, RequestMethod.POST})
    public void  resetSecretKeyOf(@PathVariable("username") String username) {  
	   ud.updateaAndTU(null, null, username);
	   
	}

}
