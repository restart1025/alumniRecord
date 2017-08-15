package cn.edu.cumt.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.cumt.dao.NoticeDao;
import cn.edu.cumt.domain.Notice;
import cn.edu.cumt.domain.NoticeAttachment;
import cn.edu.cumt.service.NoticeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

	@Resource(name="noticeDao")
	private NoticeDao noticeDao;

	@Override
	public JSONObject showDataByPage(Integer page, Integer rows) {
		return noticeDao.showDataByPage(page, rows);
	}

	@Override
	public JSONObject showMyDataByPage(String personId, Integer page, Integer rows) {
		return noticeDao.showMyDataByPage(personId, page, rows);
	}

	@Override
	public void add(Notice notice) {
		noticeDao.save(notice);
	}

	@Override
	public void update(Notice notice) {
		noticeDao.update(notice);
	}

	@Override
	public void delete(Integer id) {
		Notice notice = noticeDao.get(Notice.class, id);
		notice.setDeleted(true);
		noticeDao.update(notice);
	}

	@Override
	public Notice getById(Integer id) {
		return noticeDao.get(Notice.class, id);
	}

	@Override
	public JSONObject showAttachment(int id) {
		
		Notice notice = noticeDao.get(Notice.class, id);
		
		int total = 0;
		JSONArray ja = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		
		if( notice.getAttachments() != null ){
			for( NoticeAttachment noticeAttachment : notice.getAttachments() ){
				if( !noticeAttachment.getDeleted() ){
					JSONObject jo = new JSONObject();
					jo.put("id", noticeAttachment.getId());
					jo.put("attachmentType", noticeAttachment.getAttachmentType());
					jo.put("logicalFileName", noticeAttachment.getLogicalFileName());
					jo.put("physicalFileName", noticeAttachment.getPhysicalFileName());
					ja.add(jo);
					total++;
				}
			}
		}
		jsonObject.put("rows", ja);
		jsonObject.put("total", total);
		return jsonObject;
	}
	
}
