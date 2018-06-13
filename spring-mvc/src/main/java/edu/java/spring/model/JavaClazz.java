package edu.java.spring.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="clazz")
public class JavaClazz {

private List<Student> students;
	
@XmlElements(@XmlElement(name="student", type=Student.class))
public List<Student> getStudents(){
	return students;
}

public void setStudents(List<Student> students) {
	this.students = students;
}

public JavaClazz() {
	super();
	// TODO Auto-generated constructor stub
}

public JavaClazz(List<Student> students) {
	super();
	this.students = students;
}
}
