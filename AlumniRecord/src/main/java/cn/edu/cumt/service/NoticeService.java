package cn.edu.cumt.service;

import cn.edu.cumt.domain.Notice;
import net.sf.json.JSONObject;

public interface NoticeService {

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
	/**
	 * 新增公告
	 * @param notice
	 */
	void add(Notice notice);
	/**
	 * 修改公告
	 * @param notice
	 */
	void update(Notice notice);
	/**
	 * 删除公告
	 * @param id
	 */
	void delete(Integer id);
	/**
	 * 根据id查询实体
	 * @param id
	 * @return
	 */
	Notice getById(Integer id);
	/**
	 * 展示公告所属附件
	 * @param id
	 * @return
	 */
	JSONObject showAttachment(int id);
}
