package egovframework.fusion.imgboard.vo;

import java.sql.Date;

public class ImgVO {
	private Integer imgId;
	private String imgOriginName;
	private String imgSaveName;
	private String imgSavePath;
	private long imgSize;
	private String imgFormat;
	private Date imgRegisterDt;
	private String delYn;
	private Integer imgBoardId;
	private Date imgUpdateDt;
	private String imgCallPath;
	
	
	public String getImgCallPath() {
		return imgCallPath;
	}
	public void setImgCallPath(String imgCallPath) {
		this.imgCallPath = imgCallPath;
	}
	private String imgWriter;
	
	public String getImgWriter() {
		return imgWriter;
	}
	public void setImgWriter(String imgWriter) {
		this.imgWriter = imgWriter;
	}
	public Integer getImgId() {
		return imgId;
	}
	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}
	public String getImgOriginName() {
		return imgOriginName;
	}
	public void setImgOriginName(String imgOriginName) {
		this.imgOriginName = imgOriginName;
	}
	public String getImgSaveName() {
		return imgSaveName;
	}
	public void setImgSaveName(String imgSaveName) {
		this.imgSaveName = imgSaveName;
	}
	public String getImgSavePath() {
		return imgSavePath;
	}
	public void setImgSavePath(String imgSavePath) {
		this.imgSavePath = imgSavePath;
	}
	public long getImgSize() {
		return imgSize;
	}
	public void setImgSize(long imgSize) {
		this.imgSize = imgSize;
	}
	public String getImgFormat() {
		return imgFormat;
	}
	public void setImgFormat(String imgFormat) {
		this.imgFormat = imgFormat;
	}
	
	public Date getImgRegisterDt() {
		return imgRegisterDt;
	}
	public void setImgRegisterDt(Date imgRegisterDt) {
		this.imgRegisterDt = imgRegisterDt;
	}
	public Date getImgUpdateDt() {
		return imgUpdateDt;
	}
	public void setImgUpdateDt(Date imgUpdateDt) {
		this.imgUpdateDt = imgUpdateDt;
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
	@Override
	public String toString() {
		return "ImgVO [imgId=" + imgId + ", imgOriginName=" + imgOriginName + ", imgSaveName=" + imgSaveName
				+ ", imgSavePath=" + imgSavePath + ", imgSize=" + imgSize + ", imgFormat=" + imgFormat
				+ ", imgRegisterDt=" + imgRegisterDt + ", delYn=" + delYn + ", imgBoardId=" + imgBoardId
				+ ", imgUpdateDt=" + imgUpdateDt + ", imgCallPath=" + imgCallPath + ", imgWriter=" + imgWriter + "]";
	}
	
}