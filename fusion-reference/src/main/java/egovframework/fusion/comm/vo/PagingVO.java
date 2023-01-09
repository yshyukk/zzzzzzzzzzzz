package egovframework.fusion.comm.vo;

public class PagingVO {
	
	private String userId;
	
	

	private int total;
	private int totalPage; //총 페이지 수
	private int viewPage = 1; //현재페이지 번호
	
	private int startIndex = 1; //시작
	private int endIndex=10; // 끝
	private int startPage;
	private int endPage;

	
	private int pageFilter=10;
	private String pageFilterNum;
	
	private boolean prev;
	private boolean next;
	private String search; //검색
	private String searchText;
	

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
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
	public int getViewPage() {
		return viewPage;
	}
	public void setViewPage(int viewPage) {
		this.viewPage = viewPage;
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
	@Override
	public String toString() {
		return "PagingVO [total=" + total + ", totalPage=" + totalPage + ", viewPage=" + viewPage + ", startIndex="
				+ startIndex + ", endIndex=" + endIndex + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", prev=" + prev + ", next=" + next + ", search=" + search + ", searchText=" + searchText
				+ ", pageFilter=" + pageFilter + ", pageFilterNum=" + pageFilterNum + "]";
	}
	
}