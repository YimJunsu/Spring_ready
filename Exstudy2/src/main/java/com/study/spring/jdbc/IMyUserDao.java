package com.study.spring.jdbc;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper  // MyBatis에서 Mapper 인터페이스로 인식하도록 지정
public interface IMyUserDao {
    List<MyUserDto> list();
}
