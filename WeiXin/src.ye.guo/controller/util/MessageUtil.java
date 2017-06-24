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
	 * 将XML转换成Map集合
	 * @param request
	 * @return
	 */
	public static Map<String, String>xmlToMap(HttpServletRequest request){ 
		try {
			Map<String, String> map = new HashMap<String, String>(); 
			SAXReader reader = new SAXReader(); // 使用dom4j解析xml
			InputStream ins;
			ins = request.getInputStream();
			Document doc = reader.read(ins); 
			Element root = doc.getRootElement(); 
			// 获取根元素 
			List<Element> list = root.elements(); // 获取所有节点
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
		// 从request中获取输入流
		
		return null;
	}
	
	/** * 将文本消息对象转换成XML */ 
	public static String textMessageToXML(TextMeaasge textMessage){
		XStream xstream = new XStream(); // 使用XStream将实体类的实例转换成xml格式  
		xstream.alias("xml", textMessage.getClass()); // 将xml的默认根节点替换成“xml” 
		return xstream.toXML(textMessage);  
	}
	/** * 将文本消息对象转换成XML */ 
	public static String messageToXML(Class<?> classes,Object obj){
		XStream xstream = new XStream(); // 使用XStream将实体类的实例转换成xml格式  
		xstream.alias("xml", classes); // 将xml的默认根节点替换成“xml” 
		return xstream.toXML(obj);  
	}
	
}
