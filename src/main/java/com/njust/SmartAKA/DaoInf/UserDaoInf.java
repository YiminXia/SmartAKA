package com.njust.SmartAKA.DaoInf;

import java.util.ArrayList;
import java.util.List;

import com.njust.SmartAKA.model.User;

import it.unisa.dia.gas.jpbc.Element;

public interface UserDaoInf {
	
	
	 public User findByUsername(String username); 
	 
	 
	 public User findByUsernameFromaccountone(String username);
	 
	 
	 public List<User> selectAllUsers();
   
	 
	
	
    /**
     * 用户注册
     */
	  public int rigiest(String username,String password);
   
	/**
	 * 用户登录
	 */
   //public boolean login(String username,String password);
    /**
     * 用户存储自己的公私钥对
     * @param QU   用户公钥  SU用户的私钥
     * @param username  用户名
     * @return
     */
    public int updateQUAndSU(Element QU,Element SU,String username);
    /**
     * 获取用户的公钥
     * @param username  用户名
     * @return 公钥
     */
    public Element getQU(String username);
  
    public Element getSU(String username);

    public int updateaAndTU(Element a,Element TU,String username);
    
    public int updatea(Element a,String username);
    
    public int updateTU(Element TU ,String username);
    
    public Element geta(String username);
    
    public Element getTU(String username);
    

}
