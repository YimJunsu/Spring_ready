package com.example.demo.JDBCStudy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SimpleBbsDao implements ISimpleBbsDao{
	@Autowired JdbcTemplate jdbcTemplate;
	// 전체 조회(R)
	@Override
	public List<SimpleBbsDto> listDao() {
		System.out.println("listDao()");
		String query = "select * from simple_bbs order by id desc";
		List<SimpleBbsDto> dtos = jdbcTemplate.query(query, new BeanPropertyRowMapper<SimpleBbsDto>(SimpleBbsDto.class));
		return dtos;
	} 
	// 개별 조회 (R)
	@Override
	public SimpleBbsDto viewDao(String id) {
		System.out.println("viewDao()");
		String query = "select * from simple_bbs where id = " + id;
		SimpleBbsDto dto = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<SimpleBbsDto>(SimpleBbsDto.class));
		return dto;
	}
	// 작성(C)
	@Override
	public int writeDao(final String writer, final String title, final String content) {
		System.out.println("writeDao()");
		String query = "insert into simple_bbs (writer, title, content) " + "values (?,?,?)";
		return jdbcTemplate.update(query, writer, title, content);
	}
	// 삭제(D)
	@Override
	public int deleteDao(String id) {
		System.out.println("deleteDao()");
		
		String query = "delete from simple_bbs where id = ?";
		return jdbcTemplate.update(query, Integer.parseInt(id));
	}
}
