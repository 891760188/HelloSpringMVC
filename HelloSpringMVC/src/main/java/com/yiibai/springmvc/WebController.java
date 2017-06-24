package com.yiibai.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {
	
	  @RequestMapping(value = "/staticPage", method = RequestMethod.GET)
	   public String redirect() {
	      return "redirect:/assets/final.html";
	   }
}
