package com.example.demo.transctionstudy;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface Transaction2Dao {
    // @Param 어노테이션을 사용해 매개변수 이름을 명시적으로 지정
    void pay(@Param("consumerid") String consumerid, @Param("amount") int amount);
}
