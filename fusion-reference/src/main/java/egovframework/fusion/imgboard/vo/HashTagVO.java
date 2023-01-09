package egovframework.fusion.imgboard.vo;

import java.sql.Date;
import java.util.List;

public class HashTagVO {
	
	private Integer hashTagId;
	private Integer imgBoardId;
	private List<String> hashTag;
	private List<Integer> listHashTagId;
	private String delYn;
	private String hashTagVal;
	private List<String> hashTagList;
	
	private String tag;
	private Date hashTagRegistDt;
	private Date hashTagUpdateDt;
	
	
	public List<String> getHashTagList() {
		return hashTagList;
	}
	public void setHashTagList(List<String> hashTagList) {
		this.hashTagList = hashTagList;
	}
	public String getHashTagVal() {
		return hashTagVal;
	}
	public void setHashTagVal(String hashTagVal) {
		this.hashTagVal = hashTagVal;
	}

	
	
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public List<Integer> getListHashTagId() {
		return listHashTagId;
	}
	public void setListHashTagId(List<Integer> listHashTagId) {
		this.listHashTagId = listHashTagId;
	}
	public List<String> getHashTag() {
		return hashTag;
	}
	public void setHashTag(List<String> hashTag) {
		this.hashTag = hashTag;
	}
	private String hashTagWriter;
	
	public Integer getHashTagId() {
		return hashTagId;
	}
	public void setHashTagId(Integer hashTagId) {
		this.hashTagId = hashTagId;
	}
	public Integer getImgBoardId() {
		return imgBoardId;
	}
	public void setImgBoardId(Integer imgBoardId) {
		this.imgBoardId = imgBoardId;
	}
	
	public String getHashTagWriter() {
		return hashTagWriter;
	}
	public void setHashTagWriter(String hashTagWriter) {
		this.hashTagWriter = hashTagWriter;
	}
	public Date getHashTagRegistDt() {
		return hashTagRegistDt;
	}
	public void setHashTagRegistDt(Date hashTagRegistDt) {
		this.hashTagRegistDt = hashTagRegistDt;
	}
	public Date getHashTagUpdateDt() {
		return hashTagUpdateDt;
	}
	public void setHashTagUpdateDt(Date hashTagUpdateDt) {
		this.hashTagUpdateDt = hashTagUpdateDt;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "HashTagVO [hashTagId=" + hashTagId + ", imgBoardId=" + imgBoardId + ", hashTag=" + hashTag
				+ ", listHashTagId=" + listHashTagId + ", delYn=" + delYn + ", hashTagVal=" + hashTagVal
				 + ", tag=" + tag + ", hashTagRegistDt="
				+ hashTagRegistDt + ", hashTagUpdateDt=" + hashTagUpdateDt + ", hashTagWriter=" + hashTagWriter + "]";
	}
	
	
	
	
}