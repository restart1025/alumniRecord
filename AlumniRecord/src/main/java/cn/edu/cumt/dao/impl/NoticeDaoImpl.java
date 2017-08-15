package cn.edu.cumt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.edu.cumt.dao.NoticeDao;
import cn.edu.cumt.domain.Notice;
import cn.edu.cumt.domain.NoticeAttachment;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository("noticeDao")
public class NoticeDaoImpl extends BaseDaoImpl<Notice> implements NoticeDao {

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject showDataByPage(Integer page, Integer rows) {
		
		String hql = "SELECT n FROM Notice n WHERE n.deleted != true Order By n.dateTime desc";
		
		List<Notice> notices =  getSessionFactory().getCurrentSession()
				.createQuery(hql).list();
		
		JSONObject jsonObject = new JSONObject();
		JSONArray ja = new JSONArray();
//		int num = 0;
		
		for( Notice notice : notices )
		{
			JSONObject jo = new JSONObject();
			jo.put("id", notice.getId());
			jo.put("title", notice.getTitle());
			jo.put("author", notice.getAuthor().getPersonName());
			jo.put("dateTime", notice.getDateTime().toString());
			jo.put("content", notice.getContent());
//			num = 0;
			
			JSONArray jay = new JSONArray();
			for( NoticeAttachment noticeAttachment : notice.getAttachments() )
			{
				JSONObject jot = new JSONObject();
				if( !noticeAttachment.getDeleted() )
				{
					jot.put("logicalFileName", noticeAttachment.getLogicalFileName());
					jot.put("physicalFileName", noticeAttachment.getPhysicalFileName());
					jay.add(jot);
				}
			}
			
			if( jay.size() > 0 )
			{
				jo.put("attachment", jay);
			}else{
				jo.put("attachment", "no");
			}
			ja.add(jo);
		}
		
		hql = "SELECT count(n) FROM Notice n WHERE n.deleted != true"
				+ " Order By n.dateTime desc";
		
		long total =  (long) getSessionFactory().getCurrentSession()
				.createQuery(hql).uniqueResult();
		
		jsonObject.put("rows", ja);
		jsonObject.put("total", total);
		
		return jsonObject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject showMyDataByPage(String personId, Integer page, Integer rows) {
		
		String hql = "SELECT n FROM Notice n"
				+ " WHERE n.author.personId = :personId AND n.deleted != true"
				+ " Order By n.dateTime desc";
		
		List<Notice> notices =  getSessionFactory().getCurrentSession()
				.createQuery(hql).setString("personId", personId).list();
		
		JSONObject jsonObject = new JSONObject();
		JSONArray ja = new JSONArray();
		int num = 0;
		
		for( Notice notice : notices )
		{
			JSONObject jo = new JSONObject();
			jo.put("id", notice.getId());
			jo.put("title", notice.getTitle());
			jo.put("author", notice.getAuthor().getPersonName());
			jo.put("dateTime", notice.getDateTime().toString());
			jo.put("content", notice.getContent());
			num = 0;
			for( NoticeAttachment noticeAttachment : notice.getAttachments() )
			{
				if( !noticeAttachment.getDeleted() )
				{
					num++;
				}
			}
			jo.put("attachment", num);
			ja.add(jo);
		}
		
		hql = "SELECT count(n) FROM Notice n"
				+ " WHERE n.author.personId = :personId AND n.deleted != true"
				+ " Order By n.dateTime desc";
		
		long total =  (long) getSessionFactory().getCurrentSession()
				.createQuery(hql).setString("personId", personId).uniqueResult();
		
		jsonObject.put("rows", ja);
		jsonObject.put("total", total);
		
		return jsonObject;
	}


}
