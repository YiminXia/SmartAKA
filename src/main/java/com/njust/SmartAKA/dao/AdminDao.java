package com.njust.SmartAKA.dao;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.njust.SmartAKA.DaoInf.AdminDaoInf;
import com.njust.SmartAKA.model.Administrator;
import com.njust.SmartAKA.model.User;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.field.curve.ImmutableCurveElement;

@Service("AdminDao")
public class AdminDao implements AdminDaoInf {
   
	public int updatePairingAndZrAndG1AndGTAndsAndPAndPpub(Pairing pairing, Field Zr, Field G1, Field GT, Element s,
			Element P, Element Ppub) {
		
		PreparedStatement ps=null;
		Connection conn=DataBase.getConn();
	     String sql="update admin"
	        		+ " set Pairing=?,Zr=?,G1=?,GT=?,s=?,P=?,Ppub=?"
	        		+ " where name=?";
		int num=0;
		try {
		    ps=conn.prepareStatement(sql);
		    ps.setObject(1, pairing);
		    ps.setObject(2, Zr);
		    ps.setObject(3,G1);
		    ps.setObject(4,GT);
		    ps.setObject(5,s);
		    ps.setObject(6, P);
		    ps.setObject(7, Ppub);
		    ps.setString(8, "admin");
		    num=ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(null,ps,null);
		}
	    return num;	
	}

	
	public Field getZrFormDB() {
		    Connection conn=null;
		    PreparedStatement pstmt=null;
		    ResultSet rs=null;
		    Field Zr = null;
		    conn=DataBase.getConn();
		    String sql="select Zr from admin where name=? ";
		    try {	
				pstmt=conn.prepareStatement(sql);
			    pstmt.setString(1, "admin");
				rs=pstmt.executeQuery();
			    if(rs.next()){
					  Blob inblob = (Blob) rs.getBlob("Zr");
		              InputStream is = inblob.getBinaryStream();
		              BufferedInputStream input = new BufferedInputStream(is);	             
		              byte[] buff = new byte[(int) inblob.length()];//放到一个buff 字节数组
		              while(-1 != (input.read(buff, 0, buff.length)));	             
		              ObjectInputStream in =new ObjectInputStream(new ByteArrayInputStream(buff));
		              Zr= (Field)in.readObject();//从IO流中读取出来.可以得到一个对象了
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
			return Zr;		
	}

	
	public Field getG1FormDB() {
		    Connection conn=null;
		    PreparedStatement pstmt=null;
		    ResultSet rs=null;
		    Field G1 = null;
		    conn=DataBase.getConn();
		    String sql="select G1 from admin where name=? ";
		    try {	
				pstmt=conn.prepareStatement(sql);
			    pstmt.setString(1, "admin");
				rs=pstmt.executeQuery();
			    if(rs.next()){
					  Blob inblob = (Blob) rs.getBlob("G1");
		              InputStream is = inblob.getBinaryStream();
		              BufferedInputStream input = new BufferedInputStream(is);	             
		              byte[] buff = new byte[(int) inblob.length()];//放到一个buff 字节数组
		              while(-1 != (input.read(buff, 0, buff.length)));	             
		              ObjectInputStream in =new ObjectInputStream(new ByteArrayInputStream(buff));
		              G1= (Field)in.readObject();//从IO流中读取出来.可以得到一个对象了
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
			return G1;		
	}

	
	public Field getGTFromDB() {
		    Connection conn=null;
		    PreparedStatement pstmt=null;
		    ResultSet rs=null;
		    Field GT = null;
		    conn=DataBase.getConn();
		    String sql="select GT from admin where name=? ";
		    try {	
				pstmt=conn.prepareStatement(sql);
			    pstmt.setString(1, "admin");
				rs=pstmt.executeQuery();
			    if(rs.next()){
					  Blob inblob = (Blob) rs.getBlob("GT");
		              InputStream is = inblob.getBinaryStream();
		              BufferedInputStream input = new BufferedInputStream(is);	             
		              byte[] buff = new byte[(int) inblob.length()];//放到一个buff 字节数组
		              while(-1 != (input.read(buff, 0, buff.length)));	             
		              ObjectInputStream in =new ObjectInputStream(new ByteArrayInputStream(buff));
		              GT= (Field)in.readObject();//从IO流中读取出来.可以得到一个对象了
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
			return GT;		
	}

	
	public Element getPFromDB() {
		    Connection conn=null;
		    PreparedStatement pstmt=null;
		    ResultSet rs=null;
		    Element P = null;
		    conn=DataBase.getConn();
		    String sql="select P from admin where name=? ";
		    try {	
				pstmt=conn.prepareStatement(sql);
			    pstmt.setString(1, "admin");
				rs=pstmt.executeQuery();
			    if(rs.next()){
					  Blob inblob = (Blob) rs.getBlob("P");
		              InputStream is = inblob.getBinaryStream();
		              BufferedInputStream input = new BufferedInputStream(is);	             
		              byte[] buff = new byte[(int) inblob.length()];//放到一个buff 字节数组
		              while(-1 != (input.read(buff, 0, buff.length)));	             
		              ObjectInputStream in =new ObjectInputStream(new ByteArrayInputStream(buff));
		              P = (Element)in.readObject();//从IO流中读取出来.可以得到一个对象了
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
			return P;		
	}

	
	public Element getPpubFromDB() {
		    Connection conn=null;
		    PreparedStatement pstmt=null;
		    ResultSet rs=null;
		    Element Ppub = null;
		    conn=DataBase.getConn();
		    String sql="select Ppub from admin where name=? ";
		    try {	
				pstmt=conn.prepareStatement(sql);
			    pstmt.setString(1, "admin");
				rs=pstmt.executeQuery();
			    if(rs.next()){
					  Blob inblob = (Blob) rs.getBlob("Ppub");
		              InputStream is = inblob.getBinaryStream();
		              BufferedInputStream input = new BufferedInputStream(is);	             
		              byte[] buff = new byte[(int) inblob.length()];//放到一个buff 字节数组
		              while(-1 != (input.read(buff, 0, buff.length)));	             
		              ObjectInputStream in =new ObjectInputStream(new ByteArrayInputStream(buff));
		              Ppub = (Element)in.readObject();//从IO流中读取出来.可以得到一个对象了
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
			return Ppub;		
	}


	public Element getsFromDB() {
		Connection conn=null;
	    PreparedStatement pstmt=null;
	    ResultSet rs=null;
	    Element s= null;
	    conn=DataBase.getConn();
	    String sql="select s from admin where name=? ";
	    try {	
			pstmt=conn.prepareStatement(sql);
		    pstmt.setString(1, "admin");
			rs=pstmt.executeQuery();
		    if(rs.next()){
				  Blob inblob = (Blob) rs.getBlob("s");
	              InputStream is = inblob.getBinaryStream();
	              BufferedInputStream input = new BufferedInputStream(is);	             
	              byte[] buff = new byte[(int) inblob.length()];//放到一个buff 字节数组
	              while(-1 != (input.read(buff, 0, buff.length)));	             
	              ObjectInputStream in =new ObjectInputStream(new ByteArrayInputStream(buff));
	              s = (Element)in.readObject();//从IO流中读取出来.可以得到一个对象了
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
		return s;		
	}


	public Administrator adminLogin(String adminname) {
		
		Connection conn=null;
	    PreparedStatement pstmt=null;
	    ResultSet rs=null;
	    Administrator administrator=null;
	    conn=DataBase.getConn();
	    String sql="select *from admin where name=? ";
	    try {	
			pstmt=conn.prepareStatement(sql);
		    pstmt.setString(1, adminname);
			rs=pstmt.executeQuery();
		    if(rs.next()){
		    	//int uid = rs.getInt("uid");
				String name = rs.getString("name");
				String pw = rs.getString("password");
				//int balance = rs.getInt("balance");
				administrator = new Administrator( name, pw);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(rs, pstmt, null);
		}
		return administrator;		
	}



	
}
