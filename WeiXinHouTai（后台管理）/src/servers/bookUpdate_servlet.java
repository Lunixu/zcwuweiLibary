package servers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Commen.sql_connection;
import Model.Book;

/**
 * Servlet implementation class bookUpdate_servlet
 */
@WebServlet("/bookUpdate_servlet")
public class bookUpdate_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookUpdate_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		Statement stmt;
		ResultSet rs;
		String id=null,ISBN=null,title=null,pingying=null,author=null,publish=null,version=null,face=null,preface=null,directory=null;
		String content=null,comment=null,introduction=null,category=null,firstChar=null;
		String num1,leftnum1,libaryId1;
		int num=0,leftnum=0,libaryId=0,bookId=0;
		id=request.getParameter("bookId");
		bookId=Integer.parseInt(id);
		try{
			sql_connection s=new sql_connection();
			stmt = s.sqlConnection();
			String sql = "select * from Book where bookId='"+bookId+"'";
			rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	bookId=rs.getInt(1);
				ISBN=rs.getString("ISBN");
				title=rs.getString("title");
				pingying=rs.getString("pingying");
				author=rs.getString("author");
				firstChar=rs.getString("firstChar");
				publish=rs.getString("publish");
				version=rs.getString("version");
				face=rs.getString("face");
				preface=rs.getString("preface");
				directory=rs.getString("directory");
				content=rs.getString("content");
				comment=rs.getString("comment");
				introduction=rs.getString("introduction");
				category=rs.getString("category");
				num1=rs.getString("num");
				num=Integer.parseInt(num1);
				leftnum1=rs.getString("leftnum");
				leftnum=Integer.parseInt(leftnum1);
				libaryId1=rs.getString("libaryId");
				libaryId=Integer.parseInt(libaryId1);
			}
			Book n=new Book(bookId,num,leftnum,libaryId,ISBN,title,pingying,author,publish,version,face,preface,directory,content,comment,introduction,category,firstChar);
			request.setAttribute("book", n);
			System.out.print(n);
			request.getRequestDispatcher("book_update.jsp").forward(request,response);
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
		request.setCharacterEncoding("utf-8"); 
		String ISBN,title,pingying,author,publish,version,face,preface,directory,content,comment,introduction,category,firstChar;
		String num,leftnum,libaryId,bookId;
		bookId=request.getParameter("bookId");
		ISBN=request.getParameter("ISBN");
		title=request.getParameter("title");
		pingying=request.getParameter("pingying");
		author=request.getParameter("author");
		firstChar=request.getParameter("firstChar");
		publish=request.getParameter("publish");
		version=request.getParameter("version");
		face=request.getParameter("face");
		preface=request.getParameter("preface");
		directory=request.getParameter("directory");
		content=request.getParameter("content");
		comment=request.getParameter("comment");
		introduction=request.getParameter("introduction");
		category=request.getParameter("category");
		num=request.getParameter("num");
		leftnum=request.getParameter("leftnum");
		libaryId=request.getParameter("libaryId"); 
		Statement stmt=null;
		try
		{
			sql_connection s=new sql_connection();
			stmt = s.sqlConnection();
            String sql = "update Book set ISBN='"+ISBN+"',title='"+title+"',pingying='"+pingying+"',author='"+author+"',firstChar='"+firstChar+"',publish='"+publish+"',version='"+version+"',face='"+face+"',preface='"+preface+"',directory='"+directory+"',content='"+content+"',comment='"+comment+"',introduction='"+introduction+"',category='"+category+"',num="+num+",leftnum="+leftnum+",libaryId="+libaryId+"where bookId="+bookId;
            System.out.print(sql);
            stmt.executeUpdate(sql);
            request.getRequestDispatcher("book_list.jsp").forward(request,response);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
