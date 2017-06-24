package controller.util;

import entity.Entity;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonDemo {
	public static void main(String[] args) {
		
		
		String str = ""+
			"{\n" +
			"    \"status\": 200,\n" + 
			"    \"message\": \"��ѯ�ɹ�\",\n" + 
			"    \"data\": [\n" + 
			"        {\n" + 
			"            \"id\": 1,\n" + 
			"            \"name\": \"name1\",\n" + 
			"            \"describe\": \"��һ������\"\n" + 
			"        }\n" + 
			"    ]\n" + 
			"}";

		JSONObject jsonobject = JSONObject.fromObject(str);
		 Entity entity = (Entity)JSONObject.toBean(jsonobject, Entity.class);
		System.out.println(jsonobject);
	}
}
