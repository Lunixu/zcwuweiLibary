package wx.wuwei.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wx.wuwei.po.WeixinOauth2Token;
import wx.wuwei.service.ShowUserinfoService;
import wx.wuwei.sqlhelper.SqlHelper;
import wx.wuwei.util.AdvancedUtil;

/**
 * Servlet implementation class ReserveBook
 */
@WebServlet("/ReserveBook")
public class ReserveBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*request.setCharacterEncoding("utf-8");
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
					    System.out.println("999"+session.getAttribute("readerid"));
					}
				} 
                catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }     
        }  */
        doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String bookId = request.getParameter("bookid");//图书id
        String sql="select leftnum from book where bookId="+bookId;//查询余量
        ResultSet rs=SqlHelper.executeQuery(sql);
        int leftnum=0;
        try
        {
			 while (rs.next()) 
			 {
				 leftnum=rs.getInt("leftnum");//余量
             }
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
        HttpSession session = request.getSession(false);
        if(session.getAttribute("readerid")!=null)
		{
        	String readerid = (String)session.getAttribute("readerid");//得到用户id
        	Date now = new Date();
    		String orderTime;
    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    		orderTime=dateFormat.format(now);
    		
    		if(leftnum<=0)//没有余量时   不存预订时间
    		{
    			orderTime=null;
    			String SQL="insert  BookReserve values ('"+readerid+"','"+bookId+"',"+orderTime+",'"+true+"')";
    			if(SqlHelper.executeUpdate(SQL))
            	{
            		request.setAttribute("Info", "此书预约成功！当前库存量不足，当有库存量时会及时通知您取书！");
    	   			request.setAttribute("flag", "1");
    				request.getRequestDispatcher("WindowClose.jsp").forward(request, response);
            	}
    		}
    		else
    		{
	        	String SQL="insert  BookReserve values ('"+readerid+"','"+bookId+"','"+orderTime+"','"+true+"') ; update book set leftnum=leftnum-1 where bookid="+bookId;
	        	if(SqlHelper.executeUpdate(SQL))
	        	{
		   			request.setAttribute("Info", "恭喜！此书预约成功！请务必在今天21时之前领取您预订的图书！");
		   			request.setAttribute("flag", "1");
					request.getRequestDispatcher("WindowClose.jsp").forward(request, response);
	        	}
    		}
		}
		else
		{
		    request.setAttribute("Info", "预约失败！请绑定账号后重试！");
			request.getRequestDispatcher("WindowClose.jsp").forward(request, response);
		}
	}

}
