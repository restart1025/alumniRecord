package cn.edu.cumt.domain;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class NoticeAttachment extends FileAttachment {

	private static final long serialVersionUID = 1L;
	private Notice notice;//对应的公告
	
	@ManyToOne(targetEntity=Notice.class,cascade=CascadeType.ALL)
	@JoinColumn(name="notice_id",referencedColumnName="id")
	public Notice getNotice() {
		return notice;
	}
	public void setNotice(Notice notice) {
		this.notice = notice;
	}

}
