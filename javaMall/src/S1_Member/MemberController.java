package S1_Member;

import java.util.Scanner;

import S2_Item.ItemController;
import S4_Cart.CartController;
import S_MyUtil.Util;

public class MemberController {
	private MemberController() {
	}

	static private MemberController instance = new MemberController();

	static public MemberController getInstance() {
		return instance;
	}

	private MemberDAO memberDAO;
	private ItemController itemController;
	private CartController cartController;
	private Scanner scan;

	public void init(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
		itemController = ItemController.getInstance();
		cartController = CartController.getInstance();
		scan = Util.scan;
		managerSetting();
	}

	public void managerSetting() {
		if (memberDAO.checkMember("admin") == false) {
			Member member = new Member(1, "admin", "admin", "������");
			memberDAO.addMember(member);
		}
	}

	public void printMember() {
		memberDAO.printMemberList();

	}

	public String memberLogin() {
		System.out.println("===[ �α��� ]===");
		System.out.println("[�α���] ���̵� �Է� : ");
		String id = scan.next();
		System.out.println("[�α���] ��й�ȣ �Է� : ");
		String pw = scan.next();
		boolean check = memberDAO.checkMemberLogin(id, pw);
		if (check == true) {
			System.out.println("[" + id + " �α���]");
			return id;
		}
		return null;
	}

	public void memberJoin() {
		System.out.println("===[ ȸ������ ]===");
		System.out.println("[ȸ������] ���̵� �Է� : ");
		String id = scan.next();
		boolean check = memberDAO.checkMember(id);
		if (check == true) {
			System.out.println("[�ߺ����̵�]");
		} else {
			System.out.println("[ȸ������] ��й�ȣ �Է� : ");
			String pw = scan.next();
			System.out.println("[ȸ������] �̸� �Է� : ");
			String name = scan.next();
			int memberNumber = memberDAO.getNextNumber();
			Member member = new Member(memberNumber, id, pw, name);
			memberDAO.addMember(member);
			System.out.println("[ȸ������ ����]");
		}
	}

	public String menuMember(String memberLoginID) {
		while (true) {
			System.out.println("[1.����] [2.��ٱ���] [3.�Խ���] [4.ȸ��Ż��] [5.ȸ������ ����] [6.���� Ȯ��] [0.�ڷΰ���] ");
			int select = scan.nextInt();
			if (select == 0) {
				return null;
			} else if (select == 1) {
				cartController.checkCartListSize();
				itemController.menuShop();
			} else if (select == 2) {
				cartController.menuCart();
			} else if (select == 3) {

			}
			else if(select==4) {
				boolean check=cartController.checkOrderListMember();
				if(check==false) {memberDAO.deleteMemberError();continue;}
				memberDAO.deleteMember(memberLoginID);
				cartController.removeCartListMember(memberLoginID);
				System.out.println("Ż�� �Ϸ�");
				return null;
				
			}
			else if(select==5) {
				System.out.println("��й�ȣ �Է�");
				String pw=scan.next();
				boolean check=memberDAO.checkPw(memberLoginID, pw);
				if(check) {
					int num=scan.nextInt();
					if(num==1) {System.out.println("������ ID�Է�");
					String newID=scan.next();
					check=memberDAO.checkNewId(newID,memberLoginID);
					if(check) {memberDAO.setMemberID(newID,memberLoginID);}
					else {continue;}
					}
					else if(num==2) {System.out.println("������ Pw�Է�");
					String newPW=scan.next();
					check=memberDAO.checkNewPw(pw, newPW);
					if(check) {memberDAO.setMemberPw(pw,newPW,memberLoginID);}
						
					
					else {continue;}
					}
					else if(num==3) {System.out.println("������ �̸� �Է�");
					String newName=scan.next();
					String name=memberDAO.getname(memberLoginID);
					check=memberDAO.checkNewName(name,newName);
					if(check) {
						memberDAO.setMemberName(name,newName,memberLoginID);
					}
					else {continue;}
					}
					
					else {System.out.println("1~3���� ���� �Է��ϼ���");continue;}
					
				}
				else{System.out.println("��й�ȣ�� �ٽ� Ȯ���ϼ���");continue;}
				
			}
		}
	}
}
