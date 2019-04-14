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
 * Servlet implementation class ManageScan
 */
@WebServlet("/ManagerScan")
public class ManageBorrow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageBorrow() {
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
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		System.out.print(request.getParameter("id"));
		String[] id = request.getParameter("id").split(";");
		String readerid = request.getParameter("readerid");
		System.out.println(readerid);
		ResultSet rs;
		String sql="";
		Book book = new Book();
		@SuppressWarnings("rawtypes")
		ArrayList bookslist = new ArrayList();
		for(int i=0;i<id.length;i++)
		{   
			if(i<id.length-1)
			{
			sql+=" select * from book where bookid = '"+id[i]+"' UNION ALL ";
			}
			else
			{
			sql+=" select * from book where bookid = '"+id[i]+"' ";
			}
		}
		System.out.println("查询："+sql);
		rs= SqlHelper.executeQuery(sql);
		bookslist = book.ResultSetToBook(rs);
        request.setAttribute("comparebooks", bookslist);
        request.setAttribute("readerid", readerid);
        request.getRequestDispatcher("comparebooks.jsp").forward(request, response);
	}

}
