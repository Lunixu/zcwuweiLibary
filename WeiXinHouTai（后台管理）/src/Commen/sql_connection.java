package Commen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sql_connection {
	Connection conn = null;   
	Statement stmt;
	ResultSet rs;
	public Statement sqlConnection(){
		try
		{
			try 
			{  
	            // 加载驱动  
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");    
				conn = DriverManager.getConnection("jdbc:sqlserver://115.159.201.120:1433; DatabaseName=WeiXin", "sa", "@@siqian2017");
				System.out.print("数据库加载成功");
			}
			catch(SQLException e)
			{  
	            e.printStackTrace();  
	            System.out.print("数据库加载失败");  
	        }
			stmt = conn.createStatement();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return stmt;
	}
}
