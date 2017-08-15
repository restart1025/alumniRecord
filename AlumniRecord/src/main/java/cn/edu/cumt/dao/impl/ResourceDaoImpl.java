package cn.edu.cumt.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.edu.cumt.dao.ResourceDao;
import cn.edu.cumt.domain.Resource;


@SuppressWarnings("unchecked")
@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDaoImpl<Resource>
	implements ResourceDao 
{

	@Override
	public Resource getByResourceSn(String resourceSn) {
		Query query =getSessionFactory().getCurrentSession()
				.createQuery("select r from Resource r where resourceSn=:resourceSn")
				.setString("resourceSn", resourceSn);
		return (Resource)query.uniqueResult();
	}

	@Override
	public List<Resource> getMenuTypeChildren(String resourceSn) {
		Query query =getSessionFactory().getCurrentSession()
				.createQuery("select r from Resource r where parent.resourceSn=:resourceSn and resourceType='menu'")
				.setString("resourceSn", resourceSn);
		return (List<Resource>)query.list();
	}

	@Override
	public List<Resource> getResourceByParentSn(String resourceSn) {
		Query query =getSessionFactory().getCurrentSession()
				.createQuery("select r from Resource r where parent.resourceSn=:resourceSn")
				.setString("resourceSn", resourceSn);
		return (List<Resource>)query.list();
	}

	@Override
	public List<Resource> getAllMenuResources() {
		Query query =getSessionFactory().getCurrentSession()
				.createQuery("select r from Resource r where deleted=0 and resourceType='menu'");
		return (List<Resource>)query.list();
	}
}
