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
 * Servlet implementation class ScanCodeReading
 */
@WebServlet("/ScanCodeReading")
public class ScanCodeReading extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScanCodeReading() {
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
        String ISBN=request.getParameter("ISBN");
        String face,title,author,isbn,publish;
        int leftnum,bookId;
        String sql="select * from Book where ISBN='"+ISBN+"'";
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
                isbn=re.getString("isbn");
                publish=re.getString("publish");
                Book book=new Book();
                book.setFace(face);
                book.setAuthor(author);
                book.setTitle(title);
                book.setBookId(bookId);
                book.setLeftnum(leftnum);
                book.setISBN(isbn);
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
		doPost(request,response);
	}

}
