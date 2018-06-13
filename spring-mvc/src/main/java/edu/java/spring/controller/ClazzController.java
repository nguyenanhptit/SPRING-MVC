package edu.java.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.java.spring.dao.StudentDAO;
import edu.java.spring.model.JavaClazz;
import edu.java.spring.utils.XSLTUtils;
@Controller
public class ClazzController {

	@Autowired
	private StudentDAO studentDAO;

	@RequestMapping(value = "/clazz/xml", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody JavaClazz viewInXML() {
		return new JavaClazz(studentDAO.list());
	}
	
	@RequestMapping(value = "/clazz/json", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody JavaClazz viewInJSON() {
		return new JavaClazz(studentDAO.list());
	}
	@RequestMapping(value = "/clazz/xslt", method = RequestMethod.GET)
		public ModelAndView viewXSLT() {
			JavaClazz clazz = new JavaClazz(studentDAO.list());
			ModelAndView model= new ModelAndView();
			model.setViewName("ClazzView");
			model.getModelMap().put("data", XSLTUtils.clazzToDomSource(clazz));
			return model;
		}
	@RequestMapping(value = "/clazz/excel", method = RequestMethod.GET)
	public ModelAndView viewExcel() {
		JavaClazz clazz = new JavaClazz(studentDAO.list());
		ModelAndView model= new ModelAndView();
		model.setViewName("excelView");
		model.getModelMap().put("clazzObj",clazz );
		return model;
}
}
