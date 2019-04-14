package wx.wuweiadmin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wx.wuweiadmin.service.WeixinAdminService;
import wx.wuweiadmin.util.CheckUtil;

/**
 * Servlet implementation class WeixinAdminServlet
 */
@WebServlet("/WeixinAdminServlet")
public class WeixinAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WeixinAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * 微信管理员接入验证
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳 
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		
		PrintWriter out = response.getWriter();
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if(CheckUtil.checkSignature(signature, timestamp, nonce))
		{
			out.print(echostr);
		}
		out.close();
		out = null;
	}

	/**
	 * 消息的接收与响应，处理微信服务器发来的消息
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 消息的接收、处理、响应
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
		
        // 调用核心业务类接收消息、处理消息
        String respXmlMessage = WeixinAdminService.processRequest(request);

        // 响应消息
        out.print(respXmlMessage);
        out.close();
	}

}
