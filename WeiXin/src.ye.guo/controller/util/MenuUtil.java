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
      
    //http�ͻ���    
    public static DefaultHttpClient httpclient;  
      
    static {    
        httpclient = new DefaultHttpClient();    
        httpclient = (DefaultHttpClient) HttpClientConnectionManager.getSSLInstance(httpclient); // �����κ�֤���������ͻ���  
        APPID = "";//���APPID  
        APPSECRET = ""; //���APPSECRET  
    }    
      
    /** 
     * �����˵�  
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
     * ��ȡaccessToken  
     */  
    public static String getAccessToken(String appid, String secret) throws Exception {    
        HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret);    
        HttpResponse response = httpclient.execute(get);    
        String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");  
        JSONObject demoJson = new JSONObject(jsonStr);  
        return demoJson.getString("access_token");  
    }    
    /** 
     * ��ѯ�˵� 
     */  
    public static String getMenuInfo(String accessToken) throws Exception {    
        HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken);    
        HttpResponse response = httpclient.execute(get);    
        String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");    
        return jsonStr;    
    }    
      
    /**  
     * ɾ���˵�  
     */    
    public static String deleteMenuInfo(String accessToken) throws Exception {    
        HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accessToken);    
        HttpResponse response = httpclient.execute(get);    
        String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");    
        JSONObject demoJson = new JSONObject(jsonStr);  
        return demoJson.getString("errmsg");    
    }    
      
   /* 
    * ����   ���Ϳͷ���Ϣ-�ı���Ϣ
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
            // ��ȡaccessToken -����appid��secret    
            String accessToken = getAccessToken(Constant.AppID, Constant.secret);  
            System.out.println("��ȡ�ĵ���accessToken="+accessToken);
            String res="";  
            //�����˵�
           res = msg(sb.toString(), accessToken , "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=");    
           System.out.println("����res="+res);   
            
            //��ȡ΢�ŷ���ŵ�ip��ַ
            //getWeiXinIpList(accessToken);
            
        } catch (Exception e) {  
            e.printStackTrace();  
        }
    }
    public static void getUserList(){
    
    	
    	try {  
    		// ��ȡaccessToken -����appid��secret    
    		String accessToken = getAccessToken(Constant.AppID, Constant.secret);  
    		System.out.println("��ȡ�ĵ���accessToken="+accessToken);
    		String res="";  
    		//�����˵�
    		Map<String, Object> map = new HashMap<String, Object>();
    		res = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/user/get?access_token="+accessToken+"&next_openid=",null);
    		System.out.println("����res="+res);   
    		
    		//��ȡ΢�ŷ���ŵ�ip��ַ
    		//getWeiXinIpList(accessToken);
    		
    	} catch (Exception e) {  
    		e.printStackTrace();  
    	}
    }
    
    public static void main(String[] args) {  
    	//sedKeFuMsg();
    	//����û��б�
    	//getUserList();
    	//����û�����
    	//getUserInfoDetail();
    	//���ñ�ע
    	//setUserRemark();
    	//���ö�ά��
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
        	// ��ȡaccessToken -����appid��secret    
    		String accessToken = getAccessToken(Constant.AppID, Constant.secret);  
    		System.out.println("��ȡ�ĵ���accessToken="+accessToken);
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
     * �����û���ע
     */
    private static void setUserRemark(){
    	try {
    		StringBuffer sb = new StringBuffer();  
    		sb.append(""+
    				"{\n" +
    				"  \"openid\":\"oVkrAwfJNlzS509f-T6iCc2Wg9OA\",\n" + 
    				"  \"remark\":\"�ƹ���_��������\"\n" + 
    				"}"
    				);
    		// ��ȡaccessToken -����appid��secret    
    		String accessToken = getAccessToken(Constant.AppID, Constant.secret);  
    		System.out.println("��ȡ�ĵ���accessToken="+accessToken);
    		String url = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=";
    		String result = msg(sb.toString(), accessToken, url);
    		System.out.println(result);
    	} catch (Exception e) {
    		// TODO: handle exception
    	}
    	
    }
    //����û�����ϸ��Ϣ
    private static void getUserInfoDetail(){
    	try {
    		// ��ȡaccessToken -����appid��secret    
    		String accessToken = getAccessToken(Constant.AppID, Constant.secret);  
    		System.out.println("��ȡ�ĵ���accessToken="+accessToken);
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
    			"          \"name\":\"�ƹ���\",\n" + 
    			"          \"key\":\"V1001_TODAY_MUSIC\"\n" + 
    			"      },\n" + 
    			"      {\n" + 
    			"           \"name\":\"�˵�\",\n" + 
    			"           \"sub_button\":[\n" + 
    			"           {\n" + 
    			"               \"type\":\"view\",\n" + 
    			"               \"name\":\"����\",\n" + 
    			"               \"url\":\"http://www.soso.com/\"\n" + 
    			"            },\n" + 
    			"            {\n" + 
    			"               \"type\":\"view\",\n" + 
    			"               \"name\":\"��Ƶ\",\n" + 
    			"               \"url\":\"http://v.qq.com/\"\n" + 
    			"            },\n" + 
    			"            {\n" + 
    			"               \"type\":\"click\",\n" + 
    			"               \"name\":\"��һ������\",\n" + 
    			"               \"key\":\"V1001_GOOD\"\n" + 
    			"            }]\n" + 
    			"       }]\n" + 
    			" }");  
    	
    	try {  
    		// ��ȡaccessToken -����appid��secret    
    		String accessToken = getAccessToken(Constant.AppID, Constant.secret);  
    		System.out.println("��ȡ�ĵ���accessToken="+accessToken);
    		String res="";  
    		//�����˵�
    		res = createMenu(sb.toString(), accessToken);    
    		System.out.println("����res="+res);   
    		
    		//��ȡ΢�ŷ���ŵ�ip��ַ
    		//getWeiXinIpList(accessToken);
    		
    	} catch (Exception e) {  
    		e.printStackTrace();  
    	}  
    	
    }*/
  //��ȡjsapi_ticket
	public static  String getJsapi_ticket(String accessToken) {
		try {
			/*HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=" + accessToken);    
	        HttpResponse response = httpclient.execute(get);    
	        String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");    
	        JSONObject demoJson = new JSONObject(jsonStr);  
	        System.out.println("���󵽵�jsapi_ticket=="+demoJson);
	        String errmsg = demoJson.getString("errmsg");
	        String jsapi_ticket = demoJson.getString("ticket");*/
	        return "kgt8ON7yVITDhtdwci0qeXvG0lZNexAIpCxMENygh1EQde4hopmxyRD-t47vHtVPWwXvYkq3y4O6pADkteDNVA" ;
	        //demoJson.getString("errmsg"); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}  
	//��ȡ΢�ŷ���ŵ�ip��ַ
	private static  void getWeiXinIpList(String accessToken) {
		try {
			HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=" + accessToken);    
			HttpResponse response = httpclient.execute(get);    
			String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");    
			JSONObject demoJson = new JSONObject(jsonStr);  
			System.out.println("�����IP��ַ=="+demoJson);
			//demoJson.getString("errmsg"); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}  
  
}  
