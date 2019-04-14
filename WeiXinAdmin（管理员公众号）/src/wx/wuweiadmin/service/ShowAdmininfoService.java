package wx.wuweiadmin.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import wx.wuweiadmin.sqlhelper.SqlHelper;

/**
 * 显示管理员信息及判断是否判定
 * @author ZX50j
 *
 */
public class ShowAdmininfoService {
	/**
	 * 判断是否有用户信息，即是否绑定
	 * @param fromUserName
	 * @return
	 * @throws SQLException 
	 */
	public static boolean  judgeUserInfo(String fromUserName) throws SQLException
	{
		String SQL = "select count(*)from administrator where adminId='"+fromUserName+"'";//查询管理员是否存在
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
