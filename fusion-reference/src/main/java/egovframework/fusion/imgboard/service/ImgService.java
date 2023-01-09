package egovframework.fusion.imgboard.service;

import java.util.List;

import egovframework.fusion.imgboard.vo.ImgVO;

public interface ImgService {
	public List<ImgVO> getImgInfo(ImgVO imgVo);
	
	public int insImg(List<ImgVO> imageInfo);
	
	public List<ImgVO> getImgDetailInfo(int imgBoardId);
	
	public String getImgPath(int imgId);
	
	public void delImg(int imgId);
	
	public void delImgMulti(List<Integer> imgIds);
}