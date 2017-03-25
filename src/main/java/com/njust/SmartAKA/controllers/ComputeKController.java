package com.njust.SmartAKA.controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.njust.SmartAKA.dao.AdminDao;
import com.njust.SmartAKA.dao.UserDao;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

@Controller
public class ComputeKController {

	
	@Autowired
	private UserDao ud=new UserDao();
	@Autowired
	private AdminDao ad=new AdminDao();	
	Pairing pairing = PairingFactory.getPairing("conf/spring/a.properties");// 
	
	@RequestMapping(value = "users/keys/createKOf{username}" ,method = {RequestMethod.GET, RequestMethod.POST})
    public  @ResponseBody List<HashMap> getK (@PathVariable("username") String username) {   		   
		 String name1=username.split("#")[0];
         String name2=username.split("#")[1];
         System.out.println("name1="+name1);
         List<HashMap> listone = new ArrayList();
		  HashMap<String, String> newsMap = new HashMap<String, String>();
         String KO=null;
		 Element h=null;
		 Element K=null;
		 Element Ppub=ad.getPpubFromDB();
		 Element SU=ud.getSU(name1);
		 Element QU=ud.getQU(name2);
		 Element a=ud.geta(name1);
		
		 Element TU=ud.getTU(name2);
		
			     h=TU.mulZn(a);
		         K=pairing.pairing((Ppub.mulZn(a)).mul(SU),TU.mul(QU));  
		         KO=SHA(K.toString()+h.toString(), "SHA-256"); 
		         System.out.println(name1+"的K="+K);
		     //    ud.updatea(null, name1);
		         
		         
				  newsMap.put("P",KO );
				  listone.add(newsMap);
				  return listone;  
	
		 
        
        
	
	}
	
	
	
	
	
	
	   private String SHA(final String strText, final String strType)  
	    {  
	      // 返回值  
	      String strResult = null;  
	    
	      // 是否是有效字符串  
	      if (strText != null && strText.length() > 0)  
	      {  
	        try  
	        {  
	          // SHA 加密开始  
	          // 创建加密对象 并傳入加密類型  
	          MessageDigest messageDigest = MessageDigest.getInstance(strType);  
	          // 传入要加密的字符串  
	          messageDigest.update(strText.getBytes());  
	          // 得到 byte 類型结果  
	          byte byteBuffer[] = messageDigest.digest();  
	    
	          // 將 byte 轉換爲 string  
	          StringBuffer strHexString = new StringBuffer();  
	          // 遍歷 byte buffer  
	          for (int i = 0; i < byteBuffer.length; i++)  
	          {  
	            String hex = Integer.toHexString(0xff & byteBuffer[i]);  
	            if (hex.length() == 1)  
	            {  
	              strHexString.append('0');  
	            }  
	            strHexString.append(hex);  
	          }  
	          // 得到返回結果  
	          strResult = strHexString.toString();  
	        }  
	        catch (NoSuchAlgorithmException e)  
	        {  
	          e.printStackTrace();  
	        }  
	      }  
	    
	      return strResult;  
	    } 
}
