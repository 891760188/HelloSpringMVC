package com.yiibai.springmvc;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yiibai.model.Student;
import com.yiibai.util.Constant;

@Controller
public class StudentController {
	
	@RequestMapping(value="/student" , method = RequestMethod.GET)
	public ModelAndView student(){
		//因为如果在JSP中使用<form：form>标签，spring框架需要一个名为“command”的对象文件
		//return new ModelAndView(viewName, modelName, modelObject);
		return new ModelAndView("student", Constant.COMMAND, new Student( 26,  "黄国烨",  1));
	}
	
	@RequestMapping(value="/addStudet",method = RequestMethod.POST)
	public String addStudent(@ModelAttribute("SpringWeb")Student student,ModelMap model,Map<String, Object> resultMap){
//		 model.addAttribute("name", student.getName());
//	     model.addAttribute("age", student.getAge());
//	     model.addAttribute("id", student.getId());
	     resultMap.put("name", student.getName());
	     resultMap.put("age", student.getAge());
	     resultMap.put("id", student.getId());
		return "result";
	}
}
