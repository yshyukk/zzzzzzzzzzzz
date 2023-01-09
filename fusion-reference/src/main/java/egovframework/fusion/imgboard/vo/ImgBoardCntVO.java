package egovframework.fusion.imgboard.vo;

import java.sql.Date;

public class ImgBoardCntVO {
	private Integer cntId;
	private Date limitDt;
	private String cntUser;
	private Integer imgBoardId;
	
	public Integer getCntId() {
		return cntId;
	}
	public void setCntId(Integer cntId) {
		this.cntId = cntId;
	}
	public Date getLimitDt() {
		return limitDt;
	}
	public void setLimitDt(Date limitDt) {
		this.limitDt = limitDt;
	}
	public String getCntUser() {
		return cntUser;
	}
	public void setCntUser(String cntUser) {
		this.cntUser = cntUser;
	}
	public Integer getImgBoardId() {
		return imgBoardId;
	}
	public void setImgBoardId(Integer imgBoardId) {
		this.imgBoardId = imgBoardId;
	}
}