package wx.wuwei.listener;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.json.JSONObject;

import wx.wuwei.po.AccessToken;
import wx.wuwei.po.TemplateData;
import wx.wuwei.po.WechatTemplate;
import wx.wuwei.sqlhelper.SqlHelper;
import wx.wuwei.util.CommonUtil;
import wx.wuwei.util.WeixinUtil;

public class MyTimerTask implements ServletContextListener
{ 
	private Timer timer = null;  
	public void contextDestroyed(ServletContextEvent event) 
	{  
		timer.cancel(); 
		event.getServletContext().log("定时器销毁");  
	}   
	public void contextInitialized(ServletContextEvent event) 
	{ 
		//在这里初始化监听器，在tomcat启动的时候监听器启动，可以在这里实现定时器功能 
		timer = new Timer(true); 
		event.getServletContext().log("定时器已启动");//添加日志，可在tomcat日志中查看到 
		//调用exportHistoryBean，0表示任务无延迟，5*1000表示每隔5秒执行任务，60*60*1000表示一个小时；
		timer.schedule(new SendEmail(event.getServletContext()),0,60*60*1000*24);  //一天执行一次
	}
	public class SendEmail extends TimerTask 
	{ 
		private ServletContext context = null; 
	
		public SendEmail(ServletContext context) 
		{ 
			this.context = context; 
		} 
	
		@Override 
		public void run() 
		{ 
			String sql = "select * from bookborrow ";
			ResultSet rs = SqlHelper.executeQuery(sql);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try
			{
				while(rs.next())
				{  
					String fromUserName= rs.getString("readerId");
					System.out.println(fromUserName);//用户id
					int bookid= rs.getInt("bookId");
					String returntime= rs.getString("returntime");
					Date now = dateFormat.parse(returntime);
					System.out.print(returntime);
					Date date = dateFormat.parse(rs.getString("odertime"));
					System.out.print(rs.getString("odertime"));
					long l=now.getTime()-date.getTime();
					long day=l/(24*60*60*1000);
					long hour=(l/(60*60*1000)-day*24);
					long min=((l/(60*1000))-day*24*60-hour*60);
					long s=(l/1000-day*24*60*60-hour*60*60-min*60);
					
					System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
					
					if(day<2)
					{
						String sql2="select title,author,isbn from book where bookId="+bookid;
						ResultSet rs2 = SqlHelper.executeQuery(sql2);	
						try
						{
							while(rs2.next())
							{
								String title= rs2.getString("title");
								
								String author= rs2.getString("author");
								String isbn= rs2.getString("isbn");
								
								AccessToken token = WeixinUtil.getAccessToken();
								String access_token = token.getToken();   
								String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
								WechatTemplate wechatTemplate = new WechatTemplate();  
								
								wechatTemplate.setTemplate_id("3Xrg4YHP3ApgYytuzJ9RTihNcb-nLwnu9NwdpwVbJyk");  
								wechatTemplate.setTouser(fromUserName);  
								//wechatTemplate.setUrl("http://weixin.qq.com/download");  
								  
								Map<String,TemplateData> m = new HashMap<String,TemplateData>();  
								TemplateData first = new TemplateData();  
								first.setColor("#000000");    
								first.setValue("您所借的图书即将超期，请尽快归还！");    
								m.put("first", first);  
								  
								TemplateData keyword1 = new TemplateData();    
								keyword1.setColor("#000000");    
								keyword1.setValue(title);    
								m.put("keyword1", keyword1);  
								  
								TemplateData keyword2 = new TemplateData();    
								keyword2.setColor("#000000");    
								keyword2.setValue(author);    
								m.put("keyword2", keyword2);  
								  
								TemplateData keyword3 = new TemplateData();    
								keyword3.setColor("#000000");    
								keyword3.setValue(isbn);    
								m.put("keyword3", keyword3);  
								  
								TemplateData keyword4 = new TemplateData();    
								keyword4.setColor("#000000");    
								keyword4.setValue(returntime);    
								m.put("keyword4", keyword4);  
								  
								TemplateData remark = new TemplateData();    
								remark.setColor("#000000");    
								remark.setValue("请尽快按时归还图书，若超期将产生相应损失，敬请谅解,谢谢！");    
								m.put("remark", remark);  
								wechatTemplate.setData(m); 
								
								String jsonString = JSONObject.fromObject(wechatTemplate).toString();
								JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", jsonString);
								System.out.println(jsonObject);
						        int result = 0;
						        if (null != jsonObject) 
						        {  
						             if (0 != jsonObject.getInt("errcode")) 
						             {  
						                 result = jsonObject.getInt("errcode");
						                 System.out.println( jsonObject.getInt("errcode")+ jsonObject.getString("errmsg"));  
						             }  
						             else
						             {
						            	 System.out.println("模板消息发送成功");
						             }
						            	 
						         }
						        System.out.println("模板消息发送结果："+result);
							}
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
						
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
	  }    
	}
}
