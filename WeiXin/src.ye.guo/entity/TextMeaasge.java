package entity;

import java.util.Date;

public class TextMeaasge {
	private String ToUserName ;
	private String FromUserName ;
	private String MsgType ;
	private String Content ;
	private long CreateTime ;
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	@Override
	public String toString() {
		return "TextMeaasge [ToUserName=" + ToUserName + ", FromUserName="
				+ FromUserName + ", MsgType=" + MsgType + ", Content="
				+ Content + ", CreateTime=" + CreateTime + "]";
	}
	
	
	
}
