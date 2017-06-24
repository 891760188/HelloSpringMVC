package controller;

import java.util.HashMap;
import java.util.Map;

import controller.util.Constant;
import controller.util.HttpRequest;


import net.sf.json.JSONObject;

public class MainTest {
	public static void main(String[] args) {
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
		
		
		//获取微信服务器IP地址
		
	}
}
