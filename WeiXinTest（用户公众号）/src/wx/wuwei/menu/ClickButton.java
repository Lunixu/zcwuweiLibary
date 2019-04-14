package wx.wuwei.menu;

public class ClickButton extends Button{
	//Click类型菜单key, 菜单KEY值，用于消息接口推送，不超过128字节
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
