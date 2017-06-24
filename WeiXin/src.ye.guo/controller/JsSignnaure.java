package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;



import controller.util.CheckUtil;
import controller.util.Constant;
import controller.util.MenuUtil;
import entity.SignEntity;


public class JsSignnaure extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 接收微信服务器以Get请求发送的4个参数 
		String signature = request.getParameter("signature"); 
		String timestamp = request.getParameter("timestamp"); 
		String nonce = request.getParameter("nonce"); 
		String echostr = request.getParameter("echostr");
		System.out.println("signature="+signature);
		System.out.println("timestamp="+timestamp);
		System.out.println("nonce="+nonce);
		System.out.println("echostr="+echostr);
		if (CheckUtil.checkSignature(signature, timestamp, nonce)) { 
			out.print(echostr); // 校验通过，原样返回echostr参数内容 }
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter out = response.getWriter();
			String accessToken = MenuUtil.getAccessToken(Constant.AppID, Constant.secret);
			System.out.println("accessToken="+accessToken);
			String jsapi_ticket = MenuUtil.getJsapi_ticket(accessToken);
			System.out.println("jsapi_ticket="+jsapi_ticket);
			String url = request.getParameter("url"); 
			System.out.println("url="+url);
			String timestamp = new Date().getTime() + "";
			timestamp = timestamp.substring(0, timestamp.length() - 3);
			System.out.println("timestamp="+timestamp);
			String noncestr = UUID.randomUUID().toString();
			System.out.println("noncestr="+noncestr);
			String signature = CheckUtil.jsSign(jsapi_ticket, url, timestamp, noncestr);
			System.out.println("signature="+signature);
			SignEntity signEntity = new SignEntity(Constant.AppID, timestamp, noncestr, signature);
			System.out.println("signEntity="+signEntity);
			JSONObject jsonObject = JSONObject.fromObject(signEntity);
	        System.out.println(jsonObject);
	        out.print(jsonObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
		
		
	}
	
	
	
}
