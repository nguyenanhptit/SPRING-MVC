package edu.java.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

@Controller
@RequestMapping("/hello")
public class HelloClazzController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView printMessage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		mv.addObject("message", "Hello Java Clazz ");
		return mv;

	}

	@RequestMapping(value = "welcome", method = RequestMethod.GET)
	public ModelAndView welcome() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/clazz");
		mv.addObject("name", "bideptrai");
		return mv;
	}

	@RequestMapping(value = "site", method = RequestMethod.GET)
	public String redirect() {

		return "redirect:http://google.com";

	}

	@RequestMapping(value = "data", method = RequestMethod.GET)
	public @ResponseBody String raw() {

		return "xin chao moi nguoi";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView printMessage(@RequestParam(value = "data", required = false) String message) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		mv.addObject("message", message);
		return mv;

	}

}
