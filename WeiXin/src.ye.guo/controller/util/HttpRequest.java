package controller.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 娣诲姞http璇锋眰妯″潡涓庡叾瀹冪郴缁熶氦浜�
 * 
 * add by liujinju  2014-12-01  11:58
 * */
public class HttpRequest {
    /**
     * 鍚戞寚瀹歎RL鍙戦�GET鏂规硶鐨勮姹�
     * 
     * @param url
     *            鍙戦�璇锋眰鐨刄RL
     * @param param
     *            璇锋眰鍙傛暟锛岃姹傚弬鏁板簲璇ユ槸 name1=value1&name2=value2 鐨勫舰寮忋�
     * @return URL 鎵�唬琛ㄨ繙绋嬭祫婧愮殑鍝嶅簲缁撴灉
     */
    public static String sendGet(String pathURL,Map<String,Object> param) {
        String result = "";
        BufferedReader in = null;
        try {
        	String urlNameString = null;
        	if (param == null) {
        		urlNameString = pathURL ;
			} else {
				 urlNameString = pathURL+ "?" + mapToString(param);
			}
            URL realUrl = new URL(urlNameString);
            // 鎵撳紑鍜孶RL涔嬮棿鐨勮繛鎺�
            URLConnection connection = realUrl.openConnection();
            // 璁剧疆閫氱敤鐨勮姹傚睘鎬�
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 寤虹珛瀹為檯鐨勮繛鎺�
            connection.connect();
            // 鑾峰彇鎵�湁鍝嶅簲澶村瓧娈�
            Map<String, List<String>> map = connection.getHeaderFields();
            // 閬嶅巻鎵�湁鐨勫搷搴斿ご瀛楁
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 瀹氫箟 BufferedReader杈撳叆娴佹潵璇诲彇URL鐨勫搷搴�
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        // 浣跨敤finally鍧楁潵鍏抽棴杈撳叆娴�
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 鍚戞寚瀹�URL 鍙戦�POST鏂规硶鐨勮姹�
     * 
     * @param url
     *            鍙戦�璇锋眰鐨�URL
     * @param param
     *            璇锋眰鍙傛暟锛岃姹傚弬鏁板簲璇ユ槸 name1=value1&name2=value2 鐨勫舰寮忋�
     * @return 鎵�唬琛ㄨ繙绋嬭祫婧愮殑鍝嶅簲缁撴灉
     */
    public static String sendPost(String pathURL, Map<String,Object> param) {
    	OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
        	String urlNameString = pathURL+ "?" + mapToString(param);
        	System.out.println("璇锋眰鍦板潃: ------->>>>>>>>>>>>>>>>>  "+urlNameString);
        	
            URL realUrl = new URL(pathURL);
            // 鎵撳紑鍜孶RL涔嬮棿鐨勮繛鎺�
            URLConnection conn = realUrl.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection)conn;
            // 璁剧疆閫氱敤鐨勮姹傚睘鎬�
            httpURLConnection.setRequestProperty("accept", "*/*");
            httpURLConnection.setRequestProperty("connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("contentType", "UTF-8");  
            httpURLConnection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 鍙戦�POST璇锋眰蹇呴』璁剧疆濡備笅涓よ
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 鑾峰彇URLConnection瀵硅薄瀵瑰簲鐨勮緭鍑烘祦
            out = new OutputStreamWriter(httpURLConnection.getOutputStream(),"UTF-8");
            // 鍙戦�璇锋眰鍙傛暟
            out.write(mapToString(param));
            // flush杈撳嚭娴佺殑缂撳啿
            out.flush();
            // 瀹氫箟BufferedReader杈撳叆娴佹潵璇诲彇URL鐨勫搷搴�
            in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
        	result=e.toString();
            System.out.println(e);
            e.printStackTrace();
        }
        //浣跨敤finally鍧楁潵鍏抽棴杈撳嚭娴併�杈撳叆娴�
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }  
	@SuppressWarnings("rawtypes")
	public static String mapToString(Map<String,Object> map){
	  StringBuffer stringBuffer = new StringBuffer("");
        Iterator<?> it = map.entrySet().iterator();
        while (it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (null != value) {
                stringBuffer = stringBuffer.append(key.toString() + "=").append(
                        value.toString() + "&");
            } else {
                stringBuffer = stringBuffer.append(key.toString() + "=").append("&");
            }
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return stringBuffer.toString();
    }
}