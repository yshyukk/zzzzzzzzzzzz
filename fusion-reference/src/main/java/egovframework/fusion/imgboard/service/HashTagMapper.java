package egovframework.fusion.imgboard.service;


import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.fusion.imgboard.vo.HashTagUpdateVO;
import egovframework.fusion.imgboard.vo.HashTagVO;

@Mapper
public interface HashTagMapper {
	public List<HashTagVO> getHashTagInfo(HashTagVO hashTagVo); 

	public int insHashTag(HashTagVO hashTagVo);
	
	public List<HashTagVO> getHashTagDetailInfo(int imgBoardId);
	
	//public void updateHashTag(List<HashTagUpdateVO> hashTagList);
	public void updateHashTag(HashTagVO hashTagVo);
	
	public void delHashTag(int imgBoardId);
}