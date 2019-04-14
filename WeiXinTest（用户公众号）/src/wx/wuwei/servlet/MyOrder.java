package wx.wuwei.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wx.wuwei.po.Order;
import wx.wuwei.po.WeixinOauth2Token;
import wx.wuwei.service.ShowUserinfoService;
import wx.wuwei.sqlhelper.SqlHelper;
import wx.wuwei.util.AdvancedUtil;

/**
 * Servlet implementation class MyOrder
 */
@WebServlet("/MyOrder")
public class MyOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String openId=null;
        // 用户同意授权后，能获取到code
        String code = request.getParameter("code");
        
        if (!"authdeny".equals(code)) 
        {
            // 获取网页授权access_token
            WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken("wx1e83e75abd38e250", "9455fcaa649f7528618dbbff146ad627", code);
            if(weixinOauth2Token!=null)
            {
            	 // 用户标识openId
                openId = weixinOauth2Token.getOpenId(); 
                try 
                {
					if(ShowUserinfoService.judgeUserInfo(openId))//若是绑定用户
					{
						HttpSession session = request.getSession();
					    session.setAttribute("readerid", openId);
					    System.out.println("333333"+session.getAttribute("readerid"));
					}
				} 
                catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }     
        }
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String readerid = (String)session.getAttribute("readerid");
		
		try 
		{
			if(ShowUserinfoService.judgeUserInfo(readerid))//绑定用户
			{
				String sql = "select isbn,title,face,odertime,returntime,book.bookid from book join bookBorrow on book.bookid = bookBorrow.bookId where readerid='"+readerid+"'";
				ResultSet rs = SqlHelper.executeQuery(sql);
				Order order = new Order();
				@SuppressWarnings("rawtypes")
				ArrayList orderlist = new ArrayList();
				try {
					while (rs.next()) {
						order.setISBN(rs.getString("ISBN"));
						order.setTitle(rs.getString("title"));
						order.setFace(rs.getString("face"));
						order.setOrdertime(rs.getString("odertime"));
						order.setReturntime(rs.getString("returntime"));
						order.setBookId(rs.getString("bookid"));
						System.out.print("ssssss"+rs.getString("bookid"));
						orderlist.add(order);
					}
				} catch (Exception e) {
					System.out.println(e);
				}
				request.setAttribute("myorder", orderlist);
				System.out.println("集合中为："+orderlist);
				request.setAttribute("readerid", readerid);
				if(orderlist.size()==0)
				{
					request.setAttribute("flag", "1");
					request.setAttribute("BindInfo", "您暂时没有借阅任何图书！");
					request.getRequestDispatcher("myorder.jsp").forward(request, response);
				}
				else
				{
					request.getRequestDispatcher("myorder.jsp").forward(request, response);
				}
			}
			else
			{
				request.setAttribute("flag", "1");
				request.setAttribute("BindInfo", "您的账户暂未绑定，请绑定后再进行此操作！");
				request.getRequestDispatcher("myorder.jsp").forward(request, response);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
