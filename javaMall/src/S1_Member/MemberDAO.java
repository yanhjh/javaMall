package S1_Member;
import java.util.ArrayList;
public class MemberDAO {
	private ArrayList<Member> memberList;
	private int memberNumber;

	public MemberDAO() {
		memberNumber = 1000;
		memberList = new ArrayList<>();
		setSampleData();
	}
	public void printMemberList() {
		System.out.println(memberList);
	}
	public void printSetMenu() {
		System.out.println("[1]ID 수정 [2]PW 수정 [3]이름 수정 [0]뒤로가기");
	}
	public boolean checkPw(String memberLoginID,String memberLoginPw) {
		for(int i=0;i<memberList.size();i++) {
			if(memberList.get(i).getMemberID().equals(memberLoginID)) {
				if(memberList.get(i).getMemberPW().equals(memberLoginPw)) {
					printSetMenu();
					return true;
				}
				
			}
		}
		return false;
	}
	public boolean checkNewId(String NewId,String memberLoginId) {
		if(NewId.equals(memberLoginId)) {
			System.out.println("기존과 동일한 아이디입니다. 다른아이디를 입력해주세요");return false;}
		int idx=-1;
		for(int i=0;i<memberList.size();i++) {
			if(memberList.get(i).getMemberID().equals(memberLoginId)) {idx=i;break;}
		}
		
		for(int i=0;i<memberList.size();i++) {
			if(i!=idx) {
			
			if(memberList.get(i).getMemberID().equals(NewId)) {
				System.out.println("이미 존재하는 아이디입니다. 다른아이디를 입력해주세요");return false;
			}
			}
		}
		return true;
	}
	public void setMemberID(String newId , int idx) {
		Member m = memberList.get(idx);
		m.setMemberID(newId);
		memberList.set(idx, m);
	
		
	}
	public void addMember(Member member) {
		memberList.add(member);
	}
	public int getNextNumber() {
		memberNumber += 1;
		return memberNumber;
	}
	public void deleteMemberError() {
		System.out.println("주문이 접수된 상품이 존재하여 회원탈퇴가 불가능 합니다. 수령확인후에 회원탈퇴가 가능합니다.");
	}
	public void deleteMember(String memberLoginID) {
		for(int i=0;i<memberList.size();i++) {
			if(memberList.get(i).getMemberID().equals(memberLoginID)) {
				memberList.remove(i);
			}
		}
		
		
	}
	boolean checkMember(String id) {
		for (int i = 0; i < memberList.size(); i++) {
			if (id.equals(memberList.get(i).getMemberID())) {
				return true;
			}
		}
		return false;
	}
	boolean checkMemberLogin(String id, String pw) {
		for (int i = 0; i < memberList.size(); i++) {
			if (id.equals(memberList.get(i).getMemberID()) && 
					pw.equals(memberList.get(i).getMemberPW())) {
				return true;
			}
		}
		return false;
	}
	public void setSampleData() {

		Member member = new Member(getNextNumber(), "a", "a", "김철민");
		addMember(member);
		member = new Member(getNextNumber(), "b", "b", "이민영");
		addMember(member);
	}
}
