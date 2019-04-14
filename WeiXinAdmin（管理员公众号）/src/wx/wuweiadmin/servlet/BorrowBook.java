package wx.wuweiadmin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wx.wuweiadmin.sqlhelper.SqlHelper;


/**
 * Servlet implementation class BorrowBook
 */
@WebServlet("/BorrowBook")
public class BorrowBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowBook() {
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
		String[] id = request.getParameter("id").split(";");
		String readerid = request.getParameter("readerid");
		String sql;
		Date now = new Date();
		String ordertime;
		String returntime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		ordertime=dateFormat.format(now);
		Calendar rightNow = Calendar.getInstance();
	     rightNow.setTime(now);
	     rightNow.add(Calendar.DAY_OF_YEAR,10);//日期加10天
	     now = rightNow.getTime();
	     returntime = dateFormat.format(now);
		for(String item:id)
		{  
			sql= "insert  bookborrow values ('"+readerid+"',"+item+ ",'"+ordertime+"','"+returntime+"') ; update book set leftnum=leftnum-1 where bookid="+item;
			System.out.print(sql);
			SqlHelper.executeUpdate(sql);
		}
		PrintWriter out = response.getWriter();
		out.println("借书成功！点击确定将关闭此页面！");
	}

}
