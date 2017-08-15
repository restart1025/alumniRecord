package cn.edu.cumt.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.edu.cumt.dao.RoleDao;
import cn.edu.cumt.domain.Person;
import cn.edu.cumt.domain.Role;

@SuppressWarnings("unchecked")
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> 
	implements RoleDao 
{

	@Override
	public List<Person> getPersonByRoleSn(String roleSn) {
		String hql="select r.persons FROM Role r WHERE r.roleSn=:roleSn";
		Query query=getSessionFactory().getCurrentSession().createQuery(hql)
				.setString("roleSn", roleSn);
		return (List<Person>)query.list();
	}
	@Override
	public Role getByRoleSn(String roleSn) {
		String hql="select r FROM Role r WHERE r.deleted=false and r.roleSn=:roleSn";
		Query query=getSessionFactory().getCurrentSession().createQuery(hql)
				.setString("roleSn", roleSn);
		return (Role)query.uniqueResult();
	}

	@Override
	public List<Role> getByRoleTypes(String roleType) {
		String hql="select r from Role r where r.deleted=false and r.roleType in :roleType";
		Query query=getSessionFactory().getCurrentSession().createQuery(hql)
				.setParameter("roleType", roleType);
		return (List<Role>)query.list();
	}	
	@Override
	public Role getById(int roleId) {
		String hql="select r FROM Role r WHERE r.id= :roleId";
		Query query=getSessionFactory().getCurrentSession().createQuery(hql)
				.setInteger("roleId", roleId);
		return (Role)query.uniqueResult();
	}
	@Override
	public long getCountByHql(String hql) {
		return(Long)getSessionFactory().getCurrentSession().createQuery(hql)
				.uniqueResult();
	}
}
