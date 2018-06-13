package edu.java.spring.dao;

import java.util.List;

import edu.java.spring.model.Student;

public interface StudentDAO {
	public void insert(Student student);

	public void createTableIfNotExist();

	public List<Student> list();
	
	public void delete(int id);
	
	public Student get(int id);
	
	public void update(Student student);
	
	public List<Student> listStudents(String name);

	
}