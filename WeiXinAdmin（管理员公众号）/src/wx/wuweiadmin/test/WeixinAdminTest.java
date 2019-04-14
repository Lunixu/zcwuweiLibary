package wx.wuweiadmin.test;

import net.sf.json.JSONObject;
import wx.wuweiadmin.po.AccessToken;
import wx.wuweiadmin.util.WeixinUtil;

public class WeixinAdminTest {
	public static void main(String[] args) {
		try 
		{
			AccessToken token = WeixinUtil.getAccessToken();
			System.out.println("票据"+token.getToken());
			System.out.println("有效时间"+token.getExpiresIn());
			
			//创建菜单
			String menu=JSONObject.fromObject(WeixinUtil.initMenu()).toString();
			int res=WeixinUtil.createMenu(token.getToken(),menu);
			if(res==0)
			{
				System.out.println("创建菜单成功");
			}
			else
			{
				System.out.println("错误码："+res);
			}
			
			//打印菜单
			JSONObject jsonObject = WeixinUtil.queryMenu(token.getToken());
			System.out.println(jsonObject);//打印菜单的数据结构形式
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
