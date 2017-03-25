package com.njust.SmartAKA.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.njust.SmartAKA.dao.AdminDao;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
@Controller
public class BuildSystemController {
	
	
    protected Element s, P, Ppub, SA, QA,SB,QB, TA,TB,KA,KB,hA,hB;  
	
    protected Field G1, Zr,GT;  
	
    protected Pairing pairing;  
  
  
  
    // 初始化
    private void init() throws SQLException {  
    	pairing = PairingFactory.getPairing("conf/spring/a.properties");//  
        PairingFactory.getInstance().setUsePBCWhenPossible(true);  
        checkSymmetric(pairing);  
   
        Zr = pairing.getZr();  
    
        G1 = pairing.getG1();

        GT = pairing.getGT();  
     
    }  
  
  //判断配对是否为对称配对，不对称则输出错误信息 
      
    private void checkSymmetric(Pairing pairing) {  
        if (!pairing.isSymmetric()) {  
            throw new RuntimeException("密钥不对称!");  
        }  
    }  
    @RequestMapping("users/keys/buildSystem.html")
    public void buildSystem() throws SQLException {  
        System.out.println("-------------------系统建立阶段----------------------");  
        init();  
        s = Zr.newRandomElement().getImmutable();// //随机生成主私钥s  
        P = G1.newRandomElement().getImmutable();// 生成G1的生成元P  
        Ppub = P.mulZn(s);// 计算Ppub=sP,注意顺序  ，生成主公钥Ppub。
        AdminDao ad=new AdminDao();
        int i=ad.updatePairingAndZrAndG1AndGTAndsAndPAndPpub(pairing, Zr, G1, GT, s, P, Ppub);
        System.out.println("等于一就成功了"+i);
        System.out.println("G1群的生成元P=" + P); 
        System.out.println("s属于Zr群，主私钥s=" + s);  
        System.out.println("Ppub属于G1群，主公钥Ppub=" + Ppub);  
    }  
}
