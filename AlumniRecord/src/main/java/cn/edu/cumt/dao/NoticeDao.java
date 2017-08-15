package cn.edu.cumt.dao;

import cn.edu.cumt.domain.Notice;
import net.sf.json.JSONObject;

public interface NoticeDao extends BaseDao<Notice>{

	/**
	 * 分页查询数据
	 * @param page
	 * @param rows
	 * @return
	 */
	JSONObject showDataByPage(Integer page, Integer rows);
	/**
	 * 分页查询我的数据
	 * @param page
	 * @param rows
	 * @return
	 */
	JSONObject showMyDataByPage(String personId, Integer page, Integer rows);
	
}
