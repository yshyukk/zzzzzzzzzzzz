package egovframework.fusion.imgboard.service;

import java.util.List;

import egovframework.fusion.imgboard.vo.ImgBoardLikeCntVO;
import egovframework.fusion.imgboard.vo.ImgBoardLikeVO;

public interface ImgBoardLikeService{
	
	public int insLikeInfo(ImgBoardLikeVO imgBoardLikeVo);
	
	public List<ImgBoardLikeCntVO> getLikeYn(String userId);
	
	public void upLikeYn(ImgBoardLikeCntVO imgBoardLikeCntVo);
	
	public void insLikeCntInfo(ImgBoardLikeCntVO imgBoardLikeCntVo);
	
	public int getLikeYnRes(ImgBoardLikeCntVO imgBoardLikeCntVo);
	
}