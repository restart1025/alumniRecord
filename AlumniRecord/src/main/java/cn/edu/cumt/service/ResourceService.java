package cn.edu.cumt.service;

import java.util.List;

import cn.edu.cumt.domain.Resource;


public interface ResourceService {

	Resource getByResourceSn(String resourceSn);
	/**
	 * 获取资源类型为menu的子项
	 * @param resourceSn
	 * @return
	 */
	List<Resource> getMenuTypeChildren(String resourceSn);
	/**
	 * 根据父级权限获取子类权限
	 * @param resourceSn
	 * @return
	 */
	List<Resource> getResourceByParentSn(String resourceSn);
	/**
	 * 获取所有的菜单资源项
	 * @return
	 */
	List<Resource> getAllMenuResources();
}
