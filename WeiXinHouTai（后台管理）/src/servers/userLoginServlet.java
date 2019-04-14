package servers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Commen.sql_connection;
import Model.users;

/**
 * Servlet implementation class userLoginServlet
 */
@WebServlet("/userLoginServlet")
public class userLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userLoginServlet() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Statement stmt;   
		ResultSet rs;
		String adminId=null,adminUsername=null,adminpassword=null;
		ArrayList<users> pal = new ArrayList<users>();
		try{
			adminUsername=request.getParameter("uid");
			adminpassword=request.getParameter("upwd");
			sql_connection s=new sql_connection();
			stmt = s.sqlConnection();
            String sql = "select * from administrator where adminUsername='"+adminUsername+"' and adminpassword='"+adminpassword+"'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                adminId=rs.getString("adminId");
                adminUsername=rs.getString("adminUsername");
                adminpassword=rs.getString("adminpassword");
                users u=new users();
                u.setAdminId(adminId);
                u.setAdminpassword(adminpassword);
                u.setAdminUsername(adminUsername);
                pal.add(u);
            }
            if(pal.size()==0)
            {
            	PrintWriter out = response.getWriter();
        		out.println("<script type=\"text/javascript\">alert(\"用户不存在 \");window.opener = null;window.close();</script>");
   			 	out.close();
            }
            else{
	            request.setAttribute("users", pal);
	            request.getRequestDispatcher("mian.jsp").forward(request,response);
            }
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

}
