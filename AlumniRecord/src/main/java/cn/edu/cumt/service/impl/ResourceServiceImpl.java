package cn.edu.cumt.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.cumt.dao.ResourceDao;
import cn.edu.cumt.domain.Resource;
import cn.edu.cumt.service.ResourceService;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
	@javax.annotation.Resource(name="resourceDao")
	private ResourceDao resourceDao;

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	@Override
	public Resource getByResourceSn(String resourceSn) {
		return resourceDao.getByResourceSn(resourceSn);
	}

	@Override
	public List<Resource> getMenuTypeChildren(String resourceSn) {
		return resourceDao.getMenuTypeChildren(resourceSn);
	}

	@Override
	public List<Resource> getResourceByParentSn(String resourceSn) {
		return resourceDao.getResourceByParentSn(resourceSn);
	}

	@Override
	public List<Resource> getAllMenuResources() {
		return resourceDao.getAllMenuResources();
	}
}
