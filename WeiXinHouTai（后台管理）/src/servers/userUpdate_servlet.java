package servers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Commen.sql_connection;
import Model.users;

/**
 * Servlet implementation class userUpdate_servlet
 */
@WebServlet("/userUpdate_servlet")
public class userUpdate_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userUpdate_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		Statement stmt;
		ResultSet rs;
		String adminId=null,adminUsername=null,adminPassword=null;
		adminId=request.getParameter("adminId");
		try{
			sql_connection s=new sql_connection();
			stmt = s.sqlConnection();
			String sql = "select * from administrator where adminId='"+adminId+"'";
			rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	adminId=rs.getString("adminId");
            	adminUsername=rs.getString("adminUsername");
            	adminPassword=rs.getString("adminPassword");
			}
            users u = new users(adminId,adminUsername,adminPassword);
            request.setAttribute("users", u);
			request.getRequestDispatcher("user_update.jsp").forward(request,response);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8"); 
		String adminId,adminUsername,adminPassword;
		adminId=request.getParameter("adminId");
		adminUsername=request.getParameter("adminUsername");
		adminPassword=request.getParameter("adminpassword");
		Statement stmt=null;
		try
		{
			sql_connection s=new sql_connection();
			stmt = s.sqlConnection();
            String sql = "update administrator set adminUsername='"+adminUsername+"',adminPassword='"+adminPassword+"' where adminId='"+adminId+"'";
            System.out.print(sql);
            stmt.executeUpdate(sql);
            request.getRequestDispatcher("user_list.jsp").forward(request,response);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
