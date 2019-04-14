package wx.wuwei.servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wx.wuwei.po.Book;
import wx.wuwei.sqlhelper.SqlHelper;

/**
 * Servlet implementation class InfoOfBooks
 */
@WebServlet("/InfoOfBooks")
public class InfoOfBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoOfBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		String isbn = request.getParameter("isbn");
		String SQL="select title, content from Book where isbn='"+isbn+"'";
		ResultSet rs=SqlHelper.executeQuery(SQL);
		Book book = new Book();
		try
        {
			 while (rs.next()) 
			 {
				book.setTitle(rs.getString("title"));//ÊéÃû
				book.setContent(rs.getString("content"));//ÄÚÈÝ¼ò½é
             }
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
        request.setAttribute("bookstitle",book.getTitle());
        request.setAttribute("bookscontent",book.getContent());
        request.getRequestDispatcher("InfoOfBooks.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
