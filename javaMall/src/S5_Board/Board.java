package S5_Board;

public class Board {
	
	private int boardNum;
	private String title;
	private String content;
	private String writer;
	@Override
	public String toString() {
		return "Board [boardNum=" + boardNum + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", pw=" + pw + "]";
	}
	private String pw;
	public Board() {}
	

	
//	public int getCount() {
//		return count;
//	}
//	public int getPageSize() {
//		return pageSize;
//	}
//	public int getCurPageNum() {
//		return curPageNum;
//	}
//	public int getPageCount() {
//		return pageCount;
//	}
//	public int getStartRow() {
//		return startRow;
//	}
//	public int getEndRow() {
//		return endRow;
//	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public String getWriter() {
		return writer;
	}
	public String getPw() {
		return pw;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum=boardNum;
	}
//	public void setCount(int count) {
//		this.count=count;
//		
//	}
//	public void setCurPageNum(int curPageNum) {
//		this.curPageNum=curPageNum;
//	}
//	public void setPageCount(int pageCount ) {
//		this.pageCount=pageCount;
//	}
//	public void setStartRow(int startRow) {
//		this.startRow=startRow;
//	}
//	public void setEndRow(int endRow) {
//		this.endRow=endRow;
//	}
	public void setTitle(String title) {
		this.title=title;
	}
	public void setContent(String content) {
		this.content=content;
	}
	public void setWriter(String memberLoginID) {
		this.writer=memberLoginID;
	}
	public void setPw(String memberLoginPw) {
		this.pw=memberLoginPw;
	}
	


	

}
