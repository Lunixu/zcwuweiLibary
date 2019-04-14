package wx.wuwei.po;

public class Libary {
	public int libaryId;
	public String name;
	public String address;
	public String description;
	public String gpsx;
	public String gpsy;
	
	public Libary(int libaryId,String name,String address,String description,String gpsx,String gpsy)
	{
		this.libaryId=libaryId;
		this.name=name;
		this.address=address;
		this.description=description;
		this.gpsx=gpsx;
		this.gpsy=gpsy;
	}
	
	public int getLibaryId() {
		return libaryId;
	}
	public void setLibaryId(int libaryId) {
		this.libaryId = libaryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGpsx() {
		return gpsx;
	}
	public void setGpsx(String gpsx) {
		this.gpsx = gpsx;
	}
	public String getGpsy() {
		return gpsy;
	}
	public void setGpsy(String gpsy) {
		this.gpsy = gpsy;
	}
	
	
}
