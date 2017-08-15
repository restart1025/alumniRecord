package cn.edu.cumt.service;

import java.util.List;

import cn.edu.cumt.domain.Person;
import cn.edu.cumt.domain.Role;

public interface RoleService {
	
	/**
	 * ���ݽ�ɫ��ȡ��Ա
	 * @param roleSn
	 * @return
	 */
	List<Person> getPersonByRoleSn(String roleSn);
	/**
	 * ���ݽ�ɫ��Ż�ȡ��ɫ
	 * @param roleSn
	 * @return
	 */
	Role getByRoleSn(String roleSn);

	/**
	 * ���ݽ�ɫ���ͻ�ȡ��ɫ
	 * @param roleType
	 * @return
	 */
	List<Role> getByRoleTypes(String roleType);
	/**
	 * ����id��ȡ��ɫ
	 * @param roleId
	 * @return
	 */
	Role getById(int roleId);
	/**
	 * ����hql��ȡ����
	 * @param hql
	 * @return
	 */
	long getCountByHql(String hql);
	
}
