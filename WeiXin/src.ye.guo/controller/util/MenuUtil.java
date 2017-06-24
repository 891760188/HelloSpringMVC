package controller.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.entity.StringEntity;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.util.EntityUtils;  
import org.json.JSONObject;  

  
public class MenuUtil {  
  
    public static String APPID,APPSECRET;  
      
    //http客户端    
    public static DefaultHttpClient httpclient;  
      
    static {    
        httpclient = new DefaultHttpClient();    
        httpclient = (DefaultHttpClient) HttpClientConnectionManager.getSSLInstance(httpclient); // 接受任何证书的浏览器客户端  
        APPID = "";//你的APPID  
        APPSECRET = ""; //你的APPSECRET  
    }    
      
    /** 
     * 创建菜单  
     */  
    public static String createMenu(String params, String accessToken) throws Exception {    
        HttpPost httpost = HttpClientConnectionManager.getPostMethod("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken);    
        httpost.setEntity(new StringEntity(params, "UTF-8"));    
        HttpResponse response = httpclient.execute(httpost);    
        String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");    
        JSONObject demoJson = new JSONObject(jsonStr);  
        return demoJson.getString("errcode");  
    }    
    /** 
     * 
     */  
    public static String msg(String params, String accessToken ,String url) throws Exception {    
    	HttpPost httpost = HttpClientConnectionManager.getPostMethod(url + accessToken);    
    	httpost.setEntity(new StringEntity(params, "UTF-8"));    
    	HttpResponse response = httpclient.execute(httpost);    
    	String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");    
    	JSONObject demoJson = new JSONObject(jsonStr);  
    	return demoJson.getString("errcode")+"==="+demoJson.getString("errmsg");  
    }    
    /** 
     * 
     */  
    public static JSONObject msgJSONObject(String params, String accessToken ,String url) throws Exception {    
    	HttpPost httpost = HttpClientConnectionManager.getPostMethod(url + accessToken);    
    	httpost.setEntity(new StringEntity(params, "UTF-8"));    
    	HttpResponse response = httpclient.execute(httpost);    
    	String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");    
    	JSONObject demoJson = new JSONObject(jsonStr);  
    	return demoJson;  
    }    
    /** 
     * 获取accessToken  
     */  
    public static String getAccessToken(String appid, String secret) throws Exception {    
        HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret);    
        HttpResponse response = httpclient.execute(get);    
        String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");  
        JSONObject demoJson = new JSONObject(jsonStr);  
        return demoJson.getString("access_token");  
    }    
    /** 
     * 查询菜单 
     */  
    public static String getMenuInfo(String accessToken) throws Exception {    
        HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken);    
        HttpResponse response = httpclient.execute(get);    
        String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");    
        return jsonStr;    
    }    
      
    /**  
     * 删除菜单  
     */    
    public static String deleteMenuInfo(String accessToken) throws Exception {    
        HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accessToken);    
        HttpResponse response = httpclient.execute(get);    
        String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");    
        JSONObject demoJson = new JSONObject(jsonStr);  
        return demoJson.getString("errmsg");    
    }    
      
   /* 
    * 测试   发送客服消息-文本消息
    */  
    
    public static void sedKeFuMsg(){
    	StringBuffer sb = new StringBuffer();  
        sb.append("" +
				"{\n" +
				"    \"touser\":\"oVkrAwfJNlzS509f-T6iCc2Wg9OA\",\n" + 
				"    \"msgtype\":\"text\",\n" + 
				"    \"text\":\n" + 
				"    {\n" + 
				"         \"content\":\"Hello World\"\n" + 
				"    }\n" + 
				"}"
				);  
				          
        try {  
            // 获取accessToken -参数appid，secret    
            String accessToken = getAccessToken(Constant.AppID, Constant.secret);  
            System.out.println("获取的到的accessToken="+accessToken);
            String res="";  
            //创建菜单
           res = msg(sb.toString(), accessToken , "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=");    
           System.out.println("结束res="+res);   
            
            //获取微信服务号的ip地址
            //getWeiXinIpList(accessToken);
            
        } catch (Exception e) {  
            e.printStackTrace();  
        }
    }
    public static void getUserList(){
    
    	
    	try {  
    		// 获取accessToken -参数appid，secret    
    		String accessToken = getAccessToken(Constant.AppID, Constant.secret);  
    		System.out.println("获取的到的accessToken="+accessToken);
    		String res="";  
    		//创建菜单
    		Map<String, Object> map = new HashMap<String, Object>();
    		res = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/user/get?access_token="+accessToken+"&next_openid=",null);
    		System.out.println("结束res="+res);   
    		
    		//获取微信服务号的ip地址
    		//getWeiXinIpList(accessToken);
    		
    	} catch (Exception e) {  
    		e.printStackTrace();  
    	}
    }
    
    public static void main(String[] args) {  
    	//sedKeFuMsg();
    	//获得用户列表
    	//getUserList();
    	//获得用户详情
    	//getUserInfoDetail();
    	//设置备注
    	//setUserRemark();
    	//设置二维码
    	setQrcode();
  
    }
    private static void setQrcode(){
    	try {
    		StringBuffer sb = new StringBuffer();  
        	sb.append(""+
						"{\n" +
						"    \"expire_seconds\": 604800,\n" + 
						"    \"action_name\": \"QR_SCENE\",\n" + 
						"    \"action_info\": {\n" + 
						"        \"scene\": {\n" + 
						"            \"scene_id\": 448249687\n" + 
						"        }\n" + 
						"    }\n" + 
						"}"
        			);
        	// 获取accessToken -参数appid，secret    
    		String accessToken = getAccessToken(Constant.AppID, Constant.secret);  
    		System.out.println("获取的到的accessToken="+accessToken);
    		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
    		JSONObject result = msgJSONObject(sb.toString(), accessToken, url);
    		System.err.println("result="+result);
    		String ticket = result.getString("ticket");
    		String expire_seconds = result.getString("expire_seconds");
    		String urlResult = result.getString("url");
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    }
    /**
     * 设置用户备注
     */
    private static void setUserRemark(){
    	try {
    		StringBuffer sb = new StringBuffer();  
    		sb.append(""+
    				"{\n" +
    				"  \"openid\":\"oVkrAwfJNlzS509f-T6iCc2Wg9OA\",\n" + 
    				"  \"remark\":\"黄国烨_新桥镇人\"\n" + 
    				"}"
    				);
    		// 获取accessToken -参数appid，secret    
    		String accessToken = getAccessToken(Constant.AppID, Constant.secret);  
    		System.out.println("获取的到的accessToken="+accessToken);
    		String url = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=";
    		String result = msg(sb.toString(), accessToken, url);
    		System.out.println(result);
    	} catch (Exception e) {
    		// TODO: handle exception
    	}
    	
    }
    //获得用户的详细信息
    private static void getUserInfoDetail(){
    	try {
    		// 获取accessToken -参数appid，secret    
    		String accessToken = getAccessToken(Constant.AppID, Constant.secret);  
    		System.out.println("获取的到的accessToken="+accessToken);
    		String result = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/user/info?access_token="+accessToken+"&openid=oVkrAwfJNlzS509f-T6iCc2Wg9OA&lang=zh_CN", null);
    		System.out.println("result="+result);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    }
  /*  public static void main(String[] args) {  
    	
    	StringBuffer sb = new StringBuffer();  
    	sb.append("" +
    			"{\n" +
    			"     \"button\":[\n" + 
    			"     {\n" + 
    			"          \"type\":\"click\",\n" + 
    			"          \"name\":\"黄国烨\",\n" + 
    			"          \"key\":\"V1001_TODAY_MUSIC\"\n" + 
    			"      },\n" + 
    			"      {\n" + 
    			"           \"name\":\"菜单\",\n" + 
    			"           \"sub_button\":[\n" + 
    			"           {\n" + 
    			"               \"type\":\"view\",\n" + 
    			"               \"name\":\"搜索\",\n" + 
    			"               \"url\":\"http://www.soso.com/\"\n" + 
    			"            },\n" + 
    			"            {\n" + 
    			"               \"type\":\"view\",\n" + 
    			"               \"name\":\"视频\",\n" + 
    			"               \"url\":\"http://v.qq.com/\"\n" + 
    			"            },\n" + 
    			"            {\n" + 
    			"               \"type\":\"click\",\n" + 
    			"               \"name\":\"赞一下我们\",\n" + 
    			"               \"key\":\"V1001_GOOD\"\n" + 
    			"            }]\n" + 
    			"       }]\n" + 
    			" }");  
    	
    	try {  
    		// 获取accessToken -参数appid，secret    
    		String accessToken = getAccessToken(Constant.AppID, Constant.secret);  
    		System.out.println("获取的到的accessToken="+accessToken);
    		String res="";  
    		//创建菜单
    		res = createMenu(sb.toString(), accessToken);    
    		System.out.println("结束res="+res);   
    		
    		//获取微信服务号的ip地址
    		//getWeiXinIpList(accessToken);
    		
    	} catch (Exception e) {  
    		e.printStackTrace();  
    	}  
    	
    }*/
  //获取jsapi_ticket
	public static  String getJsapi_ticket(String accessToken) {
		try {
			/*HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=" + accessToken);    
	        HttpResponse response = httpclient.execute(get);    
	        String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");    
	        JSONObject demoJson = new JSONObject(jsonStr);  
	        System.out.println("请求到的jsapi_ticket=="+demoJson);
	        String errmsg = demoJson.getString("errmsg");
	        String jsapi_ticket = demoJson.getString("ticket");*/
	        return "kgt8ON7yVITDhtdwci0qeXvG0lZNexAIpCxMENygh1EQde4hopmxyRD-t47vHtVPWwXvYkq3y4O6pADkteDNVA" ;
	        //demoJson.getString("errmsg"); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}  
	//获取微信服务号的ip地址
	private static  void getWeiXinIpList(String accessToken) {
		try {
			HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=" + accessToken);    
			HttpResponse response = httpclient.execute(get);    
			String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");    
			JSONObject demoJson = new JSONObject(jsonStr);  
			System.out.println("服务号IP地址=="+demoJson);
			//demoJson.getString("errmsg"); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}  
  
}  
