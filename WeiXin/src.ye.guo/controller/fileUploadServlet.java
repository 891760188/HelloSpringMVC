package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import controller.util.CheckUtil;
import controller.util.Constant;
import controller.util.HttpRequest;
import controller.util.MenuUtil;
import controller.util.MessageUtil;
import entity.TextMeaasge;

public class fileUploadServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter out = response.getWriter();
			String accessToken = MenuUtil.getAccessToken(Constant.AppID, Constant.secret);  
	        System.out.println("获取的到的accessToken="+accessToken);
	        out.append(accessToken);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			//getAssect();
			Map<String, String> map = MessageUtil.xmlToMap(request); 
			String toUserName = map.get("ToUserName"); 
			String fromUserName = map.get("FromUserName"); 
			String msgType = map.get("MsgType"); 
			String content = map.get("Content"); 
			String CreateTime = map.get("CreateTime"); 
			String MediaId = map.get("MediaId"); 
			String MsgId = map.get("MsgId"); 
			String Format = map.get("Format"); 
			String Recognition = map.get("Recognition"); 
			String Location_X = map.get("Location_X"); 
			String Location_Y = map.get("Location_Y"); 
			String Scale = map.get("Scale"); 
			String Label = map.get("Label"); 
			String Event = map.get("Event"); 
			System.out.println("微信服务器推送过来的消息map="+map.toString());
			
			String message = null;
			// 对文本消息进行处理 
			if ("text".equals(msgType)) { 
				TextMeaasge text = new TextMeaasge(); 
				text.setFromUserName(toUserName); // 发送和回复是反向的 
				text.setToUserName(fromUserName); 
				text.setMsgType("text"); 
				text.setCreateTime(new Date().getTime()); 
				text.setContent("收到您的消息=" + content); 
				System.out.println("text="+text);
				message = MessageUtil.textMessageToXML(text);
				System.out.println("发出去的消息="+message);  
				out.println(message);
			}
			// 对图片消息进行处理 
			if (Constant.MSGTYPE_IMAGE.equals(msgType)) { 
				TextMeaasge text = new TextMeaasge(); 
				text.setFromUserName(toUserName); // 发送和回复是反向的 
				text.setToUserName(fromUserName); 
				text.setMsgType("text"); 
				text.setCreateTime(new Date().getTime()); 
				text.setContent("收到您的消息=" + "tupian=http://mmbiz.qpic.cn/mmbiz_jpg/Zjn7GF7icQVlFxQia78HL2JTlJYCTtbGua7WCUWgAJA4dB3Ds5icpdOI2Pv1Oqb6RjK1icW0IIgxicma0gdNaL4tsTw/0"); 
				System.out.println("text="+text);
				message = MessageUtil.textMessageToXML(text);
				System.out.println("发出去的消息="+message);  
				out.println(message);
			}
			// 对语音消息进行处理 
			if (Constant.MSGTYPE_VOICE.equals(msgType)) { 
				TextMeaasge text = new TextMeaasge(); 
				text.setFromUserName(toUserName); // 发送和回复是反向的 
				text.setToUserName(fromUserName); 
				text.setMsgType("text"); 
				text.setCreateTime(new Date().getTime()); 
				text.setContent("收到您的消息=" + "语音"); 
				System.out.println("text="+text);
				message = MessageUtil.textMessageToXML(text);
				System.out.println("发出去的消息="+message);  
				out.println(message);
			}
			// 对地理位置消息进行处理 
			if (Constant.MSGTYPE_LOCATION.equals(msgType)) { 
				TextMeaasge text = new TextMeaasge(); 
				text.setFromUserName(toUserName); // 发送和回复是反向的 
				text.setToUserName(fromUserName); 
				text.setMsgType("text"); 
				text.setCreateTime(new Date().getTime()); 
				text.setContent("收到您的消息=" + Label); 
				System.out.println("text="+Label);
				message = MessageUtil.textMessageToXML(text);
				System.out.println("发出去的消息="+message);  
				out.println(message);
			}
			// 对地理位置消息进行处理 
			if (Constant.MSGTYPE_LINK.equals(msgType)) { 
				TextMeaasge text = new TextMeaasge(); 
				text.setFromUserName(toUserName); // 发送和回复是反向的 
				text.setToUserName(fromUserName); 
				text.setMsgType("text"); 
				text.setCreateTime(new Date().getTime()); 
				text.setContent("收到您的消息=" + "连接消息"); 
				System.out.println("text="+Label);
				message = MessageUtil.textMessageToXML(text);
				System.out.println("发出去的消息="+message);  
				out.println(message);
			}
			// 关注/取消关注
			if (Constant.MSGTYPE_EVENT.equals(msgType)) { 
				String answer = "默认消息";
				if (Constant.EVENT_SUBSCRIBE.equals(Event)) {//订阅
					answer = "订阅成功";
				} else  if(Constant.EVENT_UNSUBSCRIBE.equals(Event)){//取消订阅
					answer = "取消订阅";
				}
				TextMeaasge text = new TextMeaasge(); 
				text.setFromUserName(toUserName); // 发送和回复是反向的 
				text.setToUserName(fromUserName); 
				text.setMsgType("text"); 
				text.setCreateTime(new Date().getTime()); 
				text.setContent("收到您的消息=" + answer); 
				System.out.println("text="+Label);
				message = MessageUtil.textMessageToXML(text);
				System.out.println("发出去的消息="+message);  
				out.println(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
	
	public void getAssect(){
		//获取access_token
				String pathURL = Constant.access_token_url ;
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("grant_type", Constant.access_token_grant_type);
				param.put("appid", Constant.AppID);
				param.put("secret", Constant.secret);
				String httpJson = HttpRequest.sendGet(pathURL, param);
				JSONObject jsonobject = JSONObject.fromObject(httpJson);
				Map<String, Object> entity = (Map<String, Object>)JSONObject.toBean(jsonobject, HashMap.class);
				Constant.access_token = (String)entity.get("access_token");
				Constant.access_token_expires_in= (Integer)entity.get("expires_in");
				String errcode= (String)entity.get("errcode");
				String errmsg= (String)entity.get("errmsg");
				//获取access_token成功
				System.out.println("access_token="+Constant.access_token +"    expires_in="+ Constant.access_token_expires_in+"   errcode="+errcode+"errmsg    errmsg="+errmsg);
	}
	
}
