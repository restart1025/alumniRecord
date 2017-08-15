package cn.edu.cumt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.cumt.dao.RoleDao;
import cn.edu.cumt.domain.Person;
import cn.edu.cumt.domain.Role;
import cn.edu.cumt.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Resource(name="roleDao")
	private RoleDao roleDao;
	
	@Override
	public Role getByRoleSn(String roleSn) {
		return roleDao.getByRoleSn(roleSn);
	}

	@Override
	public List<Person> getPersonByRoleSn(String roleSn) {
		return roleDao.getPersonByRoleSn(roleSn);
	}

	@Override
	public List<Role> getByRoleTypes(String roleType) {
		return roleDao.getByRoleTypes(roleType);
	}

	@Override
	public Role getById(int roleId) {
		return roleDao.getById(roleId);
	}

	@Override
	public long getCountByHql(String hql) {
		return roleDao.getCountByHql(hql);
	}
}
