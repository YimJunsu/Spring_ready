package com.example.demo.JDBCStudy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// @Repository 어노테이션 사용해 빈으로 등록, @Repository 어노테이션은 스테레오 타입의 일종
// 스테레오 타입 : 내부적으로 의미 파악을 쉽게 하기 위해서 사용하는 별칭
@Repository
public class UserDao {
	@Autowired private JdbcTemplate jdbcTemplate;
	
	public List<UserDto> list(){
		String query="select * from myuser";
		List<UserDto> list = jdbcTemplate.query(query, new BeanPropertyRowMapper<UserDto>(UserDto.class));
		for(UserDto ud : list) {
			System.out.println(ud);
		}
		return list;
	}
}
