package edu.java.spring.dao.impl;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import edu.java.spring.dao.StudentDAO;
import edu.java.spring.model.Student;

@Component
public class StudentDAOImpl implements StudentDAO, DisposableBean {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	private String insertQuery;

	public final static class StudentRowMapper implements RowMapper<Student> {
		@Override
		public Student mapRow(ResultSet rs, int i) throws SQLException { // kieu tra ve
			try {
				// TODO Auto-generated method stub
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getInt("age"));
				return student;
			} catch (Exception e) {
				// TODO: handle exception

				return null;
			}
		}
	}

	public String getInsertQuery() {
		return insertQuery;
	}

	public void setInsertQuery(String insertQuery) {
		this.insertQuery = insertQuery;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@PostConstruct
	public void createTableIfNotExist() {
		try {
			DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
			ResultSet rs = dbmd.getTables(null, null, "STUDENT", null);
			if (rs.next()) {
				System.out.println("Table" + rs.getString("TABLE_NAME") + "already exists");
				return;
			}
			jdbcTemplate.execute("CREATE TABLE STUDENT("
					+ " id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY( START WITH 1, INCREMENT BY 1),"
					+ " name VARCHAR(1000)," + " age INTEGER)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void insert(Student student) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("INSERT INTO STUDENT(name,age) VALUES(?,?)", student.getName(), student.getAge());
		System.out.println("created record name = " + student.getName());
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		DriverManager.getConnection("jdbc:derby:E:/java/samplespring;shutdown=true");
	}

	@Override
	public List<Student> list() {
		// TODO Auto-generated method stub
	
		return jdbcTemplate.query("SELECT * FROM STUDENT ", new StudentRowMapper());
	}

	public int[] add(List<Student> students) {
		List<Object[]> batch = new ArrayList<Object[]>();
		students.forEach(student -> {
			batch.add(new Object[] { student.getName(), student.getAge() });// batch nhan het mang o students

		});
		return jdbcTemplate.batchUpdate(insertQuery, batch);// insert 1 mang obj
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		jdbcTemplate.execute("DELETE FROM STUDENT WHERE ID = " + id);
	}

	@Override
	public Student get(int id) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject("SELECT * FROM STUDENT WHERE ID = " + id, new StudentRowMapper());
	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("UPDATE STUDENT SET NAME =?, AGE=? WHERE ID =?", student.getName(), student.getAge(),
				student.getId());
	}

	@Override
	public List<Student> listStudents(String name) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT * FROM STUDENT WHERE NAME LIKE '%" + name + "%'", new StudentRowMapper());
	}

}
