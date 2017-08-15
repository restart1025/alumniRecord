package cn.edu.cumt.service;

import java.util.HashMap;
import java.util.List;

import cn.edu.cumt.domain.Person;
import cn.edu.cumt.domain.Resource;
import cn.edu.cumt.domain.Role;

public interface PersonService {
	
	/**
	 * 根据personId获取人员
	 * @param personId
	 * @return
	 */
	Person getByPersonId(String personId);
	/**
	 * 获取用户的权限菜单
	 * @param personId
	 * @param parentResourceSn
	 * @return
	 */
	List<Resource> getMenu(String personId, String parentResourceSn);
	/**
	 * 获取人员的角色
	 * @param person
	 * @return
	 */
	List<Role> getRoles(Person person);
	/**
	 * 获取指定人员拥有的角色
	 * @param personId
	 * @return
	 */
	HashMap<String,String> getRoles(String personId); 
	/**
	 * 根据hql语句查询总数
	 * @param hql
	 * @return
	 */
	Long getCountByHql(String hql);
	/**
	 * 根据id获取人员
	 * @param id
	 * @return
	 */
	Person getById(int id);
}