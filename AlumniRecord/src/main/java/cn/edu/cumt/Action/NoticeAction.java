package cn.edu.cumt.Action;

import java.io.File;
import java.sql.Timestamp;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;

import cn.edu.cumt.domain.Notice;
import cn.edu.cumt.domain.NoticeAttachment;
import cn.edu.cumt.domain.Person;
import net.sf.json.JSONObject;

public class NoticeAction extends BaseAction<Notice> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;//标题
	private String author;//作者
	private Timestamp dateTime;//时间
	private String content;//内容
	private JSONObject jsonObject = new JSONObject();
	private File[] file;
	private String[] fileContentType;
	private String[] fileFileName;

	/**
	 * 主页面展示公告
	 * @return
	 */
	public String showData(){
		jsonObject = noticeService.showDataByPage(1, 50);
		return SUCCESS;
	}
	/**
	 * 展示我编辑的公告
	 * @return
	 */
	public String showMyData(){
		String personId = (String) session.get("personId");
		jsonObject = noticeService.showMyDataByPage(personId, page, rows);
		return SUCCESS;
	}
	/**
	 * 展示公告所属附件
	 * @return
	 */
	public String showAttachment(){
		jsonObject = noticeService.showAttachment(id);
		return SUCCESS;
	}
	/**
	 * 删除公告附件
	 * @return
	 */
	public String deleteAttachment(){
		try{
			NoticeAttachment noticeAttachment = noticeAttachmentService.getById(id);
			noticeAttachment.setDeleted(true);
			noticeAttachmentService.update(noticeAttachment);
			jsonObject.put("message", SUCCESS);
		}catch(Exception e){
			jsonObject.put("message", ERROR);
		}
		return SUCCESS;
	}
	/**
	 * 新增公告
	 * @return
	 */
	public String addNotice(){
		try{
			String personId = (String) session.get("personId");
			Person author = personService.getByPersonId(personId);
			Notice notice = new Notice();
			notice.setTitle(title);
			notice.setContent(content);
			notice.setAuthor(author);
			notice.setDateTime(new Timestamp(System.currentTimeMillis()));
			notice.setDeleted(false);
			noticeService.add(notice);
			jsonObject.put("message", SUCCESS);
		}catch(Exception e){
			jsonObject.put("message", ERROR);
		}
		return SUCCESS;
	}
	/**
	 * 更新公告
	 * @return
	 */
	public String updateNotice(){
		try{
			String personId = (String) session.get("personId");
			Person author = personService.getByPersonId(personId);
			Notice notice = noticeService.getById(id);
			notice.setTitle(title);
			notice.setContent(content);
			notice.setAuthor(author);
			notice.setDateTime(new Timestamp(System.currentTimeMillis()));
			notice.setDeleted(false);
			noticeService.update(notice);
			jsonObject.put("message", SUCCESS);
		}catch(Exception e){
			jsonObject.put("message", ERROR);
		}
		return SUCCESS;
	}
	
	/**
	 * 删除公告
	 * @return
	 */
	public String delete(){
		try{
			noticeService.delete(id);
			jsonObject.put("message", SUCCESS);
		}catch(Exception e){
			jsonObject.put("message", ERROR);
		}
		return SUCCESS;
	}
	/**
	 * 上传附件
	 * @return
	 */
	public String uploadFile(){

		try{
			Notice notice = noticeService.getById(id);
			NoticeAttachment noticeAttachment = null;
			
			for( int i = 0; i < fileFileName.length; i++ ){
				//获取后缀
				String ext=FilenameUtils.getExtension( fileFileName[i] );
				//判断文件类型
				String attachmentType="";
				switch(ext.toLowerCase()){
					case "jpg":
					case "jpeg":
					case "png":
					case "gif":
					case "bmp":
					case "pcx":
					case "tiff":
					case "tga":
					case "exif":
					case "fpx":
					case "svg":
					case "psd":
					case "cdr":
					case "pcd":
					case "dxf":
					case "ufo":
					case "eps":
					case "ai":
					case "hdri":
					case "raw":			
						attachmentType="图片";
						break;
					case "mp4":
					case "avi":
					case "rmvb":
					case "rm":
					case "asf":
					case "divx":
					case "mpg":
					case "mpeg":
					case "mpe":
					case "wmv":
					case "vob":
						attachmentType="视频";
						break;
					case "doc":
					case "docx":
						attachmentType="文档";
						break;
					case "xls":
					case "xlsx":
						attachmentType="表格";
						break;
					case "ppt":
					case "pptx":
						attachmentType="幻灯片";
						break;
					default: attachmentType="其它文件格式";
				}
				//新名字
				String newName="noticeAttachment/"+UUID.randomUUID().toString()+"."+ext;
				
//				AwsS3Util.uploadFile(newName, file[i], session);
				
				noticeAttachment = new NoticeAttachment();
				noticeAttachment.setAttachmentType(attachmentType);
				noticeAttachment.setDeleted(false);
				noticeAttachment.setLogicalFileName( fileFileName[i] );
				noticeAttachment.setPhysicalFileName(newName);
				noticeAttachment.setNotice(notice);
				noticeAttachmentService.add(noticeAttachment);
			}
			jsonObject.put("message", SUCCESS);
		}catch(Exception e)
		{
			jsonObject.put("message", ERROR);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 删除附件
	 * @return
	 */
	public String deleteFile(){

		try
		{
			NoticeAttachment noticeAttachment = noticeAttachmentService.getById(id);
			noticeAttachment.setDeleted(true);
			noticeAttachmentService.update(noticeAttachment);
			jsonObject.put("message", SUCCESS);
			
		}catch(Exception e)
		{
			jsonObject.put("message", ERROR);
		}
		return SUCCESS;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public JSONObject getJsonObject() {
		return jsonObject;
	}
	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	public File[] getFile() {
		return file;
	}
	public void setFile(File[] file) {
		this.file = file;
	}
	public String[] getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String[] fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String[] getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}

}
