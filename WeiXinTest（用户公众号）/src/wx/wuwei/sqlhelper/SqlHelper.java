package wx.wuwei.sqlhelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class SqlHelper {
	private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String dbURL = "jdbc:sqlserver://115.159.201.120:1433;DatabaseName=WeiXin";
	private static String userName = "sa";
	private static String userPwd = "@@siqian2017";
	  
	  
	public static Connection getConnection(){
		  try{
		   Class.forName( driverName );
		   Connection conn = DriverManager.getConnection( dbURL,userName,userPwd );
		   System.out.println("---------------连接数据库成功");
		   return conn;
		  }
		  catch( Exception e ){
		   e.printStackTrace();
		   System.out.println("----------------连接失败");
		  } 
		  
		  return null;
	  }
	  
	public static ResultSet executeQuery( String SQL ){  
		  try{
		   Connection conn=getConnection();
		   
		   Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		   ResultSet rs = stmt.executeQuery(SQL);
		   
		   return  rs;
		  }
		  catch( Exception e ){
		   e.printStackTrace();
		   System.out.println("----------------查询失败");
		  }
		  
		  return null;
	}
	
	public static boolean executeUpdate( String SQL ){  
		  try{
		   Connection conn=getConnection();
		  
		   Statement stmt = conn.createStatement();
		   int result = stmt.executeUpdate( SQL );
		   
		   if( result > 0 )
			   return true;
		  }
		  catch( Exception e ){
		   e.printStackTrace();
		   System.out.println("----------------更新失败");
		  }
		  
		  return false;
	}
}
