package wx.wuwei.po;

import java.util.List;

public class NewsMessage extends BaseMessage {
	private int ArticleCount;//图文消息个数，限制为10条以内
	private List<News> Articles;//多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<News> getArticles() {
		return Articles;
	}
	public void setArticles(List<News> articles) {
		Articles = articles;
	}
}

