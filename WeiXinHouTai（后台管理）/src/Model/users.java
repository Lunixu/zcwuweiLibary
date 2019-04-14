package Model;

public class users {
	private String adminId;
	private String adminUsername;
	private String adminpassword;
	
	public users()
	{
	}
	public users(String adminId,String adminUsername,String adminpassword)
	{
		this.adminId=adminId;
		this.adminUsername=adminUsername;
		this.adminpassword=adminpassword;
	}
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminUsername() {
		return adminUsername;
	}
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
	public String getAdminpassword() {
		return adminpassword;
	}
	public void setAdminpassword(String adminpassword) {
		this.adminpassword = adminpassword;
	}
	
	
}