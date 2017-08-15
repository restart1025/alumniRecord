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
	//可以传送多个id给该字段 形式以逗号隔开
	protected String ids;
	//泛型实体备用
	protected T entity;
	//获取泛型List集合，交给struts以json格式返回
	protected List<T> jsonList=null;
	//page用于接收客户端传递的页码
	protected Integer page;
	//rows用于接收客户端传递的每页行数
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
	//返回对象压栈
	public T getModel() {
		return entity;
	}
	//用于封装请求request
	protected Map<String, Object> request;
	//用于封装会话session
	protected Map<String, Object> session;
	//用于封装application
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
