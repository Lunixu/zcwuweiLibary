package servers;

import java.io.IOException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Commen.sql_connection;

/**
 * Servlet implementation class userDelete_servlet
 */
@WebServlet("/userDelete_servlet")
public class userDelete_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userDelete_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String adminId;
		adminId=request.getParameter("adminId");; 
		Statement stmt;
		try
		{
			sql_connection s=new sql_connection();
			stmt = s.sqlConnection();
            String sql = "delete from administrator where adminId='"+adminId+"'";
            stmt.executeUpdate(sql);
            request.getRequestDispatcher("user_list.jsp").forward(request,response);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
