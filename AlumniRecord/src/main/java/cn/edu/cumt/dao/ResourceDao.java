package cn.edu.cumt.dao;

import java.util.List;

import cn.edu.cumt.domain.Resource;

public interface ResourceDao extends BaseDao<Resource> {

	Resource getByResourceSn(String resourceSn);
	/**
	 * ��ȡ��Դ����Ϊmenu������
	 * @param resourceSn
	 * @return
	 */
	List<Resource> getMenuTypeChildren(String resourceSn);
	/**
	 * ���ݸ���Ȩ�޻�ȡ����Ȩ��
	 * @param resourceSn
	 * @return
	 */
	List<Resource> getResourceByParentSn(String resourceSn);
	/**
	 * ��ȡ���еĲ˵���Դ��
	 * @return
	 */
	List<Resource> getAllMenuResources();
}
