/*********************************************************
 * 업 무 명 : 게시판 컨트롤러
 * 설 명 : 게시판을 조회하는 화면에서 사용 
 * 작 성 자 : 김민규
 * 작 성 일 : 2022.10.06
 * 관련테이블 : 
 * Copyright ⓒ fusionsoft.co.kr
 *
 *********************************************************/
package egovframework.fusion.board.service;

import java.util.List;

import egovframework.fusion.board.vo.BoardVO;


public interface BoardService {
	
	public List<BoardVO> getBoardList(BoardVO boardVo);
	
	public void insBoardPost(BoardVO boardVo);
	
	public BoardVO getBoardPost(BoardVO boardVo);
	
	//public void updBoardCnt(BoardVO boardVo);
	public int updBoardCnt(BoardVO boardVo);
	
	public void updBoardPost(BoardVO boardVo);
	
	public void delBoardPost(BoardVO boardVo);
	
	public void delBoardList(List<String> delBoardIdList);
	
	public int selectBoardTotal(BoardVO boardVo);
	
	public int searchResultTotal(BoardVO boardVo);
	
	public List<BoardVO> getNoticeList(BoardVO boardVo);
	
	public void insCntInfo(BoardVO boardVo);
	
	public void upCnt(BoardVO boardVo);

	public List<BoardVO> getCommnets(BoardVO boardVo);

	public void insCommInfo(BoardVO boardVo);
	
	public void insRepPost(BoardVO boardVo);
	
	public String commIdConf(BoardVO boardVo);
	
	public void upCommInfo(BoardVO boardVo);
	
	public void commDelDate(int commId);
	
	public void delOneBoardList(BoardVO boardVo);
	
}