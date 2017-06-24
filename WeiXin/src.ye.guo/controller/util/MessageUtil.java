package controller.util;

import java.io.IOException; 
import java.io.InputStream; 
import java.util.HashMap; 
import java.util.List; 
import java.util.Map;  
import javax.servlet.http.HttpServletRequest;  
import org.dom4j.Document; 
import org.dom4j.DocumentException; 
import org.dom4j.Element; 
import org.dom4j.io.SAXReader; 
import com.thoughtworks.xstream.XStream;

import entity.TextMeaasge;

public class MessageUtil {
	/**
	 * ��XMLת����Map����
	 * @param request
	 * @return
	 */
	public static Map<String, String>xmlToMap(HttpServletRequest request){ 
		try {
			Map<String, String> map = new HashMap<String, String>(); 
			SAXReader reader = new SAXReader(); // ʹ��dom4j����xml
			InputStream ins;
			ins = request.getInputStream();
			Document doc = reader.read(ins); 
			Element root = doc.getRootElement(); 
			// ��ȡ��Ԫ�� 
			List<Element> list = root.elements(); // ��ȡ���нڵ�
			for (Element e : list) { 
				map.put(e.getName(), e.getText());  
				System.out.println(e.getName() + "--->" + e.getText()); 
			}
			ins.close();
			return map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		// ��request�л�ȡ������
		
		return null;
	}
	
	/** * ���ı���Ϣ����ת����XML */ 
	public static String textMessageToXML(TextMeaasge textMessage){
		XStream xstream = new XStream(); // ʹ��XStream��ʵ�����ʵ��ת����xml��ʽ  
		xstream.alias("xml", textMessage.getClass()); // ��xml��Ĭ�ϸ��ڵ��滻�ɡ�xml�� 
		return xstream.toXML(textMessage);  
	}
	/** * ���ı���Ϣ����ת����XML */ 
	public static String messageToXML(Class<?> classes,Object obj){
		XStream xstream = new XStream(); // ʹ��XStream��ʵ�����ʵ��ת����xml��ʽ  
		xstream.alias("xml", classes); // ��xml��Ĭ�ϸ��ڵ��滻�ɡ�xml�� 
		return xstream.toXML(obj);  
	}
	
}
