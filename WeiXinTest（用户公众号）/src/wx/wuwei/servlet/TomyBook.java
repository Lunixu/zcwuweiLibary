package wx.wuwei.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wx.wuwei.po.Book;
import wx.wuwei.po.WeixinOauth2Token;
import wx.wuwei.service.ShowUserinfoService;
import wx.wuwei.util.AdvancedUtil;

/**
 * Servlet implementation class TomyBook
 */
@WebServlet("/TomyBook")
public class TomyBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TomyBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
					    System.out.println("77777"+session.getAttribute("readerid"));
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
		 HttpSession session = request.getSession(false);
		  if(session.getAttribute("readerid")!=null){
             int id = Integer.parseInt(request.getParameter("bookid")) ;
             System.out.print(id);
             String mid="";
			ArrayList bookslist = new ArrayList();
             Book book = new Book();
             book = book.SearchById(id);
             if(session.getAttribute("borrowbooks")==null)
             {
            	 bookslist.add(book);
            	 session.setAttribute("borrowbooks",  bookslist);
            	 mid = String.valueOf(book.getBookId());
            	 session.setAttribute("borrowbooksid",mid);
             }
             else
             {
             bookslist = (ArrayList)session.getAttribute("borrowbooks");
             bookslist.add(book);
             mid = (String)session.getAttribute("borrowbooksid")+";"+String.valueOf(book.getBookId());
             session.setAttribute("borrowbooksid", mid);
             }
             response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1e83e75abd38e250&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinTest%2Fshoppingcart.jsp&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
			  }else{

			   //response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1e83e75abd38e250&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinTest%2Flogin.jsp&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
				request.setAttribute("Info", "非绑定用户无法用此功能!");
				request.getRequestDispatcher("WindowClose.jsp").forward(request, response);
			  } 
	}

}
