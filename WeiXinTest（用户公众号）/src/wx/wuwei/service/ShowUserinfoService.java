package wx.wuwei.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import wx.wuwei.po.Reader;
import wx.wuwei.sqlhelper.SqlHelper;
/**
 * 显示用户信息操作
 * @author ZX50j
 *
 */
public class ShowUserinfoService {
	/**
	 * 得到用户在数据库中的信息
	 * @param fromUserName
	 * @return
	 */
	public static Reader getUserInfo(String fromUserName)
	{
		String SQL = "select *from reader where readerId='"+fromUserName+"'";//查询用户个人信息
		ResultSet res=SqlHelper.executeQuery(SQL);
		Reader reader= new Reader();
		try
		{
			while(res.next())
			{
				String readerId = res.getString("readerId");
				String name = res.getString("name");//获取name列的元素
				String sex = res.getString("sex");
				String brithday = res.getString("brithday");
				String education = res.getString("education");
				String hobby = res.getString("hobby");
				
				reader.setReaderId(readerId);
				reader.setName(name);
				reader.setSex(sex);
				reader.setBrithday(brithday);
				reader.setEducation(education);
				reader.setHobby(hobby);				
				
			}	
			return reader;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 判断是否有用户信息，即是否绑定
	 * @param fromUserName
	 * @return
	 * @throws SQLException 
	 */
	public static boolean  judgeUserInfo(String fromUserName) throws SQLException
	{
		String SQL = "select count(*)from reader where readerId='"+fromUserName+"'";//查询用户个人信息
		ResultSet res=SqlHelper.executeQuery(SQL);
		int result=0;
		if(res.next())
		{
			result = res.getInt(1);
		}
		if(result ==1)
		{
			return true;
		}
		
		return false;
	}
}
