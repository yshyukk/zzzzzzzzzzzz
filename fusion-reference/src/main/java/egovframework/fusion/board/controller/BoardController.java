/*********************************************************
 * 업 무 명 : 게시판 컨트롤러
 * 설 명 : 게시판을 조회하는 화면에서 사용 
 * 작 성 자 : 김민규
 * 작 성 일 : 2022.10.06
 * 관련테이블 : 
 * Copyright ⓒ fusionsoft.co.kr
 *
 *********************************************************/
package egovframework.fusion.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.fusion.board.service.BoardService;
import egovframework.fusion.board.vo.BoardVO;



@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	
	/*
	 * 게시판 리스트 출력
	 * @param	
	 * @return	
	 * @exception Exception
	 */
	@RequestMapping(value = "/board/boardList.do", method = RequestMethod.GET)
	public String boardList(@ModelAttribute BoardVO boardVO, Model model) throws Exception {
	
			/*paging 처리 */
			int total=0;
			
			String searchParam = boardVO.getSearch();			
			int filterPageNum =	boardVO.getPageFilter();
			 				
			if(filterPageNum == 0) {
				filterPageNum = 10;
				boardVO.setPageFilter(filterPageNum);
			}
			
			if(searchParam != null || searchParam != "") {
				total = boardService.searchResultTotal(boardVO);
			}
			int totalPage = (int)Math.ceil((double)total/filterPageNum);
			int viewPage = boardVO.getViewPage();
			int startIndex = (viewPage-1)*filterPageNum + 1;
			int endIndex = startIndex + (filterPageNum -1);
			
			boardVO.setStartIndex(startIndex);
			boardVO.setEndIndex(endIndex);
			
			model.addAttribute("total", total);
			model.addAttribute("totalPage", totalPage);
			
			/* boardList 출력 */
			List<BoardVO> boardList = boardService.getBoardList(boardVO);
			List<BoardVO> notice = boardService.getNoticeList(boardVO);
			
			
			model.addAttribute("boardList", boardList);
			model.addAttribute("notice", notice);
			
		return "views/board/boardList";
	}
	/*
	 * 게시글 등록 페이지 이동
	 * @param	
	 * @return	
	 * @exception Exception
	 */
	@RequestMapping(value = "/board/boardRegister.do", method = RequestMethod.GET)
	public String boardRegister(BoardVO boardVO) throws Exception {
		return "views/board/boardRegister";
	}
	
	/*
	 * 게시글 등록
	 * @param	
	 * @return	
	 * @exception Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/board/insBoardPost.do", method = RequestMethod.POST)
	public String insBoardPost(@ModelAttribute BoardVO boardVO, HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			int boardType = 1;
			
			String userId =  (String) session.getAttribute("id");
			boardVO.setWriter(userId);	
	
			String optVal = boardVO.getSelect_board();
			if (optVal.equals("notice")) {							
				boardType = 2;
			}
			boardVO.setBoard_type(boardType);
			boardService.insBoardPost(boardVO);
		}

		return "redirect:/board/boardList.do";
	}
	
	/*
	 * 답글 등록 페이지로 이동   
	 */
	@ResponseBody
	@RequestMapping(value = "/board/insRepPost.do", method = RequestMethod.GET)
	public String insRepPost(BoardVO boardVO, HttpServletRequest request, Model model) throws Exception {
		
			HttpSession session = request.getSession(false);
			
			if(session != null) {
				
				String userId =  (String) session.getAttribute("id");
				
				boardVO.setWriter(userId);	
				boardService.insRepPost(boardVO);
			}	
			model.addAttribute("repInsInfo", boardVO);
			
		return "views/board/boardRegister";
	}
	
	/*
	 * 게시글 상세 조회 페이지
	 * @param	
	 * @return	
	 * @exception Exception
	 */
	@RequestMapping(value = "/board/boardPost.do", method = RequestMethod.GET)
	public String boardPost (BoardVO boardVO, Model model, HttpServletRequest request, @RequestParam(value="board_id") int board_id) throws Exception{
		
			// 조회수 테이블에 정보 입력
			
			HttpSession session = request.getSession(false);
			
			String userId = (String) session.getAttribute("id");			
			
			if(userId == null) {
				BoardVO boardPost = boardService.getBoardPost(boardVO);
				userId = "visitor";
			}
			
			boardVO.setBoard_id(board_id);
			boardVO.setCnt_user(userId);
			
			int SearchRs = boardService.updBoardCnt(boardVO);
			
			// 중복체크
			if(SearchRs == 0 ) {
				boardService.upCnt(boardVO);
			}
			model.addAttribute("boardCnt",boardVO);
		
			BoardVO boardPost = boardService.getBoardPost(boardVO);
			model.addAttribute("boardPost", boardPost);
		
			//댓글 조회
			List<BoardVO> commList = boardService.getCommnets(boardVO);
						
			model.addAttribute("commList", commList);

		return "views/board/boardPost";
	}
	/*
	 * 댓글 추가
	 *
	 */
	@RequestMapping(value = "/board/commdPost.do", method = RequestMethod.POST)
	@ResponseBody
	public String cmmPost(HttpServletRequest request, BoardVO boardVO,
						@RequestParam(value="comm_content") String comm_content, 
						@RequestParam(value="comm_parent") int comm_parent,
						@RequestParam(value="board_id") int board_id) throws Exception {

			HttpSession session = request.getSession(false);
			
			String userId = (String) session.getAttribute("id");	
			
			boardVO.setComm_writer(userId);
			boardVO.setComm_content(comm_content);
			boardVO.setComm_parent(comm_parent);
			boardVO.setBoard_id(board_id);
			
			boardService.insCommInfo(boardVO);
			
		return "redirect:/board/boardPost.do";
	}
	
	/*
	 * 댓글 수정
	 * */
	
	@RequestMapping(value = "/board/commdUpdate.do", method = RequestMethod.POST)
	@ResponseBody
	public String commdUpdate(HttpServletRequest request, BoardVO boardVO) throws Exception {

			HttpSession session = request.getSession(false);
			
			String userId = (String) session.getAttribute("id");	
			
			boardVO.setComm_writer(userId);
			
			String confCommId = boardService.commIdConf(boardVO);
			String msg = "reject";
			
			if(confCommId.equals(userId)) {
				boardService.upCommInfo(boardVO);
				msg = "confirm";				
			}	
		return msg;
	}
	
	/*
	 * 댓글 삭제
	 * */
	@RequestMapping(value = "/board/commDelDate.do", method = RequestMethod.POST)
	@ResponseBody
	public String commDelDate(HttpServletRequest request, BoardVO boardVO) throws Exception {

			HttpSession session = request.getSession(false);
			
			String userId = (String) session.getAttribute("id");	
			
			boardVO.setComm_writer(userId);
			
			String confCommId = boardService.commIdConf(boardVO);
			
			String msg = "reject";
			
			int commId = boardVO.getComm_id();
	
			if(confCommId.equals(userId)) {
				boardService.commDelDate(commId);
				msg = "confirm";				
			}	
		return msg;
	}
	/*
	 * 게시글 수정 페이지 진입
	 * @param	
	 * @return	
	 * @exception Exception
	 */
	@RequestMapping(value = "/board/boardPostModify.do", method = RequestMethod.GET)
	public String boardPostModify(BoardVO boardVO, Model model, HttpServletRequest request) throws Exception {
			HttpSession session = request.getSession(false);
			
			String userId = (String) session.getAttribute("id");
	
			BoardVO boardPost = boardService.getBoardPost(boardVO);
			
			int boardId = boardPost.getBoard_id();
			
			//String confId = boardService.confWriter(boardId);
		
			model.addAttribute("boardPost", boardPost);
			
			return "views/board/boardPostModify";
	}
	
	/*
	 * 게시글 수정
	 * @param	
	 * @return	
	 * @exception Exception
	 */
	@RequestMapping(value = "/board/updBoardPost.do", method = RequestMethod.POST)
	@ResponseBody
	public String updBoardPost(BoardVO boardVO, Model model) throws Exception {
		
			boardService.updBoardPost(boardVO);
		 
		return "redirect:/board/boardPost.do?board_id=" + boardVO.getBoard_id();
	}
	
	/*
	 * 게시글 삭제(삭제여부만 변경)
	 * @param	
	 * @return	
	 * @exception Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/board/delBoardList.do", method = RequestMethod.GET)
	public String delBoardPost( BoardVO boardVO, Model model, 
		   @RequestParam(value="delBoardId[]", required = false)  List<String> delBoardId) throws Exception {
		
		boardService.delBoardList(delBoardId);		
		return "redirect:/board/boardList.do";
	}
	
	/*
	 * 단건삭제
	*/
	@RequestMapping(value = "/board/delOneBoardList.do", method = RequestMethod.POST)
	@ResponseBody
	public String delOneBoardList(BoardVO boardVO, Model model) throws Exception {
				
			boardService.delOneBoardList(boardVO);

		return "redirect:/board/boardList.do";
	}
	
	
}