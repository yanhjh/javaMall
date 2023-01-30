package S5_Board;

import java.util.ArrayList;

import S1_Member.MemberController;
import S1_Member.MemberDAO;



public class BoardDAO {

	private ArrayList<Board> boardList;
	
	int count; // ��ü �Խñ� ��
	int pageSize = 2; // �� �������� ������ �Խñ� ��
	int curPageNum = 1; // ���� ������ ��ȣ
	int pageCount; // ��ü ������ ����
	int startRow;// ���� �������� �Խñ� ���� ��ȣ
	int endRow;// ���� �������� �Խñ� ������ ��ȣ
	boolean nextPage;
	boolean previousPage;
	int pageStatus;

	public BoardDAO() {
		
		boardList = new ArrayList<Board>();
		

	}
	public void adminRemoveBoardNum(int select) {
	boardList.remove(select-1);
	count--;
	getPageCount();
	for(int i=0;i<boardList.size();i++) {
		if(boardList.get(i).getBoardNum()!=i+1) {
			Board b=boardList.get(i);
			b.setBoardNum(i+1);
			boardList.set(i, b);
		}
	}
	System.out.println("���� �Ϸ�.");
		
	}
	public void adminRemoveBoardID(String id) {
		int count=0;
		for(int i=0;i<boardList.size();i++) {
			if(boardList.get(i).getWriter().equals(id)) {
				boardList.remove(i);i--;
			}
		}
		this.count-=count;
		getPageCount();
		for(int i=0;i<boardList.size();i++) {
			if(boardList.get(i).getBoardNum()!=i+1) {
				Board b=boardList.get(i);
				b.setBoardNum(i+1);
				boardList.set(i, b);
			}
		}
		System.out.println("���� �Ϸ�.");
	}
	public void showBoardList() {
		if(boardList.size()==0) {System.out.println("����� �Խñ��� �������� �ʽ��ϴ�.");return;}
		System.out.println(boardList);
	}
	public void setMemberID(String newID,String memberLoginID) {
		for(int i=0;i<boardList.size();i++) {
			if(boardList.get(i).getWriter().equals(memberLoginID)) {
				Board b=boardList.get(i);
				b.setWriter(newID);
				boardList.set(i, b);
			}
		}
	System.out.println("�����Ϸ�.");}
	public void setMemberPw(String newPw,String memberLoginID) {
		for(int i=0;i<boardList.size();i++) {
			if(boardList.get(i).getWriter().equals(memberLoginID)) {
				Board b=boardList.get(i);
				b.setPw(newPw);
				boardList.set(i, b);
			}
		}
	System.out.println("�����Ϸ�.");}
	public void setPageSize(int size) {
		this.pageSize=size;
		System.out.println("�����Ϸ�.");
	}
	public void deleteMyWriting(int sel,String memberLoginID) {
		if(sel>boardList.size()) {
			System.out.println("�������� �ʴ� �Խñ� ��ȣ�Դϴ�.");return;
		}
	
		if(boardList.get(sel-1).getWriter().equals(memberLoginID)) {
			boardList.remove(sel-1);
			count--;
			getPageCount();
			
		}
		for(int i=sel-1;i<boardList.size();i++) {
			
			Board b=new Board();
			
			b.setBoardNum(boardList.get(i).getBoardNum()-1);
			b.setContent(boardList.get(i).getContent());
			b.setPw(boardList.get(i).getPw());
			b.setTitle(boardList.get(i).getTitle());
			b.setWriter(boardList.get(i).getWriter());
			
			boardList.set(i, b);
			curPageNum=1;
		}
		
		
		
		
	}
	public boolean showMyWriting(String memberLoginID) {
		int count=0;
		for(int i=0;i<boardList.size();i++) {
			if(boardList.get(i).getWriter().equals(memberLoginID)) {
				System.out.println(boardList.get(i));count++;
			}
		}
		
		if(count==0) {System.out.println("�ۼ��� ���� �������� �ʽ��ϴ�.");return false;}
		else {return true;}
	}
	
	public void showRangeErrMsg() {
		System.out.println("�������� ���� �Է����ּ���.");
	}
	public void plusCurPageNum() {
		curPageNum++;
	}
	public void minusCurPageNum() {
		curPageNum--;
	}
	public void printContent(int sel) {
		System.out.println("�Խñ� ��ȣ:"+boardList.get(sel-1).getBoardNum());
		System.out.println("�Խñ� ����:"+boardList.get(sel-1).getTitle());
		System.out.println("�Խñ� ����:"+boardList.get(sel-1).getContent());
	
		
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
	
	public void getPageCount() {
		if(count%2==1) {
		pageCount=count/pageSize+1;}
		else {
			pageCount=count/pageSize;
		}
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
		System.out.println("=====���θ� �Խ���=====");
		for (int i = (curPageNum-1)*pageSize; i < (curPageNum-1)*pageSize+pageSize; i++) {idx=i;
			System.out.println(boardList.get(i).getBoardNum() + ") " + boardList.get(i).getTitle() + " /"
					+ boardList.get(i).getWriter());
			if(i+1==boardList.size()) {break;}
		}
		
		getPageCount();
		System.out.println("����������: "+curPageNum+"/"+"������������: "+pageCount);
		startRow=(count)*pageSize+1;
		
		
		
		
		

	}

	public void writeBoard(String title, String content, String memberLoginID,String pw) {
		
		
		
		Board m = new Board();
		count++;
		m.setBoardNum(boardList.size()+1);
		m.setPw(pw);
		m.setTitle(title);
		m.setContent(content);
		m.setWriter(memberLoginID);
		boardList.add(m);

	}
	public boolean boardIdCheck(String id) {
		int count=0;
		for(int i=0;i<boardList.size();i++) {
			if(boardList.get(i).getWriter().equals(id)) {count++;}
		}
		if(count==0) {return false;}
		return true;
		
	}
	public boolean boardNumCheck(int select) {
		if(select<=boardList.size()) {return true;}
		return false;
		
	}

	public boolean checkBoardSize() {
		if (boardList.size() == 0) {
			System.out.println("����� �Խñ��� �������� �ʽ��ϴ�.");
			return false;
		}
		return true;
	}

}
