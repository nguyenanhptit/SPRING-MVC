package edu.java.spring.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.io.Files;

import edu.java.spring.dao.StudentDAO;
import edu.java.spring.model.Student;

@Controller
public class StudentController {
	// @RequestMapping(value="student/add",method = RequestMethod.GET)
	// public ModelAndView add() {
	// ModelAndView mv = new ModelAndView();
	// mv.setViewName("student.form");
	// return mv;
	//
	// }
	// @RequestMapping(value="student/save",method = RequestMethod.POST)
	// public ModelAndView save(@RequestParam(value = "name", required = false)
	// String name,
	// @RequestParam(value = "age", required = false) int age ) {
	// Student student= new Student(name, age);
	// ModelAndView mv = new ModelAndView();
	// mv.addObject("student", student);
	// mv.setViewName("student.view");
	//
	// return mv;
	//
	// }

	@RequestMapping(value = "student/add", method = RequestMethod.GET)
	public ModelAndView add() {

		return new ModelAndView("student.form", "command", new Student());
	}

	// @RequestMapping(value = "student/add", method = RequestMethod.POST)
	// public ModelAndView save(Student student) {
	// ModelAndView mv = new ModelAndView();
	// mv.addObject("student", student);
	// mv.setViewName("student.view");
	// return mv;
	//
	// }

	// @RequestMapping(value = "student/add", method = RequestMethod.POST)
	// public ModelAndView save(@Valid @ModelAttribute("command") Student student,
	// BindingResult rs) {
	// if (rs.hasErrors()) {
	// ModelAndView model = new ModelAndView("student.form", "command", student);
	// model.addObject("errors", rs);
	// return model;
	// }
	// ModelAndView mv = new ModelAndView();
	// mv.addObject("student", student);
	// mv.setViewName("student.view");
	// return mv;
	// }
	@Autowired
	private StudentDAO studentDAO;

	@RequestMapping(value = "student/add", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("command") Student student, BindingResult rs) {
		if (rs.hasErrors()) {
			ModelAndView model = new ModelAndView("student.form", "command", student);
			model.addObject("errors", rs);
			return model;
		}

		if (student.getId() > 0) {
			studentDAO.update(student);
		} else {
			studentDAO.insert(student);
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("student", student);
		mv.setViewName("student.view");
		studentDAO.insert(student);
		return new ModelAndView("redirect:/student/list");

	}

	@RequestMapping(value = "/student/list", method = RequestMethod.GET)
	public ModelAndView listStudent() {
		ModelAndView mode = new ModelAndView();
		mode.setViewName("student.list");
		mode.addObject("students", studentDAO.list());
		return mode;
	}

	@RequestMapping(value = "/student/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {
		studentDAO.delete(id);
		return ("redirect:/student/list");

	}

	@RequestMapping(value = "/student/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") int id) {
		Student student = studentDAO.get(id);
		return new ModelAndView("../student.form", "command", student);

	}

	@RequestMapping(value = "/student/edit/add", method = RequestMethod.POST)
	public String saveEdit(Student student) {
		studentDAO.update(student);
		return "redirect:/student/list";
	}

	@RequestMapping(value = "/student/json/{id}", method = RequestMethod.GET)
	public @ResponseBody Student viewJson(@PathVariable("id") int id) {
		return studentDAO.get(id);
	}

	@RequestMapping(value = "/student/search", method = RequestMethod.GET)
	public ModelAndView listStudents(@RequestParam(value = "q", required = false) String name) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("students", studentDAO.listStudents(name));
		mv.setViewName("student.list");
		return mv;
	}

	@RequestMapping(value = "/student/avatar/save", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("file") MultipartFile file, int id, HttpServletRequest rq) throws IOException {
		if (file.isEmpty())
			return "student.error";
		Path avatarPath = getImagePath(rq, id);
		byte[] bytes = file.getBytes();
		System.out.println("found--->" + bytes.length);
		java.nio.file.Files.write(avatarPath, file.getBytes(),StandardOpenOption.CREATE);

		System.out.println(avatarPath);
		return "redirect:/student/list";
	}

	private Path getImagePath(HttpServletRequest rq, int id) {
		ServletContext servletContext = rq.getSession().getServletContext();
		String diskPath = servletContext.getRealPath("/");

		File folder = new File(diskPath + File.separator + "avatar" + File.separator);
		return new File(folder, String.valueOf(id) + ".jpg").toPath();
	}

	@RequestMapping(value = "/student/avatar/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImage(@PathVariable Integer id, HttpServletRequest rq) throws IOException {

		ServletContext servletContext = rq.getSession().getServletContext();
		String absoluteDiskPath = servletContext.getRealPath("/");
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();

		File folder = new File(absoluteDiskPath + File.separator + "avatar" + File.separator);
		if ( id != null) {
			Path avataPath = getImagePath(rq, id);
			if (java.nio.file.Files.exists(avataPath))
				byteOutput.write(java.nio.file.Files.readAllBytes(avataPath));
			{
			}
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(byteOutput.toByteArray(), headers, HttpStatus.CREATED);
	}

}
