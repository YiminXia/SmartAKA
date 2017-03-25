package com.njust.SmartAKA.dao;
import java.sql.*;
public class DBUtils {
     public static void close(ResultSet rs,Statement stmt,Connection conn){
    	if(rs!=null){
    		try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	} 
    	 if(stmt!=null){
    		 try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	 }
    	 
    	 
    	 if(conn!=null){
    		 try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	 }
    	 
     }
     public static void close(ResultSet rs,Statement stmt){
    	 close(rs,stmt,null);
     }
     public static void close(Connection conn){
    	 close(null,null,conn);
     }
     public static void close(Statement stmt,Connection conn){
    	 close(null,stmt,conn);
     }
     
}
