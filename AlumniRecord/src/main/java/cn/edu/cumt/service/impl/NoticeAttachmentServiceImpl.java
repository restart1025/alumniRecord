package cn.edu.cumt.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.cumt.dao.NoticeAttachmentDao;
import cn.edu.cumt.domain.NoticeAttachment;
import cn.edu.cumt.service.NoticeAttachmentService;


@Service("noticeAttachmentService")
public class NoticeAttachmentServiceImpl implements NoticeAttachmentService {

	@Resource(name="noticeAttachmentDao")
	private NoticeAttachmentDao noticeAttachmentDao;

	@Override
	public void update(NoticeAttachment noticeAttachment) {
		noticeAttachmentDao.update(noticeAttachment);
	}

	@Override
	public void add(NoticeAttachment noticeAttachment) {
		noticeAttachmentDao.save(noticeAttachment);
	}

	@Override
	public NoticeAttachment getById(int id) {
		return noticeAttachmentDao.get(NoticeAttachment.class, id);
	}
	
}
