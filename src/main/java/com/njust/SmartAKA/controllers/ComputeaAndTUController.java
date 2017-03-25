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
public class ComputeaAndTUController {
	@Autowired
	private UserDao ud=new UserDao();
	@Autowired
	private AdminDao ad=new AdminDao();
	@RequestMapping(value = "users/keys/computeTof{username}" ,method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody List<HashMap>  getT(@PathVariable("username") String username) {
	   System.out.println("-------------------随机选择一个Zr群中的元素a，然后计算TA=aP，再将TA发送给Bob阶段----------------------");  
	   System.out.println(username); 
	   if(username.contains("#")){
		   String name1=username.split("#")[0];
		   String name2=username.split("#")[1];
		
			   Pairing pairing = PairingFactory.getPairing("conf/spring/a.properties");//   
			   Element P=ad.getPFromDB();
			   Element a = pairing.getZr().newRandomElement().getImmutable();  //
			   Element TU = P.mulZn(a); 
			   System.out.println(name1+"随机选择一个Zr群中的元素=" + a);  
			   System.out.println(name1+"计算TU=aP,TU=" + TU);
			   ud.updateaAndTU(a, TU, name1);//用户将自己的TU与随机数插入到数据中
			   

		   //计算完K的时候清空a和TU，获取TU的时候，先从数据库获取，if判断如果是是空的
			   List<HashMap> listone = new ArrayList();
			   HashMap<String, String> newsMap = new HashMap<String, String>();
			   newsMap.put("P",TU.toString() );
			   listone.add(newsMap);
			   return listone;    
		  
	   }
	   else{
		   List<HashMap> listone = new ArrayList();
		   HashMap<String, String> newsMap = new HashMap<String, String>();
		   newsMap.put("P","请选择对话对象" );
		   listone.add(newsMap);
		   return listone;    
	   }
		
	} 
}
