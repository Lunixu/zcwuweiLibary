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
 * Servlet implementation class bookDelete_servlet
 */
@WebServlet("/bookDelete_servlet")
public class bookDelete_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookDelete_servlet() {
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
		String id;
		int bookId;
		id=request.getParameter("bookId");
		bookId=Integer.parseInt(id);
		System.out.print(id);   
		Statement stmt;
		try
		{
			sql_connection s=new sql_connection();
			stmt = s.sqlConnection();
            String sql = "delete from Book where bookId="+bookId;
            stmt.executeUpdate(sql);
            request.getRequestDispatcher("book_list.jsp").forward(request,response);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
