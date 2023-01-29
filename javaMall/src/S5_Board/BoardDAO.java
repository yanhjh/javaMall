package S5_Board;

import java.util.ArrayList;

import S1_Member.MemberController;



public class BoardDAO {

	private ArrayList<Board> boardList;
	private MemberController memberController;
	int count; // 전체 게시글 수
	int pageSize = 2; // 한 페이지에 보여줄 게시글 수
	int curPageNum = 1; // 현재 페이지 번호
	int pageCount; // 전체 페이지 개수
	int startRow;// 현재 페이지의 게시글 시작 번호
	int endRow;// 현재 페이지의 게시글 마지막 번호
	boolean nextPage;
	boolean previousPage;
	int pageStatus;

	public BoardDAO() {
		
		boardList = new ArrayList<Board>();

	}
	public void deleteMyWriting(int sel,String memberLoginID) {
		int count=0;
		for(int i=0;i<boardList.size();i++) {
			if(boardList.get(i).getWriter().equals(memberLoginID)) {
				if(boardList.get(i).getBoardNum()==sel) {
					count++;
					boardList.remove(i);
				}
			}
		}
		if(count==0) {
			System.out.println("게시글번호를 다시 확인해주세요.");
		}
		
	}
	public boolean showMyWriting(String memberLoginID) {
		int count=0;
		for(int i=0;i<boardList.size();i++) {
			if(boardList.get(i).getWriter().equals(memberLoginID)) {
				System.out.println(boardList.get(i));count++;
			}
		}
		
		if(count==0) {System.out.println("작성한 글이 존재하지 않습니다.");return false;}
		else {return true;}
	}
	public boolean checkPw(String memberLoginID,String pw) {
		memberController.getInstance();
		return memberController.pwCheck(memberLoginID, memberLoginID);
		
	}
	public void showRangeErrMsg() {
		System.out.println("범위내의 값만 입력해주세요.");
	}
	public void plusCurPageNum() {
		curPageNum++;
	}
	public void minusCurPageNum() {
		curPageNum--;
	}
	public void printContent(int sel) {
		System.out.println("게시글 번호:"+boardList.get(sel-1).getBoardNum());
		System.out.println("게시글 제목:"+boardList.get(sel-1).getTitle());
		System.out.println("게시글 내용:"+boardList.get(sel-1).getContent());
	
		
	}
	public boolean rangeCheck(int sel) {
		if(sel>=(curPageNum-1)*pageSize+1&&sel<=curPageNum*pageSize) {
			return true;
		}
		showRangeErrMsg();return false;
	}
	
	public void boardSelect(int sel) {
		
		
		if(pageStatus==1) {
			
			 if(sel==2) {minusCurPageNum();return;
			 
				
			}
			 if(sel==3) {plusCurPageNum();return;
				
			}
			else {System.out.println("1");showRangeErrMsg();return;}
		}
		else if(pageStatus==2) {
			if(sel==2) {plusCurPageNum();return;
				
			}
			else {System.out.println("2");showRangeErrMsg();return;}
			
			
			
		}
		else if(pageStatus==3) {
			if(sel==2) {minusCurPageNum();return;
				
			}
			else {System.out.println("3");showRangeErrMsg();return;}
		}
		
	}
	public void pageSelect() {
		System.out.println("==============");
		System.out.println("1)게시글 확인하기");
		if(nextPage&&previousPage) {System.out.println("2)이전페이지 3)다음페이지");
		pageStatus=1;}
		else if(nextPage&&!previousPage) {
			System.out.println("2)다음페이지");
			pageStatus=2;
		}
		else if(!nextPage&&previousPage) {
			System.out.println("2)이전페이지");
			pageStatus=3;
		}
		System.out.println("0)뒤로가기");
	}
	
	public void getPageCount() {
		pageCount=count/pageSize+1;
	}
	public void setPageStatus() {
		if(pageCount!=1) {
			if(curPageNum==pageCount) {
				nextPage=false;
			}
			else {
				
				nextPage=true;
				if(curPageNum!=1) {
					previousPage=true;
				}
				else {
					previousPage=false;
				}
			}
		}
		else {
			previousPage=false;
			nextPage=false;
		}
	}

	public void showBoard() {
		
		int idx=-1;
		System.out.println("=====쇼핑몰 게시판=====");
		for (int i = (curPageNum-1)*pageSize; i < (curPageNum-1)*pageSize+2; i++) {idx=i;
			System.out.println(boardList.get(i).getBoardNum() + ") " + boardList.get(i).getTitle() + " /"
					+ boardList.get(i).getWriter());
			if(i+1==boardList.size()) {break;}
		}
		
		getPageCount();
		System.out.println("현재페이지: "+curPageNum+"/"+"마지막페이지: "+pageCount);
		startRow=(count)*2+1;
		
		
		
		
		

	}

	public void writeBoard(String title, String content, String memberLoginID) {
		Board m = new Board();
		count++;
		m.setBoardNum(boardList.size()+1);

		m.setTitle(title);
		m.setContent(content);
		m.setWriter(memberLoginID);
		boardList.add(m);

	}

	public boolean checkBoardSize() {
		if (boardList.size() == 0) {
			System.out.println("출력할 게시글이 존재하지 않습니다.");
			return false;
		}
		return true;
	}

}
