package wx.wuwei.po;

public class AccessToken {
	private String token;//获取到的凭证
	private int expiresIn;//凭证有效时间，单位：秒
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}