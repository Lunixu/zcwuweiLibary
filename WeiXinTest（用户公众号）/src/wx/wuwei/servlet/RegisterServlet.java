package wx.wuwei.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wx.wuwei.po.Reader;
import wx.wuwei.sqlhelper.SqlHelper;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        // 用户同意授权后，能获取到code
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String openId=null;
        
        // 用户同意授权
        if (!"authdeny".equals(code)) {
            // 获取网页授权access_token
            WeixinOauth2Token weixinOauth2Token = AdvancedUtil.g  etOauth2AccessToken("wx1e83e75abd38e250", "9455fcaa649f7528618dbbff146ad627", code);
            // 用户标识openId
            openId = weixinOauth2Token.getOpenId();
            // 设置要传递的参数 openId
            request.setAttribute("openId", openId);
            
            System.out.println(openId);
        }
        // 跳转到index.jsp
        request.getRequestDispatcher("login.jsp").forward(request, response);
        */
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        Reader reader=new Reader();
        String readerId=null,name=null,sex=null,brithday=null,education=null,isAccept=null,hobby="";
        String year=null,month=null,day=null;
		String[] hobbys;
        
		try
		{
			readerId = request.getParameter("readerId");//读者编号
			name = request.getParameter("name");//读者姓名
			sex = request.getParameter("sex");//读者性别
			education=request.getParameter("education");//读者学历 
			
			year = request.getParameter("year");
			month = request.getParameter("month");
			day = request.getParameter("day");
			brithday=year+"年"+month+"月"+day+"日";//读者生日
			
			if(request.getParameter("isAccept")==null)//协议
			{
				isAccept="false";
			}
			else
			{
				isAccept = request.getParameter("isAccept");
			}
			
			//用来获取多个复选按钮的值
			if(request.getParameterValues("hobby")==null)
			{
				hobby="无";
			}
			else
			{
				hobbys = request.getParameterValues("hobby");//读者爱好 数组
				for(int i=0;i<hobbys.length;i++)
	            {    
					if(i==0)
					{
						hobby=hobbys[0];
					}
					if(i!=0)
					{
						hobby=hobby+" "+hobbys[i];
					}
	            }
			}
			
			reader.setReaderId(readerId);
			reader.setName(name);
			reader.setSex(sex);
			reader.setBrithday(brithday);
			reader.setHobby(hobby);
			reader.setEducation(education);
			if(isAccept.equals("true"))
			{
				reader.setFlag(true);
			}
			else
			{
				reader.setFlag(false);
			}
			//写入到数据库中
			String SQL = "insert into reader values('"+reader.getReaderId()+"', '"+reader.getName()+"', '"+reader.getSex()+"', '"+reader.getBrithday()+"', '"+reader.getEducation()+"','"+reader.getHobby()+"')";
			boolean result=SqlHelper.executeUpdate(SQL);
			if(result)
			{
				PrintWriter out = response.getWriter();
				out.println("绑定成功！！");
				System.out.println("绑定成功");
			}
			else
			{
				PrintWriter out = response.getWriter();
				out.println("绑定失败！！");
				System.out.println("绑定失败");
			}
			
			
			//打印
			System.out.println(reader.getBrithday());
			System.out.println(reader.getEducation());
			System.out.println(reader.getName());
			System.out.println(reader.getReaderId());
			System.out.println(reader.getSex());
			System.out.println(reader.getHobby());
			System.out.println(reader.isFlag());		
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
