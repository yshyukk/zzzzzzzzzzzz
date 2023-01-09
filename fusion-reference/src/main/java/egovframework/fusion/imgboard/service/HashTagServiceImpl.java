package egovframework.fusion.imgboard.service;


import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.fusion.imgboard.vo.HashTagUpdateVO;
import egovframework.fusion.imgboard.vo.HashTagVO;

@Service
public class HashTagServiceImpl extends EgovAbstractServiceImpl implements HashTagService{
	@Autowired
	HashTagMapper hashTagMapper;
	
	@Override
	public int insHashTag(HashTagVO hashTagVo) {
		return hashTagMapper.insHashTag(hashTagVo);
	}

	@Override
	public List<HashTagVO> getHashTagInfo(HashTagVO hashTagVo) {
		return hashTagMapper.getHashTagInfo(hashTagVo);
	}

	@Override
	public List<HashTagVO> getHashTagDetailInfo(int imgBoardId) {
		return hashTagMapper.getHashTagDetailInfo(imgBoardId);
	}

	@Override
	public void updateHashTag(HashTagVO hashTagVo) {
		hashTagMapper.updateHashTag(hashTagVo);
		
	}

	@Override
	public void delHashTag(int imgBoardId) {
		hashTagMapper.delHashTag(imgBoardId);
	}

//	@Override
//	public void updateHashTag(List<HashTagUpdateVO> hashTagList) {
//		hashTagMapper.updateHashTag(hashTagList);
//	}
//	

}