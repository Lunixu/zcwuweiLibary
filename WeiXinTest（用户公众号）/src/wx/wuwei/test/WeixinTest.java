package wx.wuwei.test;

import net.sf.json.JSONObject;

import wx.wuwei.po.AccessToken;
import wx.wuwei.po.Reader;
import wx.wuwei.service.ShowUserinfoService;
import wx.wuwei.util.CommonUtil;
import wx.wuwei.util.WeixinUtil;

public class WeixinTest {
	public static void main(String[] args) {
		try {
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
			
			
			String url=CommonUtil.urlEncodeUTF8("&");
			System.out.println(url);
			
			/*int r2=WeixinUtil.deleteMenu(token.getToken());
			if(r2==0)
			{
				System.out.println("菜单删除成功");
			}
			else
			{
				System.out.println("错误码："+r2);
			}*/
			
			Reader reader=ShowUserinfoService.getUserInfo("omwjQ0aZv3eWZVd6QV80zSz9rnvw");
			if(reader!= null && !reader.equals(""))
			{
				String userid=reader.getReaderId();
				System.out.println(userid);
				
			}
			else
			{
				System.out.println("kong");
			}
			/*
			String ticket=QRCodeService.createPermanentORCode(token.getToken(), "123");
			System.out.println("ticket"+ticket);
			
			String showUrl=QRCodeService.showQRcode(ticket);
			System.out.println("二维码地址"+showUrl);
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
