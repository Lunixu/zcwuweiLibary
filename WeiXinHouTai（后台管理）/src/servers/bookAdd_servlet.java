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
 * Servlet implementation class bookAdd_servlet
 */
@WebServlet("/bookAdd_servlet")
public class bookAdd_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookAdd_servlet() {
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
		String ISBN,title,pingying,author,publish,version,face,preface,directory,content,comment,introduction,category,firstChar;
		String num,leftnum,libaryId;
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
			String sql="insert into Book values('"+ISBN+"','"+title+"','"+pingying+"','"+firstChar+"','"+author+"',"+num+","+leftnum+",'"+publish+"','"+version+"','"+face+"','"+preface+"','"+directory+"','"+content+"','"+comment+"','"+introduction+"','"+category+"',"+libaryId+")";
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
