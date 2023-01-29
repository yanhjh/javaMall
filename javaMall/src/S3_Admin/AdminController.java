package S3_Admin;

import java.util.Scanner;

import S1_Member.MemberController;
import S1_Member.MemberDAO;
import S2_Item.ItemController;
import S4_Cart.CartController;
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

	public void init() {
		scan = Util.scan;
		itemController = ItemController.getInstance();
		cartController = CartController.getInstance();
		memberController = MemberController.getInstance();
	}

	public String menuAdmin() {
		while (true) {
			System.out.println("[1.ȸ������] [2.��ǰ����] [3.��ٱ��ϰ���] [0.�ڷΰ���]");
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
		}
	}

	public void menuMemberAdmin() {
		while (true) {
			System.out.println("=== ȸ�� ������ ===");
			System.out.println("[1.ȸ�� ���] [2.�ֹ���� ] [3.����Ȯ�� ���] [0.�ڷΰ���]");
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
			System.out.println("=== ������ ������ ===");
			System.out.println("[1.��ǰ��ü��� ] [2.�������߰�] [3.�����ۻ���] [0.�ڷΰ���]");
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
				System.out.println("������ ������ �̸� �Է�");
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
			System.out.println("=== ��ٱ��� ������ ===");
			System.out.println("[1.��ٱ�����ü��� ] [0.�ڷΰ���]");
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
