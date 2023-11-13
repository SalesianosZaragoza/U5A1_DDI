package com.in28minutes.springboot.jdbc.h2.example.repopsitory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.jdbc.h2.example.student.Student;

class Studentrowmapper implements RowMapper<Student> {
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student student = new Student();
		student.setid(rs.getLong("id"));
		student.setname(rs.getString("name"));
		student.setpassportnumber(rs.getString("passport_number"));
		return student;
	}

}

@Repository
public class StudentRepopsitory {
	@Autowired
	JdbcTemplate jdbctemplate;

	public Student findbyid(long id) {
		return jdbctemplate.queryForObject("select * from student where id=?", new Object[] { id },
				new BeanPropertyRowMapper<Student>(Student.class));
	}

	public List<Student> findall() {
		return jdbctemplate.query("select * from student", new Studentrowmapper());
	}

	public int deletebyid(long id) {
		return jdbctemplate.update("delete from student where id=?", new Object[] { id });
	}

	public int insert(Student student) {
		return jdbctemplate.update("insert into student (id, name, passport_number) " + "values(?,  ?, ?)",
				new Object[] { student.getid(), student.getname(), student.getpassportnumber() });
	}

	public int update(Student student) {
		return jdbctemplate.update("update student " + " set name = ?, passport_number = ? " + " where id = ?",
				new Object[] { student.getname(), student.getpassportnumber(), student.getid() });
	}
}
