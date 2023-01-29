package S5_Board;

import java.util.ArrayList;

public class BoardDAO {

	private ArrayList<Board> boardList;
	int count; // 전체 게시글 수
	int pageSize = 2; // 한 페이지에 보여줄 게시글 수
	int curPageNum; // 현재 페이지 번호
	int pageCount; // 전체 페이지 개수
	int startRow;// 현재 페이지의 게시글 시작 번호
	int endRow;// 현재 페이지의 게시글 마지막 번호
	boolean nextPage;
	boolean previousPage;
	int pageStatus;

	public BoardDAO() {
		
		boardList = new ArrayList<Board>();

	}
	public void boardSelect(int sel) {
		
		if(pageStatus==1) {
			
		}
		else if(pageStatus==2) {
			
		}
		else if(pageStatus==3) {
			
		}
		
	}
	public void pageSelect() {
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

	public void showBoard(int count) {
		
		int idx=-1;
		System.out.println("=====쇼핑몰 게시판=====");
		for (int i = count*pageSize; i < count*pageSize+2; i++) {idx=i;
			System.out.println(boardList.get(i).getBoardNum() + ") " + boardList.get(i).getTitle() + " /"
					+ boardList.get(i).getWriter());
			if(i+1==boardList.size()) {break;}
		}
		curPageNum=count+1;
		pageCount = this.count / pageSize + 1;
		System.out.println(curPageNum+"/"+pageCount);
		startRow=(count)*2+1;
		
		if(pageCount!=1) {
			if(curPageNum==pageCount) {
				nextPage=false;
			}
			else {
				
				nextPage=true;
				if(curPageNum!=1) {
					previousPage=true;
				}
			}
		}
		
		
		

	}

	public void writeBoard(String title, String content, String memberLoginID) {
		Board m = new Board();
		count++;
		m.setBoardNum(m.getBoardNum() + 1);

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
