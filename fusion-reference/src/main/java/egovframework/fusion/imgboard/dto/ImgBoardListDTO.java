package egovframework.fusion.imgboard.dto;

public class ImgBoardListDTO {
	private int imgBoardId;
	private int likeCnt;
	
	private String title;
	private String imgBoardContnet;
	private String imgBoardWriter;
	private String thumbNailName;
	private String delYn;
	private String liked;
	

	public String getLiked() {
		return liked;
	}
	public void setLiked(String liked) {
		this.liked = liked;
	}
	public int getImgBoardId() {
		return imgBoardId;
	}
	public void setImgBoardId(int imgBoardId) {
		this.imgBoardId = imgBoardId;
	}
	public int getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgBoardContnet() {
		return imgBoardContnet;
	}
	public void setImgBoardContnet(String imgBoardContnet) {
		this.imgBoardContnet = imgBoardContnet;
	}
	public String getImgBoardWriter() {
		return imgBoardWriter;
	}
	public void setImgBoardWriter(String imgBoardWriter) {
		this.imgBoardWriter = imgBoardWriter;
	}
	public String getThumbNailName() {
		return thumbNailName;
	}
	public void setThumbNailName(String thumbNailName) {
		this.thumbNailName = thumbNailName;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	@Override
	public String toString() {
		return "ImgBoardListDTO [imgBoardId=" + imgBoardId + ", likeCnt=" + likeCnt + ", title=" + title
				+ ", imgBoardContnet=" + imgBoardContnet + ", imgBoardWriter=" + imgBoardWriter + ", thumbNailName="
				+ thumbNailName + ", delYn=" + delYn + ", liked=" + liked + "]";
	}
	

	
}