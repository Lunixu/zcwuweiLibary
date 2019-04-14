package wx.wuwei.listener;

import java.sql.ResultSet;
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

public class BookReservation implements ServletContextListener
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
	
		public int flag=0;//标志
		
		@Override 
		public void run() 
		{ 
				String sql = "select * from BookReserve ";
				ResultSet rs = SqlHelper.executeQuery(sql);
				try
				{
					while(rs.next())
					{  
						String fromUserName= rs.getString("readerId");
						System.out.println(fromUserName);//用户id
						int bookid= rs.getInt("bookId");
						
						String orderTime= rs.getString("orderTime");
						if(orderTime==null)//没有图书存量时，预订后没有时间
						{
							String Sql1="select leftnum,title,author,isbn from book where bookid="+bookid;
							ResultSet rs1 = SqlHelper.executeQuery(Sql1);	
							try
							{
								while(rs1.next())
								{
									int leftnum = rs1.getInt("leftnum");
									String title= rs1.getString("title");	
									String author= rs1.getString("author");
									String isbn= rs1.getString("isbn");
									
									if(leftnum>0)
									{
										AccessToken token = WeixinUtil.getAccessToken();
										String access_token = token.getToken();   
										String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
										WechatTemplate wechatTemplate = new WechatTemplate();  
										
										wechatTemplate.setTemplate_id("fnwE-KDs79twfBkjp1tiTBCavHyrpoA6RecJvtbFE1M");  
										wechatTemplate.setTouser(fromUserName);  
										//wechatTemplate.setUrl("http://weixin.qq.com/download");  
										  
										Map<String,TemplateData> m = new HashMap<String,TemplateData>();  
										TemplateData first = new TemplateData();  
										first.setColor("#000000");    
										first.setValue("您所预订的图书已有库存：");    
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
										keyword4.setValue(leftnum+"");    
										m.put("keyword4", keyword4);  
										  
										TemplateData remark = new TemplateData();    
										remark.setColor("#000000");    
										remark.setValue("请尽快领取，谢谢合作！");    
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
