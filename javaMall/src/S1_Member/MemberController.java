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
			Member member = new Member(1, "admin", "admin", "관리자");
			memberDAO.addMember(member);
		}
	}

	public void printMember() {
		memberDAO.printMemberList();

	}

	public String memberLogin() {
		System.out.println("===[ 로그인 ]===");
		System.out.println("[로그인] 아이디 입력 : ");
		String id = scan.next();
		System.out.println("[로그인] 비밀번호 입력 : ");
		String pw = scan.next();
		boolean check = memberDAO.checkMemberLogin(id, pw);
		if (check == true) {
			System.out.println("[" + id + " 로그인]");
			return id;
		}
		return null;
	}

	public void memberJoin() {
		System.out.println("===[ 회원가입 ]===");
		System.out.println("[회원가입] 아이디 입력 : ");
		String id = scan.next();
		boolean check = memberDAO.checkMember(id);
		if (check == true) {
			System.out.println("[중복아이디]");
		} else {
			System.out.println("[회원가입] 비밀번호 입력 : ");
			String pw = scan.next();
			System.out.println("[회원가입] 이름 입력 : ");
			String name = scan.next();
			int memberNumber = memberDAO.getNextNumber();
			Member member = new Member(memberNumber, id, pw, name);
			memberDAO.addMember(member);
			System.out.println("[회원가입 성공]");
		}
	}

	public String menuMember(String memberLoginID) {
		while (true) {
			System.out.println("[1.쇼핑] [2.장바구니] [3.게시판] [4.회원탈퇴] [5.회원정보 수정] [6.수령 확인] [0.뒤로가기] ");
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
				System.out.println("탈퇴 완료");
				return null;
				
			}
			else if(select==5) {
				System.out.println("비밀번호 입력");
				String pw=scan.next();
				boolean check=memberDAO.checkPw(memberLoginID, pw);
				if(check) {
					int num=scan.nextInt();
					if(num==1) {System.out.println("수정할 ID입력");
					String newID=scan.next();
					check=memberDAO.checkNewId(newID,memberLoginID);
					if(check) {memberDAO.setMemberID(newID,memberLoginID);}
					else {continue;}
					}
					else if(num==2) {System.out.println("수정할 Pw입력");
					String newPW=scan.next();
					check=memberDAO.checkNewPw(pw, newPW);
					if(check) {memberDAO.setMemberPw(pw,newPW,memberLoginID);}
						
					
					else {continue;}
					}
					else if(num==3) {System.out.println("수정할 이름 입력");
					String newName=scan.next();
					String name=memberDAO.getname(memberLoginID);
					check=memberDAO.checkNewName(name,newName);
					if(check) {
						memberDAO.setMemberName(name,newName,memberLoginID);
					}
					else {continue;}
					}
					
					else {System.out.println("1~3사이 값을 입력하세요");continue;}
					
				}
				else{System.out.println("비밀번호를 다시 확인하세요");continue;}
				
			}
		}
	}
}
