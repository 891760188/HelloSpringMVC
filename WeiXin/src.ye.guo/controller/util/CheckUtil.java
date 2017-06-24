package controller.util;

import java.util.Arrays;



public class CheckUtil {
	private static final String token = "hgy";
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		String[] arr = new String[] { token, timestamp, nonce };
		// 排序 
		Arrays.sort(arr);
		// 生成字符串
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) { 
			content.append(arr[i]); 
		}
		// sha1加密 
		String temp = getSHA1String(content.toString());
		return temp.equals(signature); // 与微信传递过来的签名进行比较
	}
	public static String jsSign(String jsapi_ticket,String url,String timestamp,String noncestr){
		String[] arr = new String[] { jsapi_ticket, url ,timestamp, noncestr };
		// 排序 
		Arrays.sort(arr);
		// 生成字符串
		StringBuilder content = new StringBuilder();
		/*for (int i = 0; i < arr.length; i++) { 
			content.append(arr[i]); 
		}*/
		content.append("jsapi_ticket="+jsapi_ticket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+url);
		// sha1加密 
		String signature = getSHA1String(content.toString());
		return signature; // 与微信传递过来的签名进行比较
	}
	private static String getSHA1String(String data){ 
		return GetSha1.getSha1Methond(data); // 使用commons codec生成sha1字符串 }
	}
}
