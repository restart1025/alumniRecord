package cn.edu.cumt.dao;

import cn.edu.cumt.domain.Notice;
import net.sf.json.JSONObject;

public interface NoticeDao extends BaseDao<Notice>{

	/**
	 * ��ҳ��ѯ����
	 * @param page
	 * @param rows
	 * @return
	 */
	JSONObject showDataByPage(Integer page, Integer rows);
	/**
	 * ��ҳ��ѯ�ҵ�����
	 * @param page
	 * @param rows
	 * @return
	 */
	JSONObject showMyDataByPage(String personId, Integer page, Integer rows);
	
}
