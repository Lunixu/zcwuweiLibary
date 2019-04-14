package wx.wuwei.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import wx.wuwei.po.Book;
import wx.wuwei.sqlhelper.SqlHelper;

/**
 * Servlet implementation class BookSearch
 */
@WebServlet("/BookSearch")
public class BookSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookSearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		String bookId = request.getParameter("bookid");
		System.out.println("8888888888+ "+bookId);
		String SQL="select * from Book where bookId="+bookId;
		ResultSet re=SqlHelper.executeQuery(SQL);
		ArrayList<Book> bookslist = new ArrayList<Book>();
        try
        {
			 while (re.next()) 
			 {
				 Book bookinfo = new Book();
				 bookinfo.setFace(re.getString("face"));
				 bookinfo.setTitle(re.getString("title"));
				 bookinfo.setAuthor(re.getString("author"));
				 bookinfo.setBookId(Integer.parseInt(re.getString("bookId")));
				 bookinfo.setLeftnum(Integer.parseInt(re.getString("leftnum")));
				 bookinfo.setISBN(re.getString("isbn"));
				 bookinfo.setPublish(re.getString("publish"));
				 
				 System.out.print(Integer.parseInt(re.getString("leftnum")));
				 
				 bookslist.add(bookinfo);
				 
             }
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
        request.setAttribute("bookslist",bookslist);
        request.getRequestDispatcher("showbooks.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		String search = request.getParameter("search");
		System.out.println("搜索内容："+search);
		response.setCharacterEncoding("utf-8");
		String libaryId;//图书馆id
		
		HttpSession session = request.getSession();
		if(session.getAttribute("libaryId")==null)
		{
			libaryId = request.getParameter("libaryId");
		}
		else
		{
			libaryId = (String)session.getAttribute("libaryId");
		}
		
		Cookie[] cookies = request.getCookies();// 获取一个cookie数组
		//Cookie cookie = myCookie.Values["SearchHistory"];
		if(cookies==null)
		{   
			
			Cookie SearchHistory = new Cookie("SearchHistory", URLEncoder.encode(search, "UTF-8"));
			SearchHistory.setMaxAge(60*60*24*7);   //- 单位为秒，7天有效
			response.addCookie(SearchHistory);
		}
		else
		{
			
			boolean flag1 = true;//是否存在该cookie
			boolean flag2 = true;//cookie中是否已有该记录
			for (Cookie cookie : cookies)
			{   
	
			    if (cookie.getName().equals("SearchHistory")) 
				{   
			    	String[] a =URLDecoder.decode(cookie.getValue(),"utf-8").split(";");
			    	String newcookie="";
			        for(int i=0;i<a.length;i++)
			        {  
			        	System.out.println(search);
			        	System.out.println(a[i]);
			        	if(search.equals(a[i]))
			        	{   
			        		
			        		flag1 = false;
			        		flag2 = false;
			        		break;
			        	}
			        	if(i!=0)
			        	{
			        		newcookie=newcookie+URLEncoder.encode(a[i]+";","utf-8");
			        	}
			        	
			        }
			        if(flag2)
			        {
			        String c;
			        if(a.length>=10)
			        {
			        c=newcookie+URLEncoder.encode(search,"utf-8");
			        System.out.println("search00"+search);
			        System.out.println("search11"+c);
			        }
			        else
			        {
			    	 c= cookie.getValue() + URLEncoder.encode(";","utf-8")+ URLEncoder.encode(search,"utf-8");
			        }
			    	Cookie SearchHistory = new Cookie("SearchHistory", c);
					SearchHistory.setMaxAge(60*60*24*7);   //- 单位为秒，7天有效
					response.addCookie(SearchHistory);
					flag1 = false;
					break;
			        }
				} 	   
			}
			if(flag1) 
			{  
				
				Cookie SearchHistory = new Cookie("SearchHistory", URLEncoder.encode(search, "UTF-8"));
				SearchHistory.setMaxAge(60*60*24*7);   //- 单位为秒，7天有效
				response.addCookie(SearchHistory);
			}
		  }
		
		System.out.println("search"+search);
		 @SuppressWarnings("rawtypes")
		ArrayList bookslist = new ArrayList();
         Book books = new Book();
         bookslist = books.Search(search,libaryId);
         //request.setAttribute("bookslist",bookslist);
         
         request.getSession().setAttribute("bookslist", bookslist);
		 //request.getSession().setAttribute("search", search);
         System.out.println("跳转页面成功1111！！");
         //request.getRequestDispatcher("showbooks.jsp").forward(request, response);
         System.out.println("跳转页面成功2222！！");
	}

}
