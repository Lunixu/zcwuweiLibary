package wx.wuweiadmin.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import wx.wuweiadmin.po.News;
import wx.wuweiadmin.po.NewsMessage;
import wx.wuweiadmin.po.TextMessage;
import wx.wuweiadmin.service.ShowAdmininfoService;
import wx.wuweiadmin.sqlhelper.SqlHelper;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class MessageUtil {
	
	public static final String MESSAGE_TEXT = "text";//文本消息
    public static final String MESSAGE_NEWS = "news";//图文消息
    public static final String MESSAGE_LINK = "link";//链接消息
    public static final String MESSAGE_LOCATION = "location";//地理位置消息
    public static final String MESSAGE_EVENT = "event";//事件
    public static final String EVENT_SUB = "subscribe";//关注
    public static final String EVENT_UNSUB = "unsubscribe";//取关
    public static final String EVENT_CLICK = "CLICK";//点击推事件
    public static final String EVENT_VIEW = "VIEW";//跳转URL
    public static final String MESSAGE_SCANCODE= "scancode_waitmsg";//扫码带事件
    
    /**
     * xml转为map对象
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String, Object> xmlToMap(HttpServletRequest request) throws IOException, DocumentException{ 
    	SAXReader reader = new SAXReader();
        InputStream ins = request.getInputStream();
        Document doc = reader.read(ins);
        Map<String, Object> map = new HashMap<String, Object>(); 
        if(doc == null) 
            return map; 
        Element root = doc.getRootElement(); 
        for (@SuppressWarnings("rawtypes")
		Iterator iterator = root.elementIterator(); iterator.hasNext();) { 
            Element e = (Element) iterator.next(); 
            @SuppressWarnings("rawtypes")
			List list = e.elements(); 
            if(list.size() > 0){ 
                map.put(e.getName(), Dom2Map(e)); 
            }else 
                map.put(e.getName(), e.getText()); 
        } 
        return map; 
    } 
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map Dom2Map(Element e){ 
        Map map = new HashMap(); 
        List list = e.elements(); 
        if(list.size() > 0){ 
            for (int i = 0;i < list.size(); i++) { 
                Element iter = (Element) list.get(i); 
                List mapList = new ArrayList(); 
                 
                if(iter.elements().size() > 0){ 
                    Map m = Dom2Map(iter); 
                    if(map.get(iter.getName()) != null){ 
                        Object obj = map.get(iter.getName()); 
                        if(!obj.getClass().getName().equals("java.util.ArrayList")){ 
                            mapList = new ArrayList(); 
                            mapList.add(obj); 
                            mapList.add(m); 
                        } 
                        if(obj.getClass().getName().equals("java.util.ArrayList")){ 
                            mapList = (List) obj; 
                            mapList.add(m); 
                        } 
                        map.put(iter.getName(), mapList); 
                    }else 
                        map.put(iter.getName(), m); 
                } 
                else{ 
                    if(map.get(iter.getName()) != null){ 
                        Object obj = map.get(iter.getName()); 
                        if(!obj.getClass().getName().equals("java.util.ArrayList")){ 
                            mapList = new ArrayList(); 
                            mapList.add(obj); 
                            mapList.add(iter.getText()); 
                        } 
                        if(obj.getClass().getName().equals("java.util.ArrayList")){ 
                            mapList = (List) obj; 
                            mapList.add(iter.getText()); 
                        } 
                        map.put(iter.getName(), mapList); 
                    }else 
                        map.put(iter.getName(), iter.getText()); 
                } 
            } 
        }else 
            map.put(e.getName(), e.getText()); 
        return map; 
    } 
    
    /**
     * 扩展xstream使其支持CDATA
     */
    @SuppressWarnings("unused")
	private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

               
                public void startNode(String name, @SuppressWarnings("rawtypes") Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
    
    /**
     * 将文本消息对象转换为xml
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage){
        XStream xstream = new XStream();
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }
    
    /**
     * 图文消息转为XML
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage){
        XStream xstream = new XStream();
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new News().getClass());
        return xstream.toXML(newsMessage);
    } 
    
    /**
	  * 组装文本消息
	  * @param toUserName
	  * @param fromUserName
	  * @param content
	  * @return
	  */
		public static String initText(String toUserName, String fromUserName, String content){
	        TextMessage text = new TextMessage();
	        text.setFromUserName(toUserName);
	        text.setToUserName(fromUserName);
	        text.setMsgType(MessageUtil.MESSAGE_TEXT);
	        text.setCreateTime(new Date().getTime());
	        text.setContent(content);
	        return textMessageToXml(text);
	    }
		
		/**
	     * 管理员借书信息
	     * @param url
	     * @return
	     */
	    public static String AdminBorrowMessage(String url)
	    {
	    	String newurl=url.replace("wx1e83e75abd38e250", "wx96b86ea8fdffd49f").replace("http%3A%2F%2F123.206.205.38%2FWeiXinTest%2FManagerScan", "http%3A%2F%2F123.206.205.38%2FWeiXinAdmin%2FManagerScan");
	    	StringBuffer sb = new StringBuffer();
	        sb.append("您好！\n\n");
	        sb.append("详细借书信息，请点击：");
	        sb.append("<a href='"+newurl+"'>详细借书信息</a>\n\n");
	        sb.append("谢谢合作！！\n");
	        return sb.toString();  
	    }
	    /**
	     * 管理员还书信息
	     * @param url
	     * @return
	     */
	    public static String AdminReturnMessage(String url)
	    {
	    	String newurl=url.replace("wx1e83e75abd38e250", "wx96b86ea8fdffd49f").replace("http%3A%2F%2F123.206.205.38%2FWeiXinTest%2FManagerScan", "http%3A%2F%2F123.206.205.38%2FWeiXinAdmin%2FManageReturn");
	    	StringBuffer sb = new StringBuffer();
	        sb.append("您好！\n\n");
	        sb.append("详细还书信息，请点击：");
	        sb.append("<a href='"+newurl+"'>详细还书信息</a>\n\n");
	        sb.append("谢谢合作！！\n");
	        return sb.toString();  
	    }
	    
	    /**
	     * 绑定用户
	     * @param toUserName
	     * @param fromUserName
	     * @return
	     * @throws SQLException
	     */
	    public static String BindAdmin(String toUserName,String fromUserName) throws SQLException
	    {
	    	String message = null;
	    	if(!ShowAdmininfoService.judgeUserInfo(fromUserName))
			{
				//图文消息  未绑定
	    		List<News> newsList = new ArrayList<News>();
	    		NewsMessage newsMessage = new NewsMessage();
	    		
	    		News news = new News();
	    		news.setTitle("绑定信息");
	    		news.setDescription("绑定管理员微信与公众号");
	    		news.setPicUrl("http://123.206.205.38/WeiXinAdmin/images/timg.jpg");  
	    		
	    		News news2 = new News();
	    		news2.setTitle("您的账号暂未绑定，请点击这里进行账号绑定！");
	    		news2.setDescription("账号绑定");
	    		news2.setPicUrl("http://123.206.205.38/WeiXinAdmin/images/timg.jpg");
	    		news2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx96b86ea8fdffd49f&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinAdmin%2Fadminlogin.jsp%3FadminId%3D"+fromUserName+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
	    		
	    		newsList.add(news);
	    		newsList.add(news2);
	    		
	    		newsMessage.setToUserName(fromUserName);
	    		newsMessage.setFromUserName(toUserName);
	    		newsMessage.setCreateTime(new Date().getTime());
	    		newsMessage.setMsgType("news");
	    		newsMessage.setArticles(newsList);
	    		newsMessage.setArticleCount(newsList.size());
	    		message = MessageUtil.newsMessageToXml(newsMessage);
			}
			else
			{
				//图文消息 绑定
	    		List<News> newsList = new ArrayList<News>();
	    		NewsMessage newsMessage = new NewsMessage();
	    		
	    		News news = new News();
	    		news.setTitle("绑定信息");
	    		news.setDescription("解除绑定管理员微信与公众号");
	    		news.setPicUrl("http://123.206.205.38/WeiXinAdmin/images/timg.jpg");                   		 
	    		
	    		News news2 = new News();
	    		news2.setTitle("您的账号已绑定,点击这里将解除绑定！");
	    		news2.setDescription("账号解除绑定");
	    		news2.setPicUrl("http://123.206.205.38/WeiXinAdmin/images/timg.jpg");
	    		news2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx96b86ea8fdffd49f&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinAdmin%2FDeleteAdmin%3FadminId%3D"+fromUserName+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
	    		
	    		newsList.add(news);
	    		newsList.add(news2);
	    		
	    		newsMessage.setToUserName(fromUserName);
	    		newsMessage.setFromUserName(toUserName);
	    		newsMessage.setCreateTime(new Date().getTime());
	    		newsMessage.setMsgType("news");
	    		newsMessage.setArticles(newsList);
	    		newsMessage.setArticleCount(newsList.size());
	    		message = MessageUtil.newsMessageToXml(newsMessage);
			}
	    	return message;
	    }
	    
	    /**
	     * 关注  提示用户绑定
	     * @param toUserName
	     * @param fromUserName
	     * @return
	     * @throws SQLException 
	     */
	    public static String AdminSUB(String toUserName,String fromUserName) throws SQLException
	    {
	    	String message = null;
	    	if(!ShowAdmininfoService.judgeUserInfo(fromUserName))
	    	{
	    		//图文消息 提示用户绑定
	    		List<News> newsList = new ArrayList<News>();
	    		NewsMessage newsMessage = new NewsMessage();
	    		
	    		News news1 = new News();
	    		news1.setTitle("感谢关注无微不至的借阅伴侣管理员端！");
	    		news1.setDescription("感谢关注公众号");
	    		news1.setPicUrl("http://123.206.205.38/WeiXinAdmin/images/timg.jpg");  
	    		
	    		News news2 = new News();
	    		news2.setTitle("您的微信号暂未绑定，请点击用户->账户绑定 进行账号绑定！");
	    		news2.setDescription("账号绑定");
	    		
	    		newsList.add(news1);
	    		newsList.add(news2);
	    	
	    		newsMessage.setToUserName(fromUserName);
	    		newsMessage.setFromUserName(toUserName);
	    		newsMessage.setCreateTime(new Date().getTime());
	    		newsMessage.setMsgType("news");
	    		newsMessage.setArticles(newsList);
	    		newsMessage.setArticleCount(newsList.size());
	    		message = MessageUtil.newsMessageToXml(newsMessage);
	    	}
	    	else
	    	{
	    		List<News> newsList = new ArrayList<News>();
	    		NewsMessage newsMessage = new NewsMessage();
	    		
	    		News news1 = new News();
	    		news1.setTitle("感谢关注无微不至的借阅伴侣管理员端！祝你有个好的管理体验！");
	    		news1.setDescription("感谢关注公众号");
	    		news1.setPicUrl("http://123.206.205.38/WeiXinAdmin/images/timg.jpg");  
	    		
	    		newsList.add(news1);
	    	
	    		newsMessage.setToUserName(fromUserName);
	    		newsMessage.setFromUserName(toUserName);
	    		newsMessage.setCreateTime(new Date().getTime());
	    		newsMessage.setMsgType("news");
	    		newsMessage.setArticles(newsList);
	    		newsMessage.setArticleCount(newsList.size());
	    		message = MessageUtil.newsMessageToXml(newsMessage);
	    	}
	    	return message;
	    }
	    
	    /**
	     * 取关   解除用户绑定
	     * @param fromUserName
	     * @throws SQLException
	     */
	    public static void AdminUNSUB(String fromUserName) throws SQLException
	    {
	    	if(ShowAdmininfoService.judgeUserInfo(fromUserName))
	    	{
	    		String SQL="delete from administrator where adminId='"+fromUserName+"'";
	    		if(SqlHelper.executeUpdate(SQL))
	    		{
	        		System.out.println("解除用户绑定成功！");
	    		}
	    		else
	    		{
	    			System.out.println("解除用户绑定失败！");
	    		}
	    	}
	    	else
	    	{
	    		System.out.println("不存在此用户！！");
	    	}
	    }
	    
	    /**
	     * 提示用户绑定才能用此功能
	     * @param toUserName
	     * @param fromUserName
	     * @return
	     * @throws SQLException
	     */
	    public static String HintAdminBind(String toUserName,String fromUserName) throws SQLException
	    {
	    	String message = null;
	    	StringBuffer sb = new StringBuffer();
	    	sb.append("您好!\n\n");
	    	sb.append("非管理员用户不能使用！\n");  
	    	sb.append("请点击用户->账户绑定 进行账号绑定！\n");
	    	sb.append("谢谢合作！！\n");
	    	message = MessageUtil.initText(toUserName, fromUserName, sb.toString());
	    	return message;
	    }
}
