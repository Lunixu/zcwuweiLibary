package wx.wuweiadmin.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wx.wuweiadmin.po.Book;
import wx.wuweiadmin.sqlhelper.SqlHelper;



/**
 * Servlet implementation class ManageReturn
 */
@WebServlet("/ManageReturn")
public class ManageReturn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageReturn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubs
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		String[] id = request.getParameter("id").split(";");
		String readerid = request.getParameter("readerid");
		ResultSet rs;
		String sql;
		Book book = new Book();
		@SuppressWarnings("rawtypes")
		ArrayList bookslist = new ArrayList();
		for(String item:id)
		{
			sql="select * from book where bookid = '"+item+"'";
			rs= SqlHelper.executeQuery(sql);
			bookslist.add((Book)book.ResultSetToBook(rs).get(0));
		}
        request.setAttribute("comparebooks", bookslist);
        request.setAttribute("readerid", readerid);
        request.getRequestDispatcher("comparebooksreturn.jsp").forward(request, response);
	}

}
