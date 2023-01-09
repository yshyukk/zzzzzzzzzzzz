package egovframework.fusion.board.service;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import egovframework.fusion.board.vo.BoardVO;

@Service
public class BoardServiceImpl extends EgovAbstractServiceImpl implements BoardService{

	private static final Logger LOGGER = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> getBoardList(BoardVO boardVo) {
		return boardMapper.getBoardList(boardVo);
	}

	@Override
	public void insBoardPost(BoardVO boardVo) {
		
		boardMapper.insBoardPost(boardVo);
	}

	@Override
	public BoardVO getBoardPost(BoardVO boardVo) {
		return boardMapper.getBoardPost(boardVo);
	}

	@Override
	public int updBoardCnt(BoardVO boardVo) {
		return boardMapper.updBoardCnt(boardVo);
	}

	@Override
	public void updBoardPost(BoardVO boardVo) {
		boardMapper.updBoardPost(boardVo);
	}

	@Override
	public void delBoardPost(BoardVO boardVo) {
		boardMapper.delBoardPost(boardVo);
	}

	@Override
	public int selectBoardTotal(BoardVO boardVo) {
		return boardMapper.selectBoardTotal(boardVo);
	}

	@Override
	public int searchResultTotal(BoardVO boardVo) {
		return boardMapper.searchResultTotal(boardVo);
	}

	@Override
	public void delBoardList(List<String> delBoardIdList) {
		boardMapper.delBoardList(delBoardIdList);
		
	}
	/*공지글만 가져오기*/
	@Override
	public List<BoardVO> getNoticeList(BoardVO boardVo) {
		
		return boardMapper.getNoticeList(boardVo);
	}

	@Override
	public void insCntInfo(BoardVO boardVo) {
		boardMapper.insCntInfo(boardVo);
		
	}

	@Override
	public void upCnt(BoardVO boardVo) {
		boardMapper.upCnt(boardVo);
		
	}

	@Override
	public List<BoardVO> getCommnets(BoardVO boardVo) {
		// TODO Auto-generated method stub
		return boardMapper.getCommnets(boardVo);
	}

	@Override
	public void insCommInfo(BoardVO boardVo) {
		boardMapper.insCommInfo(boardVo);
		
	}

	@Override
	public void insRepPost(BoardVO boardVo) {
		boardMapper.insRepPost(boardVo);		
	}

	@Override
	public void upCommInfo(BoardVO boardVo) {
		boardMapper.upCommInfo(boardVo);
		
	}
	@Override
	public String commIdConf(BoardVO boardVo) {
		return  boardMapper.commIdConf(boardVo);
		
	}
	@Override
	public void commDelDate(int commId) {
		boardMapper.commDelDate(commId);
	}

	@Override
	public void delOneBoardList(BoardVO boardVo) {
		boardMapper.delOneBoardList(boardVo);
		
	}


}