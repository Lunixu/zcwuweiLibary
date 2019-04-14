package wx.wuwei.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wx.wuwei.po.Book;
import wx.wuwei.sqlhelper.SqlHelper;

/**
 * Servlet implementation class MoreBook
 */
@WebServlet("/MoreBook")
public class MoreBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoreBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String face,title,author,publish;
        int leftnum,bookId;
		String categoryid=request.getParameter("categoryid");//类别ID
		String category=null;
		if(categoryid.equals("1"))
		{
			category="数学";
		}
		if(categoryid.equals("2"))
		{
			category="经济";
		}
		System.out.println("类别："+category);
		String sql="select * from Book where category='"+category+"'";
		ArrayList<Book> bookslist = new ArrayList<Book>();
		ResultSet re=SqlHelper.executeQuery(sql);
		try
		{
			 while (re.next()) 
			 {
                face=re.getString("face");
                author=re.getString("author");
                title=re.getString("title"); 
                leftnum=Integer.parseInt(re.getString("leftnum"));
                bookId=Integer.parseInt(re.getString("bookId"));
                publish=re.getString("publish");
                Book book=new Book();
                book.setFace(face);
                book.setAuthor(author);
                book.setTitle(title);
                book.setBookId(bookId);
                book.setLeftnum(leftnum);
                book.setPublish(publish);
                bookslist.add(book);
			 }
			 request.setAttribute("bookslist", bookslist);
			 request.getRequestDispatcher("showbooks.jsp").forward(request,response);
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
		doGet(request,response);
	}

}
