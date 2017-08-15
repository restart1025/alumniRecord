package cn.edu.cumt.Action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.edu.cumt.service.NoticeAttachmentService;
import cn.edu.cumt.service.NoticeService;
import cn.edu.cumt.service.PersonService;
import cn.edu.cumt.service.ResourceService;
import cn.edu.cumt.service.RoleService;


@SuppressWarnings("serial")
public class BaseAction<T> extends ActionSupport implements RequestAware,
SessionAware, ApplicationAware, ModelDriven<T> {
	//���Դ��Ͷ��id�����ֶ� ��ʽ�Զ��Ÿ���
	protected String ids;
	//����ʵ�屸��
	protected T entity;
	//��ȡ����List���ϣ�����struts��json��ʽ����
	protected List<T> jsonList=null;
	//page���ڽ��տͻ��˴��ݵ�ҳ��
	protected Integer page;
	//rows���ڽ��տͻ��˴��ݵ�ÿҳ����
	protected Integer rows;
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public List<T> getJsonList() {
		return jsonList;
	}

	public void setJsonList(List<T> jsonList) {
		this.jsonList = jsonList;
	}
	
	@Resource(name="personService")
	protected PersonService personService;	
	@Resource(name="resourceService")
	protected ResourceService resourceService;
	@Resource(name="roleService")
	protected RoleService roleService; 
	@Resource(name="noticeService")
	protected NoticeService noticeService;
	@Resource(name="noticeAttachmentService")
	protected NoticeAttachmentService noticeAttachmentService;
	
	public BaseAction() {

	}
	@Override
	//���ض���ѹջ
	public T getModel() {
		return entity;
	}
	//���ڷ�װ����request
	protected Map<String, Object> request;
	//���ڷ�װ�Ựsession
	protected Map<String, Object> session;
	//���ڷ�װapplication
	protected Map<String, Object> application;	

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application=application;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request=request;
	}
}
