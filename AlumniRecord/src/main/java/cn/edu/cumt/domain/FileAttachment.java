package cn.edu.cumt.domain;

public class FileAttachment extends BaseDomain {

	private String attachmentType;//附件类型
	private String logicalFileName;//附件逻辑文件名
	private String physicalFileName;//附件物理文件名
	public String getAttachmentType() {
		return attachmentType;
	}
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}
	public String getLogicalFileName() {
		return logicalFileName;
	}
	public void setLogicalFileName(String logicalFileName) {
		this.logicalFileName = logicalFileName;
	}
	public String getPhysicalFileName() {
		return physicalFileName;
	}
	public void setPhysicalFileName(String physicalFileName) {
		this.physicalFileName = physicalFileName;
	}
	
}
