package egovframework.fusion.imgboard.vo;

import java.sql.Date;

public class ImgBoardCommentVO {
	private Integer commId;
	private Integer commParent;
	private String commContent;
	private String commWriter;
	private Date commRegistDt;
	private String delYn;
	private Integer imgBoardId;
	
	public Integer getCommId() {
		return commId;
	}
	public void setCommId(Integer commId) {
		this.commId = commId;
	}
	public Integer getCommParent() {
		return commParent;
	}
	public void setCommParent(Integer commParent) {
		this.commParent = commParent;
	}
	public String getCommContent() {
		return commContent;
	}
	public void setCommContent(String commContent) {
		this.commContent = commContent;
	}
	public String getCommWriter() {
		return commWriter;
	}
	public void setCommWriter(String commWriter) {
		this.commWriter = commWriter;
	}
	public Date getCommRegistDt() {
		return commRegistDt;
	}
	public void setCommRegistDt(Date commRegistDt) {
		this.commRegistDt = commRegistDt;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public Integer getImgBoardId() {
		return imgBoardId;
	}
	public void setImgBoardId(Integer imgBoardId) {
		this.imgBoardId = imgBoardId;
	}
 
}