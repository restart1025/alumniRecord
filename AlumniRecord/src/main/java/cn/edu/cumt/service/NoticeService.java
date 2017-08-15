package cn.edu.cumt.service;

import cn.edu.cumt.domain.Notice;
import net.sf.json.JSONObject;

public interface NoticeService {

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
	/**
	 * ��������
	 * @param notice
	 */
	void add(Notice notice);
	/**
	 * �޸Ĺ���
	 * @param notice
	 */
	void update(Notice notice);
	/**
	 * ɾ������
	 * @param id
	 */
	void delete(Integer id);
	/**
	 * ����id��ѯʵ��
	 * @param id
	 * @return
	 */
	Notice getById(Integer id);
	/**
	 * չʾ������������
	 * @param id
	 * @return
	 */
	JSONObject showAttachment(int id);
}
