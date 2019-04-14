package wx.wuweiadmin.service;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import wx.wuweiadmin.sqlhelper.SqlHelper;
import wx.wuweiadmin.util.MessageUtil;

public class WeixinAdminService {
	/**
	 * 处理微信发来的请求
	 * @param request
	 * @return
	 */
	public static String processRequest (HttpServletRequest request) {
		
		// xml格式的消息数据
		String message = null;
		
		try 
        {
            Map<String,Object> map = MessageUtil.xmlToMap(request);
            String toUserName = map.get("ToUserName").toString();
            String fromUserName = map.get("FromUserName").toString();
            String msgType = map.get("MsgType").toString();
            String content=null;
            if(map.get("Content")!=null)
            {
            	content = map.get("Content").toString();   
            }
            

            if(MessageUtil.MESSAGE_TEXT.equals(msgType))//文本消息
            {    	
            	String mycontent = "你回复的消息是： " + content;
                message = MessageUtil.initText(toUserName, fromUserName, mycontent);
            }
            
            else if(MessageUtil.MESSAGE_EVENT.equals(msgType))//事件
            {
            	String eventType = map.get("Event").toString();//具体事件类型
            	
                if(MessageUtil.EVENT_SUB.equals(eventType))//关注  提示用户绑定
                {	
                	message=MessageUtil.AdminSUB(toUserName, fromUserName);
                }
                else if(MessageUtil.EVENT_UNSUB.equals(eventType))//取消关注  解除用户绑定
                {
                	//MessageUtil.AdminUNSUB(fromUserName);
                }
                else if(MessageUtil.EVENT_CLICK.equals(eventType))//点击推事件 菜单
                {
                  	String eventKey = map.get("EventKey").toString();//获取点击事件key值
                  	if (eventKey.equals("11")) //账号绑定    11菜单项被点击账号绑定
                    {  
                  		message=MessageUtil.BindAdmin(toUserName, fromUserName);
                    }
                }
                
                else if(ShowAdmininfoService.judgeUserInfo(fromUserName))//判段用户是否绑定(已绑定)
                {
                	if(MessageUtil.MESSAGE_SCANCODE.equals(eventType))//扫码带事件
                    {
                    	System.out.println("二维码map为:"+map);
                        	
                    	String eventKey = map.get("EventKey").toString();//得到按钮的key值
                    	System.out.println("二维码菜单key值为："+eventKey);
        					
                    	if(eventKey.equals("21"))//借书
                    	{
                    		String ScanCodeInfo= map.get("ScanCodeInfo").toString();
                    		String QRcodeInfo=ScanCodeInfo.substring(29,ScanCodeInfo.length()-1);
                    		System.out.println("借书二维码内容为："+QRcodeInfo);
                    		message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.AdminBorrowMessage(QRcodeInfo));
                    	}
                    	/*if(eventKey.equals("22"))//预约
                    	{
                    		String ScanCodeInfo= map.get("ScanCodeInfo").toString();
                    		String QRcodeInfo=ScanCodeInfo.substring(29,ScanCodeInfo.length()-1);
                    		System.out.println("预约二维码内容为："+QRcodeInfo);
                    		String SQL="select bookId from book where ISBN='"+QRcodeInfo+"'";
                    		ResultSet rs=SqlHelper.executeQuery(SQL);
                    		int bookid=0;
                    		try
                            {
                    			 while (rs.next()) 
                    			 {
                    				bookid=rs.getInt("bookid");
                                 }
                    		}
                    		catch(Exception ex)
                    		{
                    			ex.printStackTrace();
                    		}
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
                    		String sql="delete from BookReserve from BookReserve join Book on Book.bookID=BookReserve.bookId where isbn='"+QRcodeInfo+"';insert into bookBorrow('"+fromUserName+"',"+bookid+",'"+ordertime+"','"+returntime+"')";
                    		System.out.print(sql);
                			SqlHelper.executeUpdate(sql);
                			message="预订借书成功!!";
                			message = MessageUtil.initText(toUserName, fromUserName, message);
                    	}*/
                    	if(eventKey.equals("31"))//还书
                    	{
                    		String ScanCodeInfo= map.get("ScanCodeInfo").toString();
                    		String QRcodeInfo=ScanCodeInfo.substring(29,ScanCodeInfo.length()-1);
                    		System.out.println("还书二维码内容为："+QRcodeInfo);
                    		message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.AdminReturnMessage(QRcodeInfo));
                    	}
                    }
                }
                else//未绑定
                {
                	message=MessageUtil.HintAdminBind(toUserName, fromUserName);//提示用户绑定才能用此功能
                }
           }         
            System.out.println(message);
        } 
        catch (Exception e) 
        {
            // TODO: handle exception
        	e.printStackTrace();
        } 
		return message;
	}
}
