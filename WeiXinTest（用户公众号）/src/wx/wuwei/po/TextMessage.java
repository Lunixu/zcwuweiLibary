package wx.wuwei.po;

public class TextMessage extends BaseMessage {
    
    private String Content;//文本消息内容
    private long MsgId;//消息id，64位整型
    
    
    public String getContent() {
        return Content;
    }
    public void setContent(String content) {
        Content = content;
    }
    public long getMsgId() {
        return MsgId;
    }
    public void setMsgId(long msgId) {
        MsgId = msgId;
    }
}