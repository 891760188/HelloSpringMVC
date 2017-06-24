package controller.util;

import java.util.Arrays;



public class CheckUtil {
	private static final String token = "hgy";
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		String[] arr = new String[] { token, timestamp, nonce };
		// ���� 
		Arrays.sort(arr);
		// �����ַ���
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) { 
			content.append(arr[i]); 
		}
		// sha1���� 
		String temp = getSHA1String(content.toString());
		return temp.equals(signature); // ��΢�Ŵ��ݹ�����ǩ�����бȽ�
	}
	public static String jsSign(String jsapi_ticket,String url,String timestamp,String noncestr){
		String[] arr = new String[] { jsapi_ticket, url ,timestamp, noncestr };
		// ���� 
		Arrays.sort(arr);
		// �����ַ���
		StringBuilder content = new StringBuilder();
		/*for (int i = 0; i < arr.length; i++) { 
			content.append(arr[i]); 
		}*/
		content.append("jsapi_ticket="+jsapi_ticket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+url);
		// sha1���� 
		String signature = getSHA1String(content.toString());
		return signature; // ��΢�Ŵ��ݹ�����ǩ�����бȽ�
	}
	private static String getSHA1String(String data){ 
		return GetSha1.getSha1Methond(data); // ʹ��commons codec����sha1�ַ��� }
	}
}
