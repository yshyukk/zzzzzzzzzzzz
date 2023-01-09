package egovframework.fusion.imgboard.vo;

public class HashTagUpdateVO {
	
	private String HashTagContent;
	private int HashTagId;
	
	@Override
	public String toString() {
		return "HashTagUpdateVO [HashTagContent=" + HashTagContent + ", HashTagId=" + HashTagId + "]";
	}
	public String getHashTagContent() {
		return HashTagContent;
	}
	public void setHashTagContent(String hashTagContent) {
		HashTagContent = hashTagContent;
	}
	public int getHashTagId() {
		return HashTagId;
	}
	public void setHashTagId(int hashTagId) {
		HashTagId = hashTagId;
	}
}