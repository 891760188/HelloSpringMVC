package controller.util;

public class Constant {
//	public static final String AppID = "wx511a467f3edc0e3b";
	public static final String AppID = "wx93e7e4e3e95d71b5";
	//public static final String secret = "47637845a968c6138a94e5f5a6f2f483";
	public static final String secret = "f056d4f6081dd1f1d748dc9317870e25";
	//https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
	
	public static final String access_token_url = "https://api.weixin.qq.com/cgi-bin/token";
	public static final String access_token_grant_type = "client_credential";
	public static final String MSGTYPE_IMAGE = "image";
	public static final String MSGTYPE_VOICE = "voice";
	public static final String MSGTYPE_LOCATION = "location";
	public static final String MSGTYPE_LINK = "link";
	public static final String MSGTYPE_EVENT = "event";
	public static final String EVENT_SUBSCRIBE = "subscribe";
	public static final String EVENT_UNSUBSCRIBE = "unsubscribe";
	public static  String access_token = "";
	public static  Integer access_token_expires_in = 0;
	public static  String URL_FILEUPLOAD = "http://1667006iw3.imwork.net/WeiXin/fileUpload.do";
	
}
