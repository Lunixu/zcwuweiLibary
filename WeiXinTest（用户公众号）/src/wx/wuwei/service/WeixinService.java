package wx.wuwei.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import wx.wuwei.util.MessageUtil;

/**
 * 核心服务类
 * @author ZX50j
 *
 */
public class WeixinService {
	/**
	 * 处理微信发来的请求
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		
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
                String eventType = map.get("Event").toString();//具体事件
                if(MessageUtil.EVENT_SUB.equals(eventType))//关注  提示用户绑定
                {	
                    message = MessageUtil.UserSUB(toUserName, fromUserName);
                }
                else if(MessageUtil.EVENT_UNSUB.equals(eventType))//取消关注 
                {
                	String mycontent = "谢谢您的关注！欢迎您给我们提出宝贵的意见！ ";
                    message = MessageUtil.initText(toUserName, fromUserName, mycontent);
                }
                
                else if(MessageUtil.EVENT_CLICK.equals(eventType))//点击推事件 菜单
                {
                	String eventKey = map.get("EventKey").toString();  //具体菜单    菜单的key值
                	if (eventKey.equals("11")) //账号绑定    11菜单项被点击
                	{  
                		message=MessageUtil.BindUser(toUserName, fromUserName);  //账号绑定              	                              				
                	}
                	else if(eventKey.equals("32"))//关于我们
                	{
                		message=MessageUtil.AboutUs(fromUserName, toUserName);
                	}
                	
                	else if(ShowUserinfoService.judgeUserInfo(fromUserName))//绑定用户
                	{
                		if(eventKey.equals("12"))//个人信息
                		{
                			message=MessageUtil.userInfo(fromUserName, toUserName);
                		}
                	}
                	else//非绑定用户则不能用此功能
                	{
                		message=MessageUtil.HintUserBind(toUserName, fromUserName);//提示绑定
                	}
				}
                
                else if(MessageUtil.EVENT_VIEW.equals(eventType))//跳转URL
                {             	
                	String url = map.get("EventKey").toString();
                	if(ShowUserinfoService.judgeUserInfo(fromUserName))//绑定用户
                	{
                		message = MessageUtil.initText(toUserName, fromUserName, url);
                	}
                	else
                	{
                		message=MessageUtil.HintUserBind(toUserName, fromUserName);//提示绑定
                	}

                }
                else if(MessageUtil.MESSAGE_SCANCODE.equals(eventType))//扫码带事件
                {
                	if(ShowUserinfoService.judgeUserInfo(fromUserName))//绑定用户
                	{
                		System.out.println("二维码map为:"+map);

                		String key = map.get("EventKey").toString();//得到按钮的key值
                		System.out.println("二维码菜单key值为："+key);

                		if(key.equals("31"))
                		{
                			String ScanCodeInfo= map.get("ScanCodeInfo").toString();
                			String QRcodeInfo=ScanCodeInfo.substring(29,ScanCodeInfo.length()-1);//得到图书的isbn
                			System.out.println("二维码内容为："+QRcodeInfo);
                			message=MessageUtil.ScanCodeReading(QRcodeInfo, toUserName, fromUserName);
                		}
                	}
                	else//非绑定用户
                	{
                		message=MessageUtil.HintUserBind(toUserName, fromUserName);//提示绑定
                	}
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
