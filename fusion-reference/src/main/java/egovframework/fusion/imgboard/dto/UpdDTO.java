package egovframework.fusion.imgboard.dto;

import java.util.List;

public class UpdDTO {
	private List<String> originImgId;

	public List<String> getOriginImgId() {
		return originImgId;
	}

	public void setOriginImgId(List<String> originImgId) {
		this.originImgId = originImgId;
	}

	@Override
	public String toString() {
		return "UpdDTO [originImgId=" + originImgId + "]";
	}
}
