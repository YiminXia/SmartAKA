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

//导入JPBC相应的jar包
import it.unisa.dia.gas.jpbc.*;  





@Controller
public class ComputeQUAndSUController{
	@Autowired
	private UserDao ud=new UserDao();
	@Autowired
	 private AdminDao ad=new AdminDao();
	@RequestMapping(value = "users/keys/extractSecretKeyOf{username}" ,method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody List<HashMap>  getSecretKey(@PathVariable("username") String username) {  
		   System.out.println("-------------------用户公私钥对产生阶段----------------------");  
	        Field G1=ad.getG1FormDB();
	        Element s=ad.getsFromDB();
	        
	        Element QU = G1.newElement().setFromHash(username.getBytes(), 0, username.length())  
	                .getImmutable();// //从长度为3的Hash值IDu确定用户U产生的公钥Qa，生成用户U的公钥。        
	        Element SU = QU.mulZn(s).getImmutable();//生成用户U的私钥。   
	        System.out.println("用户"+username+"的公钥:" + QU);  
	        System.out.println("用户"+username+"的私钥:" + SU);
		    
	        ud.updateQUAndSU(QU, SU, username);//用户将自己的公私钥对插入到数据库中
		  
		   List<HashMap> listone = new ArrayList();
		   HashMap<String, String> newsMap = new HashMap<String, String>();
		   newsMap.put("P",SU.toString() );
		   listone.add(newsMap);
		   return listone;   
		
		 
	 }	 

	/*
	@RequestMapping(value = "users/keys/computeTof{username}" ,method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody List<HashMap>  getT(@PathVariable("username") String username) {
		
	   System.out.println("-------------------随机选择一个Zr群中的元素a，然后计算TA=aP，再将TA发送给Bob阶段----------------------");  
	   System.out.println(username);
	   HashMap<String,Element> paramentMap5=new HashMap<String,Element>();
	   HashMap<String,Element> paramentMap6=new HashMap<String,Element>();
	   HashMap<String,Element> paramentMap7=new HashMap<String,Element>();
	   HashMap<String,Element> paramentMap8=new HashMap<String,Element>();
	   if((listOfParaments.contains(paramentMap5)==false)&&(listOfParaments.contains(paramentMap6)==false)&&(listOfParaments.contains(paramentMap7)==false)&&(listOfParaments.contains(paramentMap8)==false)){
		   a = Zr.newRandomElement().getImmutable();  //
	       TA = P.mulZn(a);
	       paramentMap5.put("a of"+username, a);
		   paramentMap6.put("T of"+username, TA);
		   listOfParaments.add(paramentMap5);
		   listOfParaments.add(paramentMap6);
		   List<HashMap> listone = new ArrayList();
		   HashMap<String, String> newsMap = new HashMap<String, String>();
		   newsMap.put("P",TA.toString() );
		   listone.add(newsMap);
		   return listone;   
	   }
	   else{
		   if((listOfParaments.contains(paramentMap5)==true)&&(listOfParaments.contains(paramentMap6)==true)&&(listOfParaments.contains(paramentMap7)==false)&&(listOfParaments.contains(paramentMap8)==false)){
			   if(paramentMap6.keySet().contains("T of"+username)){
				   List<HashMap> listone = new ArrayList();
	    		   HashMap<String, String> newsMap = new HashMap<String, String>();
	    		   newsMap.put("P",paramentMap6.get("T of"+username).toString());
	    		   listone.add(newsMap);
	    		   return listone;				   
			   }
			   else{
				   a = Zr.newRandomElement().getImmutable();  //
			       TA = P.mulZn(a);
			       paramentMap7.put("a of"+username, a);
				   paramentMap8.put("T of"+username, TA);
				   listOfParaments.add(paramentMap7);
				   listOfParaments.add(paramentMap8);
				   List<HashMap> listone = new ArrayList();
				   HashMap<String, String> newsMap = new HashMap<String, String>();
				   newsMap.put("P",TA.toString() );
				   listone.add(newsMap);
				   return listone;   				   
			   }			   
		   }
		   else{
			   if(paramentMap6.keySet().contains("T of"+username)){
	    			 List<HashMap> listone = new ArrayList();
	    			 HashMap<String, String> newsMap = new HashMap<String, String>();
	    			 newsMap.put("P",paramentMap6.get("T of"+username).toString());
	    			 listone.add(newsMap);
	    			 return listone;
	    			 }
	    		 else{
	    			 List<HashMap> listone = new ArrayList();
	    			 HashMap<String, String> newsMap = new HashMap<String, String>();
	    			 newsMap.put("P",paramentMap8.get("T of"+username).toString());
	    			 listone.add(newsMap);
	    			 return listone;
	    		 }			   
		   }		   
	   }
	}	        

	
	@RequestMapping(value = "users/keys/createKOf{username}" ,method = {RequestMethod.GET, RequestMethod.POST})
    public  @ResponseBody List<HashMap> getK (@PathVariable("username") String username) {   		   
            System.out.println(username.split("#")[0]);
            System.out.println(username.split("#")[1]); 
            String stringKA;
       
       
    	   if(listOfParaments.size()==8){      
                 for(int i=0;i<listOfParaments.size();i++){
                   HashMap<String,Element> map = listOfParaments.get(i);
                   if(map.keySet().contains("private key of"+username.split("#")[0])){
                 	  System.out.println("private key of"+username.split("#")[0]+(Element) listOfParaments.get(i).get("private key of"+username.split("#")[0]));
                 	  SA=(Element) listOfParaments.get(i).get("private key of"+username.split("#")[0]);
                   }
                 }
                 
                 for(int i=0;i<listOfParaments.size();i++){
                     HashMap<String,Element> map = listOfParaments.get(i);
                     if(map.keySet().contains("a of"+username.split("#")[0])){
                   	  System.out.println("a of"+username.split("#")[0]+(Element) listOfParaments.get(i).get("a of"+username.split("#")[0]));
                   	  a=(Element) listOfParaments.get(i).get("a of"+username.split("#")[0]);
                     }
                   }
                 
                 for(int i=0;i<listOfParaments.size();i++){
                     HashMap<String,Element> map = listOfParaments.get(i);
                     if(map.keySet().contains("T of"+username.split("#")[1])){
                   	  System.out.println((Element) listOfParaments.get(i).get("T of"+username.split("#")[1]));
                   	  TB=(Element) listOfParaments.get(i).get("T of"+username.split("#")[1]);
                     }
                   }
                 for(int i=0;i<listOfParaments.size();i++){
                     HashMap<String,Element> map = listOfParaments.get(i);
                     if(map.keySet().contains("public key of"+username.split("#")[1])){
                   	  System.out.println((Element) listOfParaments.get(i).get("public key of"+username.split("#")[1]));
                   	  QB=(Element) listOfParaments.get(i).get("public key of"+username.split("#")[1]);
                     }
                   }
                 
                 
                 h=TB.mulZn(a);
                 KA=pairing.pairing((Ppub.mulZn(a)).mul(SA),TB.mul(QB));     
                 System.out.println("KA=e(aPpub+SA,TB+QB)=" + KA);
                 System.out.println(KA.toString()+h.toString()+(username.split("#")[0]+username.split("#")[1]));
                 stringKA=SHA(KA.toString()+h.toString(), "SHA-256");
                
    	   }
            else{
            	for(int i=0;i<listOfParaments.size();i++){
                    HashMap<String,Element> map = listOfParaments.get(i);
                    if(map.keySet().contains("private key of"+username.split("#")[0])){
                  	  System.out.println((Element) listOfParaments.get(i).get("private key of"+username.split("#")[0]));
                  	  SA=(Element) listOfParaments.get(i).get("private key of"+username.split("#")[0]);
                    }
                  }
                  
                  for(int i=0;i<listOfParaments.size();i++){
                      HashMap<String,Element> map = listOfParaments.get(i);
                      if(map.keySet().contains("a of"+username.split("#")[0])){
                    	  System.out.println((Element) listOfParaments.get(i).get("a of"+username.split("#")[0]));
                    	  a=(Element) listOfParaments.get(i).get("a of"+username.split("#")[0]);
                      }
                    }
                  
                  for(int i=0;i<listOfParaments.size();i++){
                      HashMap<String,Element> map = listOfParaments.get(i);
                      if(map.keySet().contains("T of"+username.split("#")[1])){
                    	  System.out.println((Element) listOfParaments.get(i).get("T of"+username.split("#")[1]));
                    	  TB=(Element) listOfParaments.get(i).get("T of"+username.split("#")[1]);
                      }
                    }
                  for(int i=0;i<listOfParaments.size();i++){
                      HashMap<String,Element> map = listOfParaments.get(i);
                      if(map.keySet().contains("public key of"+username.split("#")[1])){
                    	  System.out.println((Element) listOfParaments.get(i).get("public key of"+username.split("#")[1]));
                    	  QB=(Element) listOfParaments.get(i).get("public key of"+username.split("#")[1]);
                      }
                    }                 
                  h=TB.mulZn(a);                  
                  KA=pairing.pairing((Ppub.mulZn(a)).mul(SA),TB.mul(QB));     
                  System.out.println("KA=e(aPpub+SA,TB+QB)=" + KA); 
                  System.out.println(KA.toString()+h.toString()+(username.split("#")[1]+username.split("#")[0]));
                  stringKA=SHA(KA.toString()+h.toString(), "SHA-256"); 
            	
            }
            
            List<HashMap> listone = new ArrayList();
	 	    HashMap<String, String> newsMap = new HashMap<String, String>();
	 		newsMap.put("P",stringKA);
	 	    listone.add(newsMap);
	 	    return listone;
       }
  
	
	

	private void checkSymmetric(Pairing pairing) {  
        if (!pairing.isSymmetric()) {  
            throw new RuntimeException("密钥不对称!");  
        }  
    }  
	 private void init() {  
		    listOfParaments = new ArrayList();
		    
	        pairing = PairingFactory.getPairing("conf/spring/a.properties");//  
	        PairingFactory.getInstance().setUsePBCWhenPossible(true);  
	        checkSymmetric(pairing);  
	        //将变量r初始化为Zr中的元素  
	        Zr = pairing.getZr();  
	        a = Zr.newElement(); 
	        b=Zr.newElement(); 
	        //将变量Ppub，Qa，Sa，V初始化为G1中的元素，G1是加法群  
	        G1 = pairing.getG1();  
	        Ppub = G1.newElement();  
	        QA = G1.newElement();  
	        SA = G1.newElement(); 	        
	        QB=G1.newElement(); 
	        SB=G1.newElement(); 
	        h=G1.newElement();
	      
	        TA=G1.newElement();
	        TB=G1.newElement();     
	        //将变量T1，T2V初始化为GT中的元素，GT是乘法群  
	        Field GT = pairing.getGT();         
	        KA=GT.newElement();  
	        KB=GT.newElement();  
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
	    */
}
