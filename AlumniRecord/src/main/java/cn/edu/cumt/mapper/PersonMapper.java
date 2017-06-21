package cn.edu.cumt.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.edu.cumt.domain.Person;

/**
 * BookMapper接口
 * */
public interface PersonMapper {

	/**
	 * 根据personId查找人员
	 * @return 人员对象
	 * */
	@Select(" SELECT * FROM person WHERE person_id = #{personId} ")
	Person getByPersonId(@Param("personId")String personId);
	
}
