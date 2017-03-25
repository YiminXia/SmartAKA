package com.njust.SmartAKA.dao;


import java.sql.*;


public class DataBase {
	
	private static Connection conn;
	/**
	 * 静态代码块
	 * 静态的方法和属性是优先于对象而存在的
	 * 静态代码块只会在类加载的时候运行一次
	 * 静态的成员：在类加载的时候，都是只会加载一次
	 * 单例模式，
	 */
	static{
		//加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//连接数据库
			conn = DriverManager.getConnection("jdbc:MySql:///test","root","Huawei123");
			//System.out.println(conn);//检测数据库有没有连接成功
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取连接
	 * @return
	 */
	public static Connection getConn(){
		return conn;
	}
	
	/*
	public static final String Driver = "com.mysql.jdbc.Driver";  
    public static final String url = "jdbc:mysql://localhost:3306/test";  
    public static final String user = "root";  
    public static final String password = "Huawei123";  
  
    static {  
        try {  
            Class.forName(Driver);  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        }  
    }  
    public static Connection getConnection() throws SQLException{  
        Connection conn = null;  
        conn = DriverManager.getConnection(url,user,password);  
        return conn;  
    }  
    */
}
