package cn.edu.cumt.service;

import cn.edu.cumt.domain.Person;

/**
 * Book服务层接口
 * */
public interface PersonService {
	
	/**
	 * 根据personId查找人员
	 * @return Person对象
	 * */
	Person getByPersonId(String personId);

}
