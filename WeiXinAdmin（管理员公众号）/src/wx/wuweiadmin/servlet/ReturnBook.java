package wx.wuweiadmin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wx.wuweiadmin.sqlhelper.SqlHelper;



/**
 * Servlet implementation class ReturnBook
 */
@WebServlet("/ReturnBook")
public class ReturnBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		String[] id = request.getParameter("id").split(";");
		String readerid = request.getParameter("readerid");
		String sql;
		for(String item:id)
		{  
			sql= "delete from bookborrow where readerid='"+readerid+"' and bookid='"+item+"' ;update book set leftnum=leftnum+1 where bookid="+item;
			System.out.print(sql);
			SqlHelper.executeUpdate(sql);
		}
		PrintWriter out = response.getWriter();
		out.println("还书成功！点击确定将关闭此页面！");
	}

}
