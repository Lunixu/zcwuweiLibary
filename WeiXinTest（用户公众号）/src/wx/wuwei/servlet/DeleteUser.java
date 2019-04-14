package wx.wuwei.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wx.wuwei.service.ShowUserinfoService;
import wx.wuwei.sqlhelper.SqlHelper;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        
        String readerId=request.getParameter("readerId");//得用户id
        try 
        {
			if(ShowUserinfoService.judgeUserInfo(readerId))
			{
				String SQL="delete from reader where readerId='"+readerId+"'";
				if(SqlHelper.executeUpdate(SQL))
				{  
					session.removeAttribute("readerid");
					request.setAttribute("Info", "解除用户绑定成功！");
					request.getRequestDispatcher("WindowClose.jsp").forward(request, response);
					System.out.println("解除用户绑定成功！");
				}
				else
				{
					request.setAttribute("Info", "解除用户绑定失败！");
					request.getRequestDispatcher("WindowClose.jsp").forward(request, response);
					System.out.println("解除用户绑定失败！");
				}
			}
			else
			{
				System.out.println("不存在此用户！！");
			}
		} 
        catch (SQLException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

}
