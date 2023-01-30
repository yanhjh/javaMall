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
		if(memberList.size()==0) {System.out.println("����� ����� �������� �ʽ��ϴ�");return;}
		System.out.println(memberList);
	}
	public void printSetMenu() {
		System.out.println("[1]ID ���� [2]PW ���� [3]�̸� ���� [0]�ڷΰ���");
	}
	public String getMemberPw(String memberLoginID) {
		int idx=-1;
		for(int i=0;i<memberList.size();i++) {
			if(memberList.get(i).getMemberID().equals(memberLoginID)) {
				idx=i;
			}
		}
		return memberList.get(idx).getMemberPW();
		
	}
	
	public boolean isCorrectPw(String memberLoginID,String insertPw) {
		System.out.println(memberList);

		return false;
		
	}
	public boolean pwCheck(String memberLoginID,String memberLoginPw) {
		for(int i=0;i<memberList.size();i++) {
			if(memberList.get(i).getMemberID().equals(memberLoginID)) {
				if(memberList.get(i).getMemberPW().equals(memberLoginPw)) {
					
					return true;
				}
				
			}
		}
		return false;
		
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
	
	public boolean checkNewPw(String Pw, String newPw) {
		if(Pw.equals(newPw)) {System.out.println("�Է��Ͻ� ���ο� ��й�ȣ�� ������ ������ ��й�ȣ �Դϴ�.");return false;}
		return true;
	}
	public boolean checkNewId(String NewId,String memberLoginId) {
		if(NewId.equals(memberLoginId)) {
			System.out.println("������ ������ ���̵��Դϴ�. �ٸ����̵� �Է����ּ���");return false;}
		int idx=-1;
		for(int i=0;i<memberList.size();i++) {
			if(memberList.get(i).getMemberID().equals(memberLoginId)) {idx=i;break;}
		}
		
		for(int i=0;i<memberList.size();i++) {
			if(i!=idx) {
			
			if(memberList.get(i).getMemberID().equals(NewId)) {
				System.out.println("�̹� �����ϴ� ���̵��Դϴ�. �ٸ����̵� �Է����ּ���");return false;
			}
			}
		}
		return true;
	}
	public void setMemberName(String name, String newName, String memberLoginID) {
		int idx=-1;
		for(int i=0;i<memberList.size();i++) {
			if(memberList.get(i).getMemberID().equals(memberLoginID)) {
				idx=i;break;
			}
		}
		
		Member m=memberList.get(idx);
		m.setMemberName(newName);
		System.out.println("�̸� �����Ϸ� "+name+" ==> "+newName);
		
	}
	public boolean checkNewName(String name,String newName) {
		if(name.equals(newName)) {System.out.println("������ ������ �̸��Դϴ�");return false;}
		return true;
		
	}
	public String getname(String memberLoginID) {
		for(int i=0;i<memberList.size();i++) {
			if(memberLoginID.equals(memberList.get(i).getMemberID())) {
				return memberList.get(i).getMemberName();
			}
		}
		return null;
		
	}
	public void setMemberPw(String pw,String newPW, String memberLoginID) {
		int idx=-1;
		for(int i=0;i<memberList.size();i++) {
			if(memberList.get(i).getMemberID().equals(memberLoginID)) {
				idx=i;break;
			}
		}
		
		Member m=memberList.get(idx);
		m.setMemberPW(newPW);
		memberList.set(idx, m);
		System.out.println("PW �����Ϸ� "+pw+" ==> "+newPW);
	}
	public void setMemberID(String newId, String memberLoginID) {
		int idx=-1;
		for(int i=0;i<memberList.size();i++) {
			if(memberList.get(i).getMemberID().equals(memberLoginID)) {
				idx=i;break;
			}
		}
		Member m = memberList.get(idx);
		m.setMemberID(newId);
		memberList.set(idx, m);
	
		System.out.println("ID ���� �Ϸ� "+memberLoginID+" ==>  "+newId);
	}
	public void addMember(Member member) {
		memberList.add(member);
	}
	public int getNextNumber() {
		memberNumber += 1;
		return memberNumber;
	}
	public void deleteMemberError() {
		System.out.println("�ֹ��� ������ ��ǰ�� �����Ͽ� ȸ��Ż�� �Ұ��� �մϴ�. ����Ȯ���Ŀ� ȸ��Ż�� �����մϴ�.");
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

		Member member = new Member(getNextNumber(), "a", "a", "��ö��");
		addMember(member);
		member = new Member(getNextNumber(), "b", "b", "�̹ο�");
		addMember(member);
	}
}
