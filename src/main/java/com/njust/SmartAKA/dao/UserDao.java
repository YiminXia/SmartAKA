package com.njust.SmartAKA.dao;

import com.njust.SmartAKA.model.User;
import com.njust.SmartAKA.DaoInf.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import it.unisa.dia.gas.jpbc.Element;
 


import java.io.*;
import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.plaf.jpbc.field.curve.ImmutableCurveElement;
@Service("UserDao")
public class UserDao implements UserDaoInf {
    /**
     * 用户插入自己的公钥与私钥
     */
	public int updateQUAndSU(Element QU,Element SU, String username) {
		PreparedStatement ps=null;
		Connection conn=DataBase.getConn();
	     String sql="update users"
	        		+ " set QU=?,SU=?"
	        		+ " where Username=?";
		int num=0;
		try {
		    ps=conn.prepareStatement(sql);
		    ps.setObject(1, QU);
		    ps.setObject(2, SU);
		    ps.setString(3, username);
		    num=ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(null,ps,null);
		}
	    return num;	
	}
	/**
	 * 用户获取自己或者别人的公钥
	 */
	public Element getQU(String username) {
	    Connection conn=null;
	    PreparedStatement pstmt=null;
	    ResultSet rs=null;
	    Element QU = null;
	    conn=DataBase.getConn();
	    String sql="select QU from users where Username=? ";
	    try {	
			pstmt=conn.prepareStatement(sql);
		    pstmt.setString(1, username);
			rs=pstmt.executeQuery();
		    if(rs.next()){
				  Blob inblob = (Blob) rs.getBlob("QU");
	              InputStream is = inblob.getBinaryStream();
	              BufferedInputStream input = new BufferedInputStream(is);	             
	              byte[] buff = new byte[(int) inblob.length()];//放到一个buff 字节数组
	              while(-1 != (input.read(buff, 0, buff.length)));	             
	              ObjectInputStream in =new ObjectInputStream(new ByteArrayInputStream(buff));
	              QU = (ImmutableCurveElement)in.readObject();//从IO流中读取出来.可以得到一个对象了
	             // System.out.println("Alice的私钥：SA="+QU);//输出对象中指定的数据  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, pstmt, null);
		}
		return QU;		
	}
/**
 * 获得自己的私钥
 */
	public Element getSU(String username) {
		    Connection conn=null;
		    PreparedStatement pstmt=null;
		    ResultSet rs=null;
		    Element SU = null;
		    conn=DataBase.getConn();
		    String sql="select SU from users where Username=? ";
		    try {	
				pstmt=conn.prepareStatement(sql);
			    pstmt.setString(1, username);
				rs=pstmt.executeQuery();
			    if(rs.next()){
					  Blob inblob = (Blob) rs.getBlob("SU");
		              InputStream is = inblob.getBinaryStream();
		              BufferedInputStream input = new BufferedInputStream(is);
		             
		              byte[] buff = new byte[(int) inblob.length()];//放到一个buff 字节数组
		              while(-1 != (input.read(buff, 0, buff.length)));
		             
		              ObjectInputStream in =new ObjectInputStream(new ByteArrayInputStream(buff));
		              SU = (ImmutableCurveElement)in.readObject();//从IO流中读取出来.可以得到一个对象了
		             // System.out.println("Alice的私钥：SA="+SU);//输出对象中指定的数据  
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {				
				e.printStackTrace();
			} catch (ClassNotFoundException e) {				
				e.printStackTrace();
			}finally{
				DBUtils.close(rs, pstmt, null);
			}
			return SU;
	}
	
   /**
    * 用户更新自己的随机数与TU
    */
	public int updateaAndTU(Element a,Element TU,String username){
		PreparedStatement ps=null;
		Connection conn=DataBase.getConn();
	     String sql="update users"
	        		+ " set a=?,TU=?"
	        		+ " where Username=?";
		int num=0;
		try {
		    ps=conn.prepareStatement(sql);
		    ps.setObject(1, a);
		    ps.setObject(2, TU);
		    ps.setString(3, username);
		    num=ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(null,ps,null);
		}
	    return num;	
	}
	
	
	
	
	
	
	/**
	 * 用户获取自己的随机数
	 */
	public Element geta(String username) {
		   Connection conn=null;
		    PreparedStatement pstmt=null;
		    ResultSet rs=null;
		    Element a= null;
		    conn=DataBase.getConn();
		    String sql="select a from users where Username=? ";
		    try {	
				pstmt=conn.prepareStatement(sql);
			    pstmt.setString(1, username);
				rs=pstmt.executeQuery();
			    if(rs.next()){
					  Blob inblob = (Blob) rs.getBlob("a");
		              InputStream is = inblob.getBinaryStream();
		              BufferedInputStream input = new BufferedInputStream(is);	             
		              byte[] buff = new byte[(int) inblob.length()];//放到一个buff 字节数组
		              while(-1 != (input.read(buff, 0, buff.length)));	             
		              ObjectInputStream in =new ObjectInputStream(new ByteArrayInputStream(buff));
		              a = (Element)in.readObject();//从IO流中读取出来.可以得到一个对象了
		             // System.out.println("Alice的私钥：SA="+QU);//输出对象中指定的数据  
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {			
				e.printStackTrace();
			} catch (ClassNotFoundException e) {			
				e.printStackTrace();
			} catch (NullPointerException e){
				return null;
			}finally{
				DBUtils.close(rs, pstmt, null);
			}
			return a;		
	}
	/**
	 * 用户获取自己或者别人的TU
	 */
	public Element getTU(String username) {
		Connection conn=null;
	    PreparedStatement pstmt=null;
	    ResultSet rs=null;
	    Element TU= null;
	    conn=DataBase.getConn();
	    String sql="select TU from users where Username=? ";
	    try {	
			pstmt=conn.prepareStatement(sql);
		    pstmt.setString(1, username);
			rs=pstmt.executeQuery();
		    if(rs.next()){
				  Blob inblob = (Blob) rs.getBlob("TU");
	              InputStream is = inblob.getBinaryStream();
	              BufferedInputStream input = new BufferedInputStream(is);	             
	              byte[] buff = new byte[(int) inblob.length()];//放到一个buff 字节数组
	              while(-1 != (input.read(buff, 0, buff.length)));	             
	              ObjectInputStream in =new ObjectInputStream(new ByteArrayInputStream(buff));
	              TU = (ImmutableCurveElement)in.readObject();//从IO流中读取出来.可以得到一个对象了
	             // System.out.println("Alice的私钥：SA="+QU);//输出对象中指定的数据  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}catch (NullPointerException e){
			return null;
		}finally{
			DBUtils.close(rs, pstmt, null);
		}
		return TU;		
	}
	public User findByUsername(String username) {
		Connection conn=null;
	    PreparedStatement pstmt=null;
	    ResultSet rs=null;
	    User user=null;
	    conn=DataBase.getConn();
	    String sql="select *from users where Username=? ";
	    try {	
			pstmt=conn.prepareStatement(sql);
		    pstmt.setString(1, username);
			rs=pstmt.executeQuery();
		    if(rs.next()){
		    	//int uid = rs.getInt("uid");
				String name = rs.getString("Username");
				String pw = rs.getString("Password");
				//int balance = rs.getInt("balance");
				user = new User( name, pw);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, pstmt, null);
		}
		return user;		
	}
	public User findByUsernameFromaccountone(String username) {
		Connection conn=null;
	    PreparedStatement pstmt=null;
	    ResultSet rs=null;
	    User user=null;
	    conn=DataBase.getConn();
	    String sql="select *from accountone where username=? ";
	    try {	
			pstmt=conn.prepareStatement(sql);
		    pstmt.setString(1, username);
			rs=pstmt.executeQuery();
		    if(rs.next()){
		    	//int uid = rs.getInt("uid");
				String name = rs.getString("username");
				String pw = rs.getString("password");
				//int balance = rs.getInt("balance");
				user = new User( name, pw);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, pstmt, null);
		}
		return user;		
		
	}
	public List<User> selectAllUsers() {
		String sql = "select * from users";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		try {
			ps = DataBase.getConn().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				//int uid = rs.getInt("uid");
				String name = rs.getString("username");
				String pw = rs.getString("password");
				//int balance = rs.getInt("balance");
				User user = new User( name, pw);
				users.add(user);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBUtils.close(rs, ps, null);
		}
		return users;
	}
	public int updatea(Element a, String username) {
		PreparedStatement ps=null;
		Connection conn=DataBase.getConn();
	     String sql="update users"
	        		+ " set a=?"
	        		+ " where Username=?";
		int num=0;
		try {
		    ps=conn.prepareStatement(sql);
		    ps.setObject(1, a);
		 
		    ps.setString(2, username);
		    num=ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(null,ps,null);
		}
	    return num;	
		
		
	}
	public int updateTU(Element TU, String username) {
		PreparedStatement ps=null;
		Connection conn=DataBase.getConn();
	     String sql="update users"
	        		+ " set TU=?"
	        		+ " where Username=?";
		int num=0;
		try {
		    ps=conn.prepareStatement(sql);
		    ps.setObject(1, TU);
		 
		    ps.setString(2, username);
		    num=ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(null,ps,null);
		}
	    return num;	
		
	}
	public int rigiest(String username, String password) {
		String sql = "insert into users(Username,Password) values(?,?)";
		int num = 0;
		PreparedStatement ps = null;
		try {
			 ps = DataBase.getConn().prepareStatement(sql);
			/*
			 * 设置替代符的内容，
			 * 前面的参数1表示第几个替代符
			 * 后面的参数表示替代符要替代的内容
			 */
			ps.setString(1, username);
			ps.setString(2, password);
			
			num = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtils.close(null, ps, null);
		}
		return num;
	
	}
	
	
	
	
	
	
   
}
