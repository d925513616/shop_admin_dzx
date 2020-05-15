package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;



public class DBUtil {
	private DBUtil() {}
	private static String url = "jdbc:mysql://localhost:3306/dzx?characterEncoding=utf8&userSSL=false&serverTimezone=Asia/Shanghai";
	private static String user = "root";
	private static String password = "root";
	static  {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConn() {
		try {
			System.out.println("开始连接数据库");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("数据库连接成功");
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("连接失败");
			throw new RuntimeException(e);
		}
	}
	public static void close(Connection conn) {	
		if (conn != null) {
			try {
				conn.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void	close(ResultSet rs, Statement stm, Connection conn) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {	
				e.printStackTrace();
			}
		}
		if(stm != null) {
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	//万能更新
	public static int update(String sql,Object ... param) {
		int r = 0;
		Connection conn = null;
		PreparedStatement stm = null;
		try {
			conn = DBUtil.getConn();
			stm = conn.prepareStatement(sql);
			for(int i = 0; i < param.length; i++) {
				stm.setObject(i+1, param[i]);
			}
			r = stm.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.close(null,stm,conn);
		}
		return r;
	}
	//添加数据,返回生成的自增ID
		public static int addAndReturnId(String sql,Object ... params) {
			int autoId =0 ;
			Connection conn=null;
			PreparedStatement stm=null;
			try {
				conn=getConn();
				stm=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				for(int i=0;i<params.length;i++) {
					stm.setObject(i+1, params[i]);
				}
				stm.execute();
				ResultSet rskey=stm.getGeneratedKeys();
				rskey.next(); 
				autoId=rskey.getInt(1);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}finally {
				close(null,stm,conn);
			}
			return autoId;
		}
		//查询单个对象
		public static <T> T getSingleObject(String sql,Class<T> clas,  Object ...  params) {
			T result=null;
			QueryRunner qr=null;
			Connection conn=null;
			try {
				conn=getConn();
				qr=new QueryRunner();
				result=qr.query(conn,sql, new BeanHandler<T>(clas),params);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}finally {
				close(conn);
			}
			return result;		
		}
		//查询一组对象
		public static <T>  List<T> getList(String sql,Class<T> clas,  Object ...  params){
			List<T> list=new ArrayList<T>();
			Connection conn=null;
			try {
				conn=getConn();
				list=new QueryRunner().query(conn,sql, new BeanListHandler<T>(clas),params);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}finally {
				close(conn);
			}
			return list;
		}
		//返回Map集合
		public static Map<String,Object> getMap(String sql,Object ...params){
			Map<String,Object> m=new HashMap<String,Object>();
			Connection conn=null;
			try {
				conn=getConn();
				m=new QueryRunner().query(conn,sql, new MapHandler(), params);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}finally {
				close(conn);
			}
			return m;
		}
		//返回一个List集合,里面的每条数据都是一个Map集合
		public static List<Map<String,Object>> getMapList(String sql,Object ... params){
			List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>> ();
			Connection conn=null;
			try {
				conn=getConn();
				listMap=new QueryRunner().query(conn,sql, new MapListHandler(), params);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}finally {
				close(conn);
			}
			return listMap;
		}
		//返回单个数据 
		public static <T> T getScalar(String sql, Object ... params) {
			T result=null;
			
			Connection conn=null;
			try {
				conn=DBUtil.getConn();
				result=new QueryRunner().query(conn,sql,new ScalarHandler<T>(1), params);
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}finally {
				close(conn);
			}
			return result;	
		}
		
		public static <T> List<T> getScalarList(String sql, Object ... params) {
			List<T> list = new ArrayList<T>();
			Connection conn=null;
			try {
				conn=DBUtil.getConn();
				list=new QueryRunner().query(conn,sql,new ColumnListHandler<T>("menuid"), params);
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}finally {
				close(conn);
			}
			return list;	
		}
	

}
