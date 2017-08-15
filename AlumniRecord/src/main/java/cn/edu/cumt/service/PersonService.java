package cn.edu.cumt.service;

import java.util.HashMap;
import java.util.List;

import cn.edu.cumt.domain.Person;
import cn.edu.cumt.domain.Resource;
import cn.edu.cumt.domain.Role;

public interface PersonService {
	
	/**
	 * ����personId��ȡ��Ա
	 * @param personId
	 * @return
	 */
	Person getByPersonId(String personId);
	/**
	 * ��ȡ�û���Ȩ�޲˵�
	 * @param personId
	 * @param parentResourceSn
	 * @return
	 */
	List<Resource> getMenu(String personId, String parentResourceSn);
	/**
	 * ��ȡ��Ա�Ľ�ɫ
	 * @param person
	 * @return
	 */
	List<Role> getRoles(Person person);
	/**
	 * ��ȡָ����Աӵ�еĽ�ɫ
	 * @param personId
	 * @return
	 */
	HashMap<String,String> getRoles(String personId); 
	/**
	 * ����hql����ѯ����
	 * @param hql
	 * @return
	 */
	Long getCountByHql(String hql);
	/**
	 * ����id��ȡ��Ա
	 * @param id
	 * @return
	 */
	Person getById(int id);
}