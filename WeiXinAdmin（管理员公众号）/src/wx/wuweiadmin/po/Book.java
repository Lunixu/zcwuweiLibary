package wx.wuweiadmin.po;

import java.sql.ResultSet;
import java.util.ArrayList;

import wx.wuweiadmin.sqlhelper.SqlHelper;


/* 图书模型*/
public class Book {
       private int bookId;
       private String ISBN;
       private int num;
       private int leftnum;
       private int libaryId;
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
    //根据关键字,isbn,拼音全拼,首字母查询书籍
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList Search(String s)
	{   
		
		String sql =" select a.name from syscolumns a join sysobjects b on a.id=b.id where b.name='book' and a.name not in('num','leftnum','bookid','libaryid') ";
        @SuppressWarnings("unused")
		String name;
		ArrayList BooksList = new ArrayList();
		ResultSet rs = SqlHelper.executeQuery(sql);
		sql="";
		try
		{    
			rs.last();
			int length = rs.getRow();
			rs.beforeFirst();
			 while(rs.next())
			 {
			 length-=1;
			 if(length==0)
			 {
				 sql+="select * from book where "+rs.getString("name")+" like '%"+s+"%' ";
			 }
			 else
			 {
				 sql+="select * from book where "+rs.getString("name")+" like '%"+s+"%' UNION ";
			 }
			 }
		}
		catch(Exception e) {
			System.out.println(e);
		}
		System.out.println(sql);
		rs = SqlHelper.executeQuery(sql);
		try
		{
			while(rs.next())
			{
				
		Book book = new Book();
		book.setBookId(rs.getInt("bookid"));
		book.setISBN(rs.getString("isbn"));
		book.setLeftnum(rs.getInt("leftnum"));
		book.setNum(rs.getInt("num"));
		book.setLibaryId(rs.getInt("libaryid"));
		book.setPingying(rs.getString("pingying"));
		book.setPreface(rs.getString("preface"));
		book.setPublish(rs.getString("publish"));
		book.setVersion(rs.getString("Version"));
		book.setTitle(rs.getString("title"));
		book.setAuthor(rs.getString("author"));
		book.setContent(rs.getString("content"));
		book.setCategory(rs.getString("category"));
		book.setComment(rs.getString("comment"));
		book.setDirectory(rs.getString("directory"));
		book.setFace(rs.getString("face"));
		book.setIntroduction(rs.getString("Introduction"));
		@SuppressWarnings("unused")
		int id =book.getBookId();
	    @SuppressWarnings("unused")
		Book obook = new Book();
		BooksList.add(book);
		
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		/*try {
			while (rs.next()) {
				name=rs.getString("name");
				System.out.print(name);
				sql = "select  * from book where CONTAINS ("+name+",'"+s+"')";
				ResultSet ors = SqlHelper.executeQuery(sql);
				if (rs!=null)
				{  
					
					while(ors.next())
					{
						
				Book book = new Book();
				book.setBookId(ors.getInt("bookid"));
				book.setISBN(ors.getString("isbn"));
				book.setLeftnum(ors.getInt("leftnum"));
				book.setNum(ors.getInt("num"));
				book.setLibaryId(ors.getInt("libaryid"));
				book.setPingying(ors.getString("pingying"));
				book.setPreface(ors.getString("preface"));
				book.setPublish(ors.getString("publish"));
				book.setVersion(ors.getString("Version"));
				book.setTitle(ors.getString("title"));
				book.setAuthor(ors.getString("author"));
				book.setContent(ors.getString("content"));
				book.setCategory(ors.getString("category"));
				book.setComment(ors.getString("comment"));
				book.setDirectory(ors.getString("directory"));
				book.setFace(ors.getString("face"));
				book.setIntroduction(ors.getString("Introduction"));
				int id =book.getBookId();
				boolean flag = true;
				Book obook = new Book();
				for(int i=0;i<BooksList.size();i++)
				{   
					
					obook = (Book)BooksList.get(i);
					if(obook.getBookId()==id)
					{  
						flag = false;
						break;
					}
				}
				if(flag)
				{   
					
					BooksList.add(book);
				}
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}*/
		
		return BooksList;
	}
	//通过ISBN获得图书信息
	public   Book  SearchByISBN(String ISBN)
	{  
		Book book = new Book();
		String sql ="  select * from Book where ISBN = '"+ISBN+"' ";
		ResultSet rs = SqlHelper.executeQuery(sql);
		try {
			while (rs.next()) {
				
				book.setBookId(rs.getInt("bookid"));
				book.setISBN(rs.getString("isbn"));
				book.setLeftnum(rs.getInt("leftnum"));
				book.setNum(rs.getInt("num"));
				book.setLibaryId(rs.getInt("libaryid"));
				book.setPingying(rs.getString("pingying"));
				book.setPreface(rs.getString("preface"));
				book.setPublish(rs.getString("publish"));
				book.setVersion(rs.getString("Version"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setContent(rs.getString("content"));
				book.setCategory(rs.getString("category"));
				book.setComment(rs.getString("comment"));
				book.setDirectory(rs.getString("directory"));
				book.setFace(rs.getString("face"));
				book.setIntroduction(rs.getString("Introduction"));
				
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return book;
	}
	//根据bookid查询书籍
		public Book SearchById(int id)
		{  
			Book book = new Book();
			String sql ="select * from book where bookid ="+id+" ";
			ResultSet rs = SqlHelper.executeQuery(sql);
			book = (Book)ResultSetToBook(rs).get(0);
			return book;
			
		}
		//用查询到结果集实例化book类
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public ArrayList ResultSetToBook(ResultSet rs)
		{  
			ArrayList bookslist = new ArrayList();
			try {
				while (rs.next()) {
					Book book = new Book();
					book.setBookId(rs.getInt("bookid"));
					book.setISBN(rs.getString("isbn"));
					book.setLeftnum(rs.getInt("leftnum"));
					book.setNum(rs.getInt("num"));
					book.setLibaryId(rs.getInt("libaryid"));
					book.setPingying(rs.getString("pingying"));
					book.setPreface(rs.getString("preface"));
					book.setPublish(rs.getString("publish"));
					book.setVersion(rs.getString("Version"));
					book.setTitle(rs.getString("title"));
					book.setAuthor(rs.getString("author"));
					book.setContent(rs.getString("content"));
					book.setCategory(rs.getString("category"));
					book.setComment(rs.getString("comment"));
					book.setDirectory(rs.getString("directory"));
					book.setFace(rs.getString("face"));
					book.setIntroduction(rs.getString("Introduction"));
					bookslist.add(book);
					
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			return bookslist;
		}
}




