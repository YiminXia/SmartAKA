package com.njust.SmartAKA.DaoInf;

import com.njust.SmartAKA.model.Administrator;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;

public interface AdminDaoInf {
	/**
	 * 管理员登录
	 */
	public Administrator adminLogin(String adminname);
	
	
	  /**
	    * 服务器更新pairing,Zr,G1,GT,s,P,Ppub
	    */
    public int updatePairingAndZrAndG1AndGTAndsAndPAndPpub(Pairing pairing,Field Zr,Field G1,Field GT,Element s,Element P,Element Ppub);
    /**
     * 服务器公开Zr
     */
     public Field getZrFormDB();
     /**
      * 服务器公开G1
      */
     public Field getG1FormDB();
     /**
      * 服务器公开GT
      */
     public Field getGTFromDB();
     /**
      * 服务器公开P
      */
     public Element getPFromDB();
     /**
      * 服务器公开Ppub
      */
     public Element getPpubFromDB();
     /**
      * 服务器允许生成用户公私钥对时候，获取主密钥
      */
      public Element getsFromDB(); 
   
}
