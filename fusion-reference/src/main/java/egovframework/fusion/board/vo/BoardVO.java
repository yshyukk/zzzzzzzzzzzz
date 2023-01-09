/*********************************************************
 * 업 무 명 : 게시판 컨트롤러
 * 설 명 : 게시판을 조회하는 화면에서 사용 
 * 작 성 자 : 김민규
 * 작 성 일 : 2022.10.06
 * 관련테이블 : 
 * Copyright ⓒ fusionsoft.co.kr
 *
 *********************************************************/
package egovframework.fusion.board.vo;

import java.io.Serializable;

public class BoardVO implements Serializable{

	private static final long serialVersionUID = -8402510944659037798L;

	/* 게시판 */
	private Integer board_num;
	private Integer row_num;
	
	private Integer board_id;
	private String title;
	private String content;
	private String writer;
	private String board_content;
	private String del_yn;
	private String regist_dt;
	private String update_dt;
	private String select_board;
	private int board_type;
	
	/*페이징*/
	private int total;
	private int totalPage;
	private int viewPage = 1; //현재페이지
	private int startIndex = 1; //시작
	private int endIndex=10; // 끝
	private boolean prev;
	private boolean next;
	
	/*검색*/
	private String search;
	private String searchText;
	private int pageFilter;
	private String pageFilterNum;
	
	/*카운트*/
	private String cnt_user;
	private Integer board_cnt=0;
	private String limit_dt;
	private Integer cnt_id;
	
	/*댓글*/
	private Integer comm_id;
	private Integer comm_parent;
	private String comm_content;
	private String comm_writer;
	private String comm_regist_dt;
	private Integer level;

	/*답글*/
	private int rep_parent;
	
	
	
	public Integer getRow_num() {
		return row_num;
	}
	public void setRow_num(Integer row_num) {
		this.row_num = row_num;
	}
	public int getPageFilter() {
		return pageFilter;
	}
	public void setPageFilter(int pageFilter) {
		this.pageFilter = pageFilter;
	}
	public String getPageFilterNum() {
		return pageFilterNum;
	}
	public void setPageFilterNum(String pageFilterNum) {
		this.pageFilterNum = pageFilterNum;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public int getRep_parent() {
		return rep_parent;
	}
	public void setRep_parent(int rep_parent) {
		this.rep_parent = rep_parent;
	}
	public Integer getComm_parent() {
		return comm_parent;
	}
	public void setComm_parent(Integer comm_parent) {
		this.comm_parent = comm_parent;
	}
	public String getComm_content() {
		return comm_content;
	}
	public void setComm_content(String comm_content) {
		this.comm_content = comm_content;
	}
	public String getComm_writer() {
		return comm_writer;
	}
	public void setComm_writer(String comm_writer) {
		this.comm_writer = comm_writer;
	}
	public String getComm_regist_dt() {
		return comm_regist_dt;
	}
	public void setComm_regist_dt(String comm_regist_dt) {
		this.comm_regist_dt = comm_regist_dt;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getComm_id() {
		return comm_id;
	}
	public void setComm_id(Integer cOMM_ID) {
		comm_id = cOMM_ID;
	}
	public Integer getCnt_id() {
		return cnt_id;
	}
	public void setCnt_id(Integer cnt_id) {
		this.cnt_id = cnt_id;
	}
	public String getLimit_dt() {
		return limit_dt;
	}
	public void setLimit_dt(String limit_dt) {
		this.limit_dt = limit_dt;
	}
	public String getCnt_user() {
		return cnt_user;
	}
	public void setCnt_user(String cnt_user) {
		this.cnt_user = cnt_user;
	}
	public String getSelect_board() {
		return select_board;
	}
	public void setSelect_board(String select_board) {
		this.select_board = select_board;
	}
	public int getBoard_type() {
		return board_type;
	}
	public void setBoard_type(int board_type) {
		this.board_type = board_type;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public int getViewPage() {
		return viewPage;
	}
	public void setViewPage(int viewPage) {
		this.viewPage = viewPage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/* 사용자 */
	private String id;
	
	public Integer getBoard_id() {
		return board_id;
	}
	public void setBoard_id(Integer board_id) {
		this.board_id = board_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Integer getBoard_cnt() {
		return board_cnt;
	}
	public void setBoard_cnt(Integer board_cnt) {
		this.board_cnt = board_cnt;
	}
	public String getRegist_dt() {
		return regist_dt;
	}
	public void setRegist_dt(String regist_dt) {
		this.regist_dt = regist_dt;
	}
	public String getUpdate_dt() {
		return update_dt;
	}
	public void setUpdate_dt(String update_dt) {
		this.update_dt = update_dt;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getBoard_num() {
		return board_num;
	}
	public void setBoard_num(Integer board_num) {
		this.board_num = board_num;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

}