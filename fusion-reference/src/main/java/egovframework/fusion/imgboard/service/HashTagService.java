package egovframework.fusion.imgboard.service;



import java.util.List;

import egovframework.fusion.imgboard.vo.HashTagUpdateVO;
import egovframework.fusion.imgboard.vo.HashTagVO;

public interface HashTagService {
	

	public List<HashTagVO> getHashTagInfo(HashTagVO hashTagVo); 
	
	public int insHashTag(HashTagVO hashTagVo);
	
	public List<HashTagVO> getHashTagDetailInfo(int imgBoardid);
	
	//public void updateHashTag(List<HashTagUpdateVO> hashTagList);
	public void updateHashTag(HashTagVO hashTagVo);
	
	public void delHashTag(int imgBoardId);
	
}
