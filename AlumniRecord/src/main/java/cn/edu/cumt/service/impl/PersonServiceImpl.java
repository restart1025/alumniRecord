package cn.edu.cumt.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.cumt.dao.PersonDao;
import cn.edu.cumt.domain.Person;
import cn.edu.cumt.domain.Resource;
import cn.edu.cumt.domain.Role;
import cn.edu.cumt.service.PersonService;

@Service("personService")
public class PersonServiceImpl implements PersonService {
	
	@javax.annotation.Resource(name="personDao")	
	private PersonDao personDao;
	
	@Override
	public Person getByPersonId(String personId) {
		return personDao.getByPersonId(personId);
	}

	@Override
	public List<Resource> getMenu(String personId, String parentResourceSn) {
		return personDao.getMenu(personId, parentResourceSn);
	}

	@Override
	public List<Role> getRoles(Person person) {
		return personDao.getRoles(person);
	}

	@Override
	public HashMap<String, String> getRoles(String personId) {
		return personDao.getRoles(personId);
	}

	@Override
	public Long getCountByHql(String hql) {
		return personDao.getCountByHql(hql);
	}

	@Override
	public Person getById(int id) {
		return personDao.getById(id);
	}

}
