package cn.edu.cumt.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.edu.cumt.dao.PersonDao;
import cn.edu.cumt.domain.Person;
import cn.edu.cumt.domain.Resource;
import cn.edu.cumt.domain.Role;


@SuppressWarnings("unchecked")
@Repository("personDao")
public class PersonDaoImpl extends BaseDaoImpl<Person>
	implements PersonDao 
{
	
	@Override
	public Person getByPersonId(String personId) {

		Query query = getSessionFactory().getCurrentSession()
		.createQuery("select p from Person p where personId=:personId")
		.setString("personId", personId);
		return (Person)query.uniqueResult();
	}
	public List<Person> getDepartmentsByDepartmentSn(String departmentSn){
		Query query =getSessionFactory().getCurrentSession()
				.createQuery("select p from Person p where p.department.departmentSn=:departmentSn")
				.setString("departmentSn", departmentSn);
				return (List<Person>)query.list();
	}
	@Override
	public List<Resource> getMenu(String personId, String parentResourceSn) {
		String sql;
		Query query;
		if(parentResourceSn==null){
			sql="select distinct resource.* from resource"
					+ " inner join role_resource on resource.resource_sn=role_resource.resource_sn"
					+ " inner join role on role_resource.role_sn=role.role_sn"
					+ " inner join person_role on role.role_sn=person_role.role_sn"
					+ " where resource.deleted=0 and resource.resource_type='menu'"
					+ " and parent_resource_sn is null and person_role.person_id=:person_id"
					+ " order by resource.show_sequence";
			query =getSessionFactory().getCurrentSession()
					.createSQLQuery(sql).addEntity(Resource.class).setString("person_id", personId);
		}else{
			sql="select distinct resource.* from resource"
					+ " inner join role_resource on resource.resource_sn=role_resource.resource_sn"
					+ " inner join role on role_resource.role_sn=role.role_sn"
					+ " inner join person_role on role.role_sn=person_role.role_sn"
					+ " where resource.deleted=0 and resource.resource_type='menu'"
					+ " and parent_resource_sn=:parent_resource_sn"
					+ " and person_role.person_id=:person_id order by resource.show_sequence";
			query =getSessionFactory().getCurrentSession()
					.createSQLQuery(sql).addEntity(Resource.class)
					.setString("parent_resource_sn", parentResourceSn)
					.setString("person_id", personId);
		}
		return (List<Resource>)query.list();
	}

	
	@Override
	public List<Role> getRoles(Person person) {
		String hql="select p.roles FROM Person p WHERE p.id=:id";
		Query query=getSessionFactory().getCurrentSession()
				.createQuery(hql).setInteger("id", person.getId());
		return (List<Role>)query.list();
	}
	@Override
	public HashMap<String, String> getResources(String personId) {
		String sql="select distinct resource.* FROM resource"
				+ " inner join role_resource  on resource.resource_sn=role_resource.resource_sn"
				+ " inner join person_role on role_resource.role_sn=person_role.role_sn"
				+ " WHERE person_role.person_id=:personId";
		Query query=getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Resource.class);
		query=query.setParameter("personId", personId);
		List<Resource> resources=new ArrayList<Resource>();
		resources=(List<Resource>)query.list();
		HashMap<String, String> permissions=new HashMap<String, String>();
		for(Resource resource:resources){
			permissions.put(resource.getResourceSn(), resource.getUrl());
		}
		return permissions;
	}
	@Override
	public HashMap<String, String> getRoles(String personId) {
		String hql="select r from Role r inner join r.persons p where p.personId=:personId";
		Query query=getSessionFactory().getCurrentSession().createQuery(hql);
		query=query.setParameter("personId", personId);
		List<Role> rolesList=query.list();
		HashMap<String, String> roles=new HashMap<String, String>();
		for(Role role:rolesList){
			roles.put(role.getRoleSn(), role.getRoleName());
		}
		return roles;
	}
	//hql²éÑ¯×ÜÊý
	@Override
	public Long getCountByHql(String hql) {
		return (Long) getSessionFactory().getCurrentSession()
				.createQuery(hql).uniqueResult();
	}
	@Override
	public Person getById(int id) {
		Query query = getSessionFactory().getCurrentSession()
				.createQuery("select p from Person p where p.id=:id")
				.setInteger("id", id);
				return (Person)query.uniqueResult();
	}
}