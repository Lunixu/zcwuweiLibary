package wx.wuwei.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wx.wuwei.service.createQRCodeService;

/**
 * Servlet implementation class QRcode
 */
@WebServlet("/QRcode")
public class QRcode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QRcode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		if(session.getAttribute("readerid")!=null)
		{
	    String id = request.getParameter("id");
	    
	    String readerid = (String)session.getAttribute("readerid");
	    
	    String imgpath;
		createQRCodeService QRcode = new createQRCodeService();
		try
		{
		
			imgpath=QRcode.createBorrowQRCodeURL("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1e83e75abd38e250&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinTest%2FManagerScan%3Fid%3D"+id+"%26readerid%3D"+readerid+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
		 	session.setAttribute("imgpath", imgpath);
		 	
		 	session.removeAttribute("borrowbooks");
		 	session.removeAttribute("borrowbooksid");
		}
		
		catch(Exception e)
		{
			System.out.print(e);
		}
		}
		else
		{
			//response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1e83e75abd38e250&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinTest%2Flogin.jsp&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
			request.setAttribute("Info", "非绑定用户无法用此功能!");
			request.getRequestDispatcher("WindowClose.jsp").forward(request, response);
		}
		
	}

}
