package wx.wuwei.menu;

public class ViewButton extends Button{
	//view类型菜单url , 网页链接，用户点击菜单可打开链接，不超过1024字节
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
