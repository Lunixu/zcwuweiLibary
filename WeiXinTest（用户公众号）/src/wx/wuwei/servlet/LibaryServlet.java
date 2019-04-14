package wx.wuwei.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wx.wuwei.po.Book;
import wx.wuwei.sqlhelper.SqlHelper;

/**
 * Servlet implementation class LibaryServlet
 */
@WebServlet("/LibaryServlet")
public class LibaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibaryServlet() {
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
        
        String libaryId=request.getParameter("libaryId");//得到图书馆id
        
        HttpSession session = request.getSession();
	    session.setAttribute("libaryId", libaryId);
	    String libaryname="图书馆";
	    String sql0="select * from Library where libaryId="+libaryId;
	    ResultSet rs=SqlHelper.executeQuery(sql0);
	    try
	    {
	    	while (rs.next()) 
	    	{
	    		libaryname=rs.getString("name");
	    	}
	    }
	    catch(Exception ex){
			ex.printStackTrace();
		} 
        
        String face,author,title,category;
        int bookId;
		String sql="select * from Book where libaryId="+libaryId;
		ArrayList<Book> pal = new ArrayList<Book>();
		ArrayList<Book> pla = new ArrayList<Book>();
		ResultSet re=SqlHelper.executeQuery(sql);
		try{
			 while (re.next()) {
                face=re.getString("face");
                author=re.getString("author");
                title=re.getString("title");  
                category=re.getString("category");
                bookId=Integer.parseInt(re.getString("bookId"));
                System.out.print(category);
                if(category.equals("数学"))
                {
                	Book book=new Book();
                	book.setFace(face);
                	book.setAuthor(author);
                	book.setTitle(title);
                	book.setBookId(bookId);
                	pal.add(book);
                }
                if(category.equals("经济"))
                {
                	Book book1=new Book();
                	book1.setFace(face);
                	book1.setAuthor(author);
                	book1.setTitle(title);
                	book1.setBookId(bookId);
                	pla.add(book1);
                }
                	
          }
			 request.setAttribute("book_math", pal);
			 request.setAttribute("book_emconomy", pla);
			 request.setAttribute("libaryname", libaryname);
			 request.getRequestDispatcher("bookmain.jsp").forward(request,response);
		}
		catch(Exception ex){
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
