package S5_Board;

import java.util.ArrayList;

public class BoardDAO {

	private ArrayList<Board> boardList;
	int count; // ��ü �Խñ� ��
	int pageSize = 2; // �� �������� ������ �Խñ� ��
	int curPageNum; // ���� ������ ��ȣ
	int pageCount; // ��ü ������ ����
	int startRow;// ���� �������� �Խñ� ���� ��ȣ
	int endRow;// ���� �������� �Խñ� ������ ��ȣ
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
		System.out.println("1)�Խñ� Ȯ���ϱ�");
		if(nextPage&&previousPage) {System.out.println("2)���������� 3)����������");
		pageStatus=1;}
		else if(nextPage&&!previousPage) {
			System.out.println("2)����������");
			pageStatus=2;
		}
		else if(!nextPage&&previousPage) {
			System.out.println("2)����������");
			pageStatus=3;
		}
		System.out.println("0)�ڷΰ���");
	}

	public void showBoard(int count) {
		
		int idx=-1;
		System.out.println("=====���θ� �Խ���=====");
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
			System.out.println("����� �Խñ��� �������� �ʽ��ϴ�.");
			return false;
		}
		return true;
	}

}
