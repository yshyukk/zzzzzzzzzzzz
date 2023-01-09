package egovframework.fusion.board.service;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import egovframework.fusion.board.vo.BoardVO;


@Mapper
public interface BoardMapper {
	
	
	public List<BoardVO> getBoardList(BoardVO boardVo);
	
	public void insBoardPost(BoardVO boardVo);
	
	public BoardVO getBoardPost(BoardVO boardVo);
	
	public int updBoardCnt(BoardVO boardVo);
	
	public void updBoardPost(BoardVO boardVo);
	
	public void delBoardPost(BoardVO boardVo);
	
	public int selectBoardTotal(BoardVO boardVo);
	
	public int searchResultTotal(BoardVO boardVo);
	
	public void delBoardList(List<String> delBoardIdList);
	
	public List<BoardVO> getNoticeList(BoardVO boardVo);
	
	public void insCntInfo(BoardVO boardVo);
	
	public void upCnt(BoardVO boardVo);
	
	public List<BoardVO> getCommnets(BoardVO boardVo);
	
	public void insCommInfo(BoardVO boardVo);
	
	public void upCommInfo(BoardVO boardVo);
	
	public void insRepPost(BoardVO boardVo);
	
	public String commIdConf(BoardVO boardVo);
	
	public void commDelDate(int commId);
	
	public void delOneBoardList(BoardVO boardVo);
	
	
}