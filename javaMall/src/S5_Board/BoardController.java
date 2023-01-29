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
	private MemberController memberController;
	
	
	public void init(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
		scan=Util.scan;
		mallController=MallController.getInstance();
		memberController=MemberController.getInstance();
		
		
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
					
					int count=0;
					boardDAO.showBoard(count);
					
					boardDAO.pageSelect();

					
					
					sel=scan.nextInt();
					if(sel==0) {break;}
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
				boardDAO.writeBoard(title,content,mallController.getMemberLoginID());
				
			}
			else if(sel==3) {
				
			}
		}
	}
	

}
