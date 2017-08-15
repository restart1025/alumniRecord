package cn.edu.cumt.service;

import cn.edu.cumt.domain.NoticeAttachment;

public interface NoticeAttachmentService {

	/**
	 * 更新公告附件
	 * @param noticeAttachment
	 */
	void update(NoticeAttachment noticeAttachment);

	/**
	 * 增加公告附件
	 * @param noticeAttachment
	 */
	void add(NoticeAttachment noticeAttachment);
	/**
	 * 根据id查找附件
	 * @param id
	 * @return
	 */
	NoticeAttachment getById(int id);

}
