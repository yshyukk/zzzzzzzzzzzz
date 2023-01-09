package egovframework.fusion.imgboard.service;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.fusion.imgboard.vo.ImgBoardLikeCntVO;
import egovframework.fusion.imgboard.vo.ImgBoardLikeVO;

@Mapper
public interface ImgBoardLikeMapper {
	
	public int insLikeInfo(ImgBoardLikeVO imgBoardLikeVo);
	
	public List<ImgBoardLikeCntVO> getLikeYn(String userId);
	
	public void upLikeYn(ImgBoardLikeCntVO imgBoardLikeCntVo);
	
	public void getLikeYnStat(ImgBoardLikeVO imgBoardLikeVo);

	public void insLikeCntInfo(ImgBoardLikeCntVO imgBoardLikeCntVo);
	
	public int getLikeYnRes(ImgBoardLikeCntVO imgBoardLikeCntVo);


}