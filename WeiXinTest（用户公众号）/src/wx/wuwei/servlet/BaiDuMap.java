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

import wx.wuwei.po.Libary;
import wx.wuwei.po.WeixinOauth2Token;
import wx.wuwei.service.ShowUserinfoService;
import wx.wuwei.sqlhelper.SqlHelper;
import wx.wuwei.util.AdvancedUtil;



/**
 * Servlet implementation class BaiDuMap
 */
@WebServlet("/BaiDuMap")
public class BaiDuMap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BaiDuMap() {
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
					    System.out.println("22222"+session.getAttribute("readerid"));
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		ArrayList<Libary> pal = new ArrayList<Libary>();
		String name=null,address=null,description=null,gpsx=null,gpsy=null;
		int libaryId=0;
		String sql="select * from Library";
		ResultSet re=SqlHelper.executeQuery(sql);
		try{
			 while (re.next()) {
                 libaryId=re.getInt("libaryId");
                 name=re.getString("name");
                 address=re.getString("address");
                 description=re.getString("description");
                 gpsx=re.getString("gpsx");
                 gpsy=re.getString("gpsy");
                 Libary l=new Libary(libaryId,name,address,description,gpsx,gpsy);
                 pal.add(l);
                 System.out.println("/"+name);
           }
			 request.setAttribute("libaryMap", pal);
			 request.getRequestDispatcher("map.jsp").forward(request,response);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
