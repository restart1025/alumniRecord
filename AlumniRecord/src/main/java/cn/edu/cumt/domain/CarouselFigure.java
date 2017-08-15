package cn.edu.cumt.domain;

import java.sql.Timestamp;

public class CarouselFigure extends BaseDomain {

	private String title;//标题
	
	private String content;//内容
	
	private CarouselFigureAttachment attachement;//附件
	
	private Person editor;//编辑人
	
	private Timestamp editDateTime;//编辑时间
	
}
