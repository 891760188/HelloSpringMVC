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
import controller.util.MessageUtil;
import entity.PicMeaasge;
import entity.TextMeaasge;

public class Test extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ����΢�ŷ�������Get�����͵�4������ 
		String signature = request.getParameter("signature"); 
		String timestamp = request.getParameter("timestamp"); 
		String nonce = request.getParameter("nonce"); 
		String echostr = request.getParameter("echostr");
		System.out.println("signature="+signature);
		System.out.println("timestamp="+timestamp);
		System.out.println("nonce="+nonce);
		System.out.println("echostr="+echostr);
		PrintWriter out = response.getWriter(); 
		if (CheckUtil.checkSignature(signature, timestamp, nonce)) { 
			out.print(echostr); // У��ͨ����ԭ������echostr�������� }
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
			System.out.println("΢�ŷ��������͹�������Ϣmap="+map.toString());
			
			String message = null;
			// ���ı���Ϣ���д��� 
			if ("text".equals(msgType)) { 
				TextMeaasge text = new TextMeaasge(); 
				text.setFromUserName(toUserName); // ���ͺͻظ��Ƿ���� 
				text.setToUserName(fromUserName); 
				text.setMsgType("text"); 
				text.setCreateTime(new Date().getTime()); 
				text.setContent("�յ�������Ϣ=" + content); 
				System.out.println("text="+text);
				message = MessageUtil.messageToXML(TextMeaasge.class,text);
				System.out.println("����ȥ����Ϣ="+message);  
				out.println(message);
			}
			// ��ͼƬ��Ϣ���д��� 
//			if (Constant.MSGTYPE_IMAGE.equals(msgType)) { 
//				TextMeaasge text = new TextMeaasge(); 
//				text.setFromUserName(toUserName); // ���ͺͻظ��Ƿ���� 
//				text.setToUserName(fromUserName); 
//				text.setMsgType("text"); 
//				text.setCreateTime(new Date().getTime()); 
//				text.setContent("�յ�������Ϣ=" + "tupian=http://mmbiz.qpic.cn/mmbiz_jpg/Zjn7GF7icQVlFxQia78HL2JTlJYCTtbGua7WCUWgAJA4dB3Ds5icpdOI2Pv1Oqb6RjK1icW0IIgxicma0gdNaL4tsTw/0"); 
//				System.out.println("text="+text);
//				message = MessageUtil.textMessageToXML(text);
//				System.out.println("����ȥ����Ϣ="+message);  
//				out.println(message);
//			}
			// ��ͼƬ��Ϣ���д���   ����ͼƬ
			if (Constant.MSGTYPE_IMAGE.equals(msgType)) { 
				PicMeaasge text = new PicMeaasge(); 
				text.setFromUserName(toUserName); // ���ͺͻظ��Ƿ���� 
				text.setToUserName(fromUserName); 
				text.setMsgType(Constant.MSGTYPE_IMAGE); 
				text.setCreateTime(new Date().getTime()); 
				text.setMediaId("_Tfjk8mGkqno6T6BtxZnfxHrRMYKkWxDCVc04bx4ppdy25U1SKQEOmyknFd9rC-d");
				System.out.println("text="+text);
				message = MessageUtil.messageToXML(PicMeaasge.class,text);
				System.out.println("����ȥ����Ϣ="+message);  
				out.println(message);
			}
			// ��������Ϣ���д��� 
			if (Constant.MSGTYPE_VOICE.equals(msgType)) { 
				TextMeaasge text = new TextMeaasge(); 
				text.setFromUserName(toUserName); // ���ͺͻظ��Ƿ���� 
				text.setToUserName(fromUserName); 
				text.setMsgType("text"); 
				text.setCreateTime(new Date().getTime()); 
				text.setContent("�յ�������Ϣ=" + "����"); 
				System.out.println("text="+text);
				message = MessageUtil.textMessageToXML(text);
				System.out.println("����ȥ����Ϣ="+message);  
				out.println(message);
			}
			// �Ե���λ����Ϣ���д��� 
			if (Constant.MSGTYPE_LOCATION.equals(msgType)) { 
				TextMeaasge text = new TextMeaasge(); 
				text.setFromUserName(toUserName); // ���ͺͻظ��Ƿ���� 
				text.setToUserName(fromUserName); 
				text.setMsgType("text"); 
				text.setCreateTime(new Date().getTime()); 
				text.setContent("�յ�������Ϣ=" + Label); 
				System.out.println("text="+Label);
				message = MessageUtil.textMessageToXML(text);
				System.out.println("����ȥ����Ϣ="+message);  
				out.println(message);
			}
			// �Ե���λ����Ϣ���д��� 
			if (Constant.MSGTYPE_LINK.equals(msgType)) { 
				TextMeaasge text = new TextMeaasge(); 
				text.setFromUserName(toUserName); // ���ͺͻظ��Ƿ���� 
				text.setToUserName(fromUserName); 
				text.setMsgType("text"); 
				text.setCreateTime(new Date().getTime()); 
				text.setContent("�յ�������Ϣ=" + "������Ϣ"); 
				System.out.println("text="+Label);
				message = MessageUtil.textMessageToXML(text);
				System.out.println("����ȥ����Ϣ="+message);  
				out.println(message);
			}
			// ��ע/ȡ����ע
			if (Constant.MSGTYPE_EVENT.equals(msgType)) { 
				String answer = "Ĭ����Ϣ";
				if (Constant.EVENT_SUBSCRIBE.equals(Event)) {//����
					answer = "���ĳɹ�";
				} else  if(Constant.EVENT_UNSUBSCRIBE.equals(Event)){//ȡ������
					answer = "ȡ������";
				}
				TextMeaasge text = new TextMeaasge(); 
				text.setFromUserName(toUserName); // ���ͺͻظ��Ƿ���� 
				text.setToUserName(fromUserName); 
				text.setMsgType("text"); 
				text.setCreateTime(new Date().getTime()); 
				text.setContent("�յ�������Ϣ=" + answer); 
				System.out.println("text="+Label);
				message = MessageUtil.textMessageToXML(text);
				System.out.println("����ȥ����Ϣ="+message);  
				out.println(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
	
	public void getAssect(){
		//��ȡaccess_token
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
				//��ȡaccess_token�ɹ�
				System.out.println("access_token="+Constant.access_token +"    expires_in="+ Constant.access_token_expires_in+"   errcode="+errcode+"errmsg    errmsg="+errmsg);
	}
	
}
