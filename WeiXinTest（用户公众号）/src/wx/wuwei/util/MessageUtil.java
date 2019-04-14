package wx.wuwei.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import wx.wuwei.po.News;
import wx.wuwei.po.NewsMessage;
import wx.wuwei.po.Reader;
import wx.wuwei.po.TextMessage;
import wx.wuwei.service.ShowUserinfoService;
import wx.wuwei.sqlhelper.SqlHelper;
import wx.wuwei.util.MessageUtil;

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
    @SuppressWarnings({ "unchecked", "rawtypes" })
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

                @SuppressWarnings({  "rawtypes" })
                public void startNode(String name, Class clazz) {
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
     * 显示用户个人信息
     * @param fromUserName
     * @return
     */
    public static String userInfo(String fromUserName,String toUserName){
    	String message = null;
    	Reader reader=ShowUserinfoService.getUserInfo(fromUserName);
        StringBuffer sb = new StringBuffer();
        sb.append("姓名："+reader.getName()+"\n");
        sb.append("证号："+reader.getReaderId()+"\n");  
        sb.append("性别："+reader.getSex()+"\n");
        sb.append("生日："+reader.getBrithday()+"\n");
        sb.append("学历："+reader.getEducation()+"\n");
        sb.append("爱好："+reader.getHobby()+"\n\n");
        sb.append("若以上信息有误，请及时与我们联系。\n");
        
        //图文消息
		List<News> newsList = new ArrayList<News>();
		NewsMessage newsMessage = new NewsMessage();
		
		News news = new News();
		news.setTitle("您的个人信息");
		news.setDescription("用户信息");
		news.setPicUrl("http://123.206.205.38/WeiXinTest/image/timg.jpg");
		//news.setUrl("");   
		
		News news2 = new News();
		news2.setTitle(sb.toString());
		news2.setDescription("个人信息情况");
		//news2.setPicUrl("");
		//news2.setUrl("");
		
		newsList.add(news);
		newsList.add(news2);
		
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType("news");
		newsMessage.setArticles(newsList);
		newsMessage.setArticleCount(newsList.size());
		message = MessageUtil.newsMessageToXml(newsMessage);
        
        return message;       
    }
    /**
     * 关于我们
     * @param fromUserName
     * @param toUserName
     * @return
     */
    public static String AboutUs(String fromUserName,String toUserName)
    {
    	String message=null;
    	StringBuffer sb = new StringBuffer();
    	sb.append("您好!欢迎使用无微不至的借阅伴侣！！\n\n");
    	sb.append("如您在使用过程中有什么疑问，请联系我们。\n");  
    	sb.append("邮箱：1124588341@qq.com\n");
    	sb.append("谢谢您的使用！！\n\n");
    	sb.append("我们是来自西南石油大学的BYT团队。\n");
    	
    	List<News> newsList = new ArrayList<News>();
		NewsMessage newsMessage = new NewsMessage();
		
		News news = new News(); 
		news.setTitle("关于我们");
		
		News news2 = new News();
		news2.setTitle(sb.toString());
		news2.setDescription("关于我们");

		
		newsList.add(news);
		newsList.add(news2);
		
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType("news");
		newsMessage.setArticles(newsList);
		newsMessage.setArticleCount(newsList.size());
		message = MessageUtil.newsMessageToXml(newsMessage);	
    	return message;
    }
    
    /**
     * 绑定用户
     * @param toUserName
     * @param fromUserName
     * @return
     * @throws SQLException
     */
    public static String BindUser(String toUserName,String fromUserName) throws SQLException
    {
    	String message = null;
    	if(!ShowUserinfoService.judgeUserInfo(fromUserName))
		{
			//图文消息  未绑定
    		List<News> newsList = new ArrayList<News>();
    		NewsMessage newsMessage = new NewsMessage();
    		
    		News news = new News();
    		news.setTitle("绑定信息");
    		news.setDescription("绑定用户微信与公众号");
    		news.setPicUrl("http://123.206.205.38/WeiXinTest/image/timg.jpg");  
    		
    		News news2 = new News();
    		news2.setTitle("您的账号暂未绑定，请点击这里进行账号绑定！");
    		news2.setDescription("账号绑定");
    		news2.setPicUrl("http://123.206.205.38/WeiXinTest/image/timg.jpg");
    		news2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1e83e75abd38e250&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinTest%2Flogin.jsp%3FreaderId%3D"+fromUserName+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
    		
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
    		news.setDescription("解除绑定用户微信与公众号");
    		news.setPicUrl("http://123.206.205.38/WeiXinTest/image/timg.jpg");                   		 
    		
    		News news2 = new News();
    		news2.setTitle("您的账号已绑定,点击这里将解除绑定！");
    		news2.setDescription("账号解除绑定");
    		news2.setPicUrl("http://123.206.205.38/WeiXinTest/image/timg.jpg");
    		news2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1e83e75abd38e250&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinTest%2FDeleteUser%3FreaderId%3D"+fromUserName+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
    		
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
    public static String UserSUB(String toUserName,String fromUserName) throws SQLException
    {
    	String message = null;
    	if(!ShowUserinfoService.judgeUserInfo(fromUserName))
    	{
    		//图文消息 提示用户绑定
    		List<News> newsList = new ArrayList<News>();
    		NewsMessage newsMessage = new NewsMessage();
    		
    		News news1 = new News();
    		news1.setTitle("感谢关注无微不至的借阅伴侣！");
    		news1.setDescription("感谢关注公众号");
    		news1.setPicUrl("http://123.206.205.38/WeiXinTest/image/timg.jpg");  
    		
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
    		news1.setTitle("感谢关注无微不至的借阅伴侣！祝你有个好的借阅体验！");
    		news1.setDescription("感谢关注公众号");
    		news1.setPicUrl("http://123.206.205.38/WeiXinTest/image/timg.jpg");  
    		
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
     * 提示用户绑定才能用此功能
     * @param toUserName
     * @param fromUserName
     * @return
     * @throws SQLException
     */
    public static String HintUserBind(String toUserName,String fromUserName) throws SQLException
    {
    	String message = null;
    	StringBuffer sb = new StringBuffer();
    	sb.append("您好!\n\n");
    	sb.append("您的微信号暂未进行绑定，未绑定用户部分功能不能使用，请尽快绑定。\n");  
    	sb.append("点击用户->账户绑定 进行账号绑定！\n\n");
    	sb.append("谢谢合作！！\n");
    	
    	List<News> newsList = new ArrayList<News>();
		NewsMessage newsMessage = new NewsMessage();
		
		News news = new News(); 
		news.setTitle("提示绑定！");
		
		News news2 = new News();
		news2.setTitle(sb.toString());
		news2.setDescription("提示绑定！");

		
		newsList.add(news);
		newsList.add(news2);
		
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType("news");
		newsMessage.setArticles(newsList);
		newsMessage.setArticleCount(newsList.size());
		message = MessageUtil.newsMessageToXml(newsMessage);	
    	return message;
    }
    /**
     * 扫码借书
     * @param QRcodeInfo
     * @return
     */
    public static String ScanCodeReading(String QRcodeInfo,String toUserName,String fromUserName)
    {
    	String message=null;
    	String ISBN = QRcodeInfo;
    	String SQL="select bookId from book where ISBN='"+ISBN+"'";
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
		String sql1="select count(*) from BookReserve where readerid='"+fromUserName+"' and bookid="+bookid;
		ResultSet rs1=SqlHelper.executeQuery(sql1);
		int r=0;
		try 
		{
			while(rs1.next())
			{
				r=rs1.getInt(1);
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(r!=0)
		{
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
    		String sql="delete from BookReserve  where bookid="+bookid+" and readerid='"+fromUserName+"';insert into bookBorrow('"+fromUserName+"',"+bookid+",'"+ordertime+"','"+returntime+"')";
    		System.out.print(sql);
			SqlHelper.executeUpdate(sql);
			message="预订借书成功!!";
			message = MessageUtil.initText(toUserName, fromUserName, message);
		}
		else
		{
	    	String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1e83e75abd38e250&redirect_uri=http%3A%2F%2F123.206.205.38%2FWeiXinTest%2FScanCodeReading%3FISBN%3Disbninfo&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
	    	String newurl=url.replace("isbninfo", QRcodeInfo);
	    	StringBuffer sb = new StringBuffer();
	        sb.append("您好！\n\n");
	        sb.append("详细图书信息，请点击：");
	        sb.append("<a href='"+newurl+"'>图书信息</a>\n\n");
	        sb.append("谢谢合作！！\n");
			message = MessageUtil.initText(toUserName, fromUserName, sb.toString());	
		}
    	return message;
    } 
    /**
     * 还书提醒
     * @param info
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String Remind(String message)
    {
    	return message;
    }
} 
