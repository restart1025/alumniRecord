package cn.edu.cumt.service;

import java.util.List;

import cn.edu.cumt.domain.Person;
import cn.edu.cumt.domain.Role;

public interface RoleService {
	
	/**
	 * 根据角色获取人员
	 * @param roleSn
	 * @return
	 */
	List<Person> getPersonByRoleSn(String roleSn);
	/**
	 * 根据角色编号获取角色
	 * @param roleSn
	 * @return
	 */
	Role getByRoleSn(String roleSn);

	/**
	 * 根据角色类型获取角色
	 * @param roleType
	 * @return
	 */
	List<Role> getByRoleTypes(String roleType);
	/**
	 * 根据id获取角色
	 * @param roleId
	 * @return
	 */
	Role getById(int roleId);
	/**
	 * 根据hql获取总数
	 * @param hql
	 * @return
	 */
	long getCountByHql(String hql);
	
}
