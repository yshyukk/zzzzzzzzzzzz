package egovframework.fusion.imgboard.vo;

import java.sql.Date;

public class ImgBoardVO {

	private Integer imgBoardId;
	private String title;
	private String imgBoardContent;
	private String imgBoardRegistDt;
	private String delYn;
	private String imgBoardWriter;
	private String thumbNailName;
	private String imgSavePath;
	

	private String search; //검색
	private String searchText;
	
	private int pageFilter;
	private String pageFilterNum;
	
	
	
	
	public String getImgSavePath() {
		return imgSavePath;
	}
	public void setImgSavePath(String imgSavePath) {
		this.imgSavePath = imgSavePath;
	}

	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public int getPageFilter() {
		return pageFilter;
	}
	public void setPageFilter(int pageFilter) {
		this.pageFilter = pageFilter;
	}
	public String getPageFilterNum() {
		return pageFilterNum;
	}
	public void setPageFilterNum(String pageFilterNum) {
		this.pageFilterNum = pageFilterNum;
	}
	public String getThumbNailName() {
		return thumbNailName;
	}
	public void setThumbNailName(String thumbNailName) {
		this.thumbNailName = thumbNailName;
	}
	public Integer getImgBoardId() {
		return imgBoardId;
	}
	public void setImgBoardId(Integer imgBoardId) {
		this.imgBoardId = imgBoardId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgBoardContent() {
		return imgBoardContent;
	}
	public void setImgBoardContent(String imgBoardContent) {
		this.imgBoardContent = imgBoardContent;
	}
	public String getImgBoardRegistDt() {
		return imgBoardRegistDt;
	}
	public void setImgBoardRegistDt(String imgBoardRegistDt) {
		this.imgBoardRegistDt = imgBoardRegistDt;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getImgBoardWriter() {
		return imgBoardWriter;
	}
	
	public void setImgBoardWriter(String imgBoardWriter) {
		this.imgBoardWriter = imgBoardWriter;
	}
	
	@Override
	public String toString() {
		return "ImgBoardVO [imgBoardId=" + imgBoardId + ", title=" + title + ", imgBoardContent=" + imgBoardContent
				+ ", imgBoardRegistDt=" + imgBoardRegistDt + ", delYn=" + delYn + ", imgBoardWriter=" + imgBoardWriter
				+ "]";
	}
	
}