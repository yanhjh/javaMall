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
			
			System.out.println("1)게시판 출력 2)게시글작성 3)게시글삭제 0)뒤로가기");
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
					else if(sel==1) {System.out.println("현재 페이지내에 있는 글 중에서 확인할 게시글 번호를 입력하세요");
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
				System.out.println("제목 입력");
				String title=scan.next();
				System.out.println("내용 입력");
				String content=scan.next();
				boardDAO.writeBoard(title,content,mallController.getMemberLoginID(),mallController.getMemberPw(mallController.getMemberLoginID()));
				
			}
			else if(sel==3) {
				System.out.println("pw입력");
				String pw=scan.next();
				
				boolean check=mallController.isCorrectPw(mallController.getMemberLoginID(), pw);
				if(check) {
					check=boardDAO.showMyWriting(mallController.getMemberLoginID());
					if(check) {
						System.out.println("삭제할 게시글 번호를 입력해주세요");
						sel=scan.nextInt();
						boardDAO.deleteMyWriting(sel,mallController.getMemberLoginID());
					}
				}
				else {System.out.println("비밀번호를 다시 확인해주세요.");}
				
			}
		}
	}
	

}
