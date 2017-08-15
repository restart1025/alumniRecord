package cn.edu.cumt.service;

import java.util.List;

import cn.edu.cumt.domain.Resource;


public interface ResourceService {

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
