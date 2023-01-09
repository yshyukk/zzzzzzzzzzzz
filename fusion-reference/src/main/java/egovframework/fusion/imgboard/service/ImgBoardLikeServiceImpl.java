package egovframework.fusion.imgboard.service;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.fusion.imgboard.vo.ImgBoardLikeCntVO;
import egovframework.fusion.imgboard.vo.ImgBoardLikeVO;

@Service
public class ImgBoardLikeServiceImpl extends EgovAbstractServiceImpl implements ImgBoardLikeService{
	private static final Logger LOGGER = LoggerFactory.getLogger(ImgBoardServiceImpl.class);
	
	@Autowired
	ImgBoardLikeMapper imgBoardLikeMapper;
		
	@Override
	public int insLikeInfo(ImgBoardLikeVO imgBoardLikeVo) {

	return imgBoardLikeMapper.insLikeInfo(imgBoardLikeVo);
	}

	@Override
	public List<ImgBoardLikeCntVO> getLikeYn(String userId) {
		return imgBoardLikeMapper.getLikeYn(userId);
	}

	@Override
	public void upLikeYn(ImgBoardLikeCntVO imgBoardLikeCntVo) {
		imgBoardLikeMapper.upLikeYn(imgBoardLikeCntVo);		
	}

	@Override
	public void insLikeCntInfo(ImgBoardLikeCntVO imgBoardLikeCntVo) {
		imgBoardLikeMapper.insLikeCntInfo(imgBoardLikeCntVo);
	}

	@Override
	public int getLikeYnRes(ImgBoardLikeCntVO imgBoardLikeCntVo) {
		return imgBoardLikeMapper.getLikeYnRes(imgBoardLikeCntVo);
	}

	
}