package wx.wuweiadmin.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wx.wuweiadmin.service.ShowAdmininfoService;
import wx.wuweiadmin.sqlhelper.SqlHelper;


/**
 * Servlet implementation class DeleteAdmin
 */
@WebServlet("/DeleteAdmin")
public class DeleteAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String adminId=request.getParameter("adminId");//得管理员id
        try 
        {
			if(ShowAdmininfoService.judgeUserInfo(adminId))
			{
				String SQL="delete from administrator where adminId='"+adminId+"'";
				if(SqlHelper.executeUpdate(SQL))
				{  
					request.setAttribute("Info", "解除管理员绑定成功！");
					request.getRequestDispatcher("WindowClose.jsp").forward(request, response);
					System.out.println("解除管理员绑定成功！");
				}
				else
				{
					request.setAttribute("Info", "解除管理员绑定失败！");
					request.getRequestDispatcher("WindowClose.jsp").forward(request, response);
					System.out.println("解除管理员绑定失败！");
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
