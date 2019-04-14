package wx.wuweiadmin.menu;

public class Button {
	//菜单类型
	private String type;
	//菜单名称
	private String name;
	//二级菜单数组，个数应为1~5个
	private Button[] sub_button;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Button[] getSub_button() {
		return sub_button;
	}
	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
}