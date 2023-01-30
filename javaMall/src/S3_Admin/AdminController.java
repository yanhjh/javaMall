package S3_Admin;

import java.util.Scanner;

import S1_Member.MemberController;
import S1_Member.MemberDAO;
import S2_Item.ItemController;
import S4_Cart.CartController;
import S5_Board.BoardController;
import S_MyUtil.Util;

public class AdminController {
	private AdminController() {
	}

	static private AdminController instance = new AdminController();

	static public AdminController getInstance() {
		return instance;
	}

	private Scanner scan;
	private MemberController memberController;
	private ItemController itemController;
	private CartController cartController;
	private BoardController boardController;

	public void init() {
		scan = Util.scan;
		itemController = ItemController.getInstance();
		cartController = CartController.getInstance();
		memberController = MemberController.getInstance();
		boardController=BoardController.getInstance();
	}

	public String menuAdmin() {
		while (true) {
			System.out.println("[1.회원관리] [2.상품관리] [3.장바구니관리] [4.게시판관리] [0.뒤로가기]");
			int select = scan.nextInt();
			if (select == 0) {
				return null;
			} else if (select == 1) {
				menuMemberAdmin();
			} else if (select == 2) {
				menuItemAdmin();
			} else if (select == 3) {
				menuCartAdmin();
			}
			else if(select==4) {
				menuBoardAdmin();
			}
		}
	}
	
	public void menuBoardAdmin() {
		while(true) {
			System.out.println("=== 게시판 관리자===");
			System.out.println("[1.한 페이지에 보여줄 게시글 수 설정] [2.게시판 출력] [3.게시글 삭제] [0. 뒤로가기]");
			int select=scan.nextInt();
			if(select==0) {
				break;
			}
			else if(select==1) {
				System.out.println("한 페이지에 보여줄 게시글 수를 입력하세요.");
				select=scan.nextInt();
				if(select<1) {System.out.println("1이상값으로 설정.");break;}
				boardController.setPageSize(select);
				break;
				
			}
			else if(select==2) {
				boardController.showBoardList();
			}
			
			else if(select==3) {
				boardController.showBoardList();
				System.out.println("[1.게시글 번호로 삭제] [2.입력한 id가 작성한 게시글 일괄삭제] [0. 뒤로가기]");
				select=scan.nextInt();
				if(select==1) {
					System.out.println("삭제할 게시글 번호 입력");
					select=scan.nextInt();
					boolean check=boardController.boardNumCheck(select);
					if(check) {boardController.adminRemoveBoardNum(select);}
					else {System.out.println("입력오류.");}
				
					
				}
				else if(select==2) {
					System.out.println("일괄삭제할 id 입력");
					String id=scan.next();
					boolean check=boardController.boardIdCheck(id);
					if(check) {
					boardController.adminRemoveBoardId(id);
					}
					else {System.out.println("입력오류.");}
					}
				else if(select==0) {break;}
				
			}
			else {
				System.out.println("범위내에서 입력.");continue;
			}
		}
	}

	public void menuMemberAdmin() {
		while (true) {
			System.out.println("=== 회원 관리자 ===");
			System.out.println("[1.회원 목록] [2.주문목록 ] [3.수령확인 목록] [0.뒤로가기]");
			int select = scan.nextInt();
			if (select == 0) {
				break;
			} else if (select == 1) {
				memberController.printMember();

			} else if (select == 2) {
				cartController.printMemberOrderList();

			} else if (select==3) {
				cartController.printReceiveList();
			}
		}
	}

	public void menuItemAdmin() {
		while (true) {
			System.out.println("=== 아이템 관리자 ===");
			System.out.println("[1.상품전체출력 ] [2.아이템추가] [3.아이템삭제] [0.뒤로가기]");
			int select = scan.nextInt();
			if (select == 0) {
				break;
			} else if (select == 1) {
				boolean check=itemController.checkItemListSize();
				if (check==false) {continue;}
				itemController.adminPrintItemList();

			} else if (select == 2) {

				itemController.adminAddItem();
			} else if (select == 3) {
				boolean check=itemController.checkItemListSize();
				if (check==false) {continue;}
				itemController.adminPrintItemList();
				System.out.println("삭제할 아이템 이름 입력");
				String itemName = scan.next();
				 check = itemController.checkItemList(itemName);
				if (check == true) {

					check = cartController.checkOrderList(itemName);
					if (check == true) {
						itemController.removeItemList(itemName);
						cartController.removeCartList(itemName);
					}

				}

			}
		}
	}

	public void menuCartAdmin() {
		while (true) {
			System.out.println("=== 장바구니 관리자 ===");
			System.out.println("[1.장바구니전체출력 ] [0.뒤로가기]");
			int select = scan.nextInt();
			if (select == 0) {
				break;
			} else if (select == 1) {
				
				boolean check=cartController.checkCartListSize();
				if(check==true) {cartController.printAllCartList();}
				else {cartController.showErrorCart();}

			}
		}
	}

}
