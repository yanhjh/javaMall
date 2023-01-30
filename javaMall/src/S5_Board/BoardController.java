package S5_Board;





import java.util.Scanner;

import S0_MALL.MallController;
import S1_Member.MemberController;
import S_MyUtil.Util;

public class BoardController {
	private BoardController() {
	}

	static private BoardController instance = new BoardController();

	static public BoardController getInstance() {
		return instance;
	}

	private BoardDAO boardDAO;
	private Scanner scan;
	private MallController mallController;
	
	
	
	public void init(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
		scan=Util.scan;
		mallController=MallController.getInstance();
		
		
		
	}
	public boolean boardNumCheck(int select) {
		return boardDAO.boardNumCheck(select);
	}
	public boolean boardIdCheck(String id) {
		return boardDAO.boardIdCheck(id);
	}
	public void adminRemoveBoardNum(int select) {
		boardDAO.adminRemoveBoardNum(select);
	}
	public void adminRemoveBoardId(String id) {
		boardDAO.adminRemoveBoardID(id);
		
	}
	public void showBoardList() {
		boardDAO.showBoardList();
	}
	public void setMemberID(String newID, String memberLoginID) {
		boardDAO.setMemberID(newID, memberLoginID);
		
	}
	public void setMemberPw( String newPw, String memberLoginID) {
		boardDAO.setMemberPw( newPw, memberLoginID);
	}
	public void setPageSize(int size) {
		boardDAO.setPageSize(size);
	}
	
	public void menuBoard() {
		while(true) {
			
			System.out.println("1)�Խ��� ��� 2)�Խñ��ۼ� 3)�Խñۻ��� 0)�ڷΰ���");
			int sel=scan.nextInt();
			if(sel==0) {
				break;
			}
			else if(sel==1) {
				boolean check=boardDAO.checkBoardSize();
				
				if(check) {
					while(true) {
					
					
					boardDAO.showBoard();
					boardDAO.setPageStatus();
					boardDAO.pageSelect();

					
					
					sel=scan.nextInt();
					if(sel==0) {break;}
					else if(sel==1) {System.out.println("���� ���������� �ִ� �� �߿��� Ȯ���� �Խñ� ��ȣ�� �Է��ϼ���");
					sel=scan.nextInt();
					check=boardDAO.rangeCheck(sel);
					if(check) {
						boardDAO.printContent(sel);
						
					}	
					continue;}
					boardDAO.boardSelect(sel);
					
					
					
					
					}
					
				
				
					
				}
				else {return;}
				
				
				
				
				
				
				
			}
			else if(sel==2) {
				System.out.println("���� �Է�");
				String title=scan.next();
				System.out.println("���� �Է�");
				String content=scan.next();
				boardDAO.writeBoard(title,content,mallController.getMemberLoginID(),mallController.getMemberPw(mallController.getMemberLoginID()));
				
			}
			else if(sel==3) {
				System.out.println("pw�Է�");
				String pw=scan.next();
				
				boolean check=mallController.isCorrectPw(mallController.getMemberLoginID(), pw);
				if(check) {
					check=boardDAO.showMyWriting(mallController.getMemberLoginID());
					if(check) {
						System.out.println("������ �Խñ� ��ȣ�� �Է����ּ���");
						sel=scan.nextInt();
						boardDAO.deleteMyWriting(sel,mallController.getMemberLoginID());
					}
				}
				else {System.out.println("��й�ȣ�� �ٽ� Ȯ�����ּ���.");}
				
			}
		}
	}
	

}
