package egovframework.fusion.imgboard.vo;

import java.sql.Date;

public class ImgBoardLikeCntVO {

	private int likeCntId;
	private int imgBoardId;
	private String pushLikeUser;
	private String likeYn;
	private Date likeCntRegistDt;

	public Date getLikeCntRegistDt() {
		return likeCntRegistDt;
	}
	public void setLikeCntRegistDt(Date likeCntRegistDt) {
		this.likeCntRegistDt = likeCntRegistDt;
	}
	public int getLikeCntId() {
		return likeCntId;
	}
	public void setLikeCntId(int likeCntId) {
		this.likeCntId = likeCntId;
	}
	public int getImgBoardId() {
		return imgBoardId;
	}
	public void setImgBoardId(int imgBoardId) {
		this.imgBoardId = imgBoardId;
	}
	public String getPushLikeUser() {
		return pushLikeUser;
	}
	public void setPushLikeUser(String pushLikeUser) {
		this.pushLikeUser = pushLikeUser;
	}
	public String getLikeYn() {
		return likeYn;
	}
	public void setLikeYn(String likeYn) {
		this.likeYn = likeYn;
	}
	
	@Override
	public String toString() {
		return "ImgBoardLikeCnt [likeCntId=" + likeCntId + ", imgBoardId=" + imgBoardId + ", pushLikeUser="
				+ pushLikeUser + ", likeYn=" + likeYn + "]";
	}
}