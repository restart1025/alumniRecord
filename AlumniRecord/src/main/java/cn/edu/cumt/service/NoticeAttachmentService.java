package cn.edu.cumt.service;

import cn.edu.cumt.domain.NoticeAttachment;

public interface NoticeAttachmentService {

	/**
	 * ���¹��渽��
	 * @param noticeAttachment
	 */
	void update(NoticeAttachment noticeAttachment);

	/**
	 * ���ӹ��渽��
	 * @param noticeAttachment
	 */
	void add(NoticeAttachment noticeAttachment);
	/**
	 * ����id���Ҹ���
	 * @param id
	 * @return
	 */
	NoticeAttachment getById(int id);

}
