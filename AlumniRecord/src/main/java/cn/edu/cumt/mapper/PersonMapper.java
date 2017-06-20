package cn.edu.cumt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.edu.cumt.domain.Person;

/**
 * BookMapper接口
 * */
public interface PersonMapper {

	/**
	 * 查询所有图书
	 * @return 图书对象集合
	 * */
	@Select(" select * from tb_book ")
	List<Person> findAll();
	
}
