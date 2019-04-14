package Model;

/* Í¼ÊéÄ£ÐÍ*/
public class Book {
       private int bookId;
       private String ISBN;
       private int num;
       private int leftnum;
       private int libaryId;
       private String firstChar;
       private String title;
       private String pingying;
       private String author;
       private String publish;
       private String version;
       private String face;
       private String preface;
       private String directory;
       private String content;
       private String comment;
       private String introduction;
       private String category;
       
    public Book(int bookId,int num,int leftnum,int libaryId,String ISBN,String title,String pingying,String author,String publish,String version,String face,String preface, String directory,String content,String comment,String introduction,String category,String firstChar)
    {
    	this.num=num;
    	this.leftnum=leftnum;
    	this.libaryId=libaryId;
    	this.bookId=bookId;
    	this.ISBN=ISBN;
		this.title=title;
		this.pingying=pingying;
		this.author=author;
		this.publish=publish;
		this.version=version;
		this.category=category;
		this.face=face;
		this.preface=preface;
		this.directory=directory;
		this.content=content;
		this.comment=comment;
		this.introduction=introduction;
		this.firstChar=firstChar;
    }
    public String getFirstChar() {
		return firstChar;
	}
	public void setFirstChar(String firstChar) {
		this.firstChar = firstChar;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getLeftnum() {
		return leftnum;
	}
	public void setLeftnum(int leftnum) {
		this.leftnum = leftnum;
	}
	public int getLibaryId() {
		return libaryId;
	}
	public void setLibaryId(int libaryId) {
		this.libaryId = libaryId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPingying() {
		return pingying;
	}
	public void setPingying(String pingying) {
		this.pingying = pingying;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public String getPreface() {
		return preface;
	}
	public void setPreface(String preface) {
		this.preface = preface;
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
}
