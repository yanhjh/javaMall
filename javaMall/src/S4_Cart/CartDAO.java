package S4_Cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartDAO {

	private ArrayList<Cart> cartList;
	private ArrayList<Cart> orderList;
	private ArrayList<Cart> receiveList;
	
	private int cartNumber;

	public CartDAO() {
		cartList = new ArrayList<Cart>();
		orderList = new ArrayList<Cart>();
		receiveList =new ArrayList<Cart>();
		
		cartNumber = 1000;
	}
	

	public int getCartNumber() {
		cartNumber += 1;
		return cartNumber;
	}

	public void removeCartListItem(String itemName) {
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getItemName().equals(itemName)) {
				cartList.remove(i);
				i--;
			}
		}
		System.out.println("��ٱ��� �� �ش� ������ ���� �Ϸ�");
	}
	public void removeCartListMember(String memberLoginID) {
		for(int i=0;i<cartList.size();i++) {
			if(cartList.get(i).getMemberID().equals(memberLoginID)) {
				cartList.remove(i);i--;
			}
		}
	}
	
	public boolean orderListCheck(String memberLoginID) {
		int count=0;
		for(int i=0;i<orderList.size();i++) {
			if(orderList.get(i).getMemberID().equals(memberLoginID)) {
				count++;
			}
		}
		if(count!=0) {return true;}
	System.out.println("�ֹ������� �������� �ʽ��ϴ�");return false;}
	

	public boolean checkOrderList(String itemName) {
		for (int i = 0; i < orderList.size(); i++) {
			if (orderList.get(i).getItemName().equals(itemName)) {
				System.out.println("�̹� �ش�������� �ֹ��� ���� ������ ������ ���� �Ұ���");
				return false;
			}

		}

		return true;

	}
	public boolean checkOrderListMember(String loginID) {
		for(int i=0;i<orderList.size();i++) {
			if(orderList.get(i).getMemberID().equals(loginID)) {return false;}
			
		}
		return true;
	}

	public void insertCart(Cart cart) {
		cartList.add(cart);
	}
	public void sumPrice(String memberLoginID) {
		int sum=0;
		for(int i=0;i<cartList.size();i++) {
			if(cartList.get(i).getMemberID().equals(memberLoginID)) {
				sum+=cartList.get(i).getItemPrice();
				
			}
		}
		System.out.println("�� �ݾ�:"+sum);
		
		
	}

	

	public void purchaseProduct(String memberLoginID) {
		
		addOrderList(memberLoginID);
		resetCartList(memberLoginID);
		System.out.println("�ֹ��� �Ϸ�Ǿ����ϴ� ��ٱ��� ����� �ʱ�ȭ�˴ϴ�");
	}

	public boolean checkCartList(String memberLoginID) {
		int count = 0;
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getMemberID().equals(memberLoginID)) {
				count++;
			}
		}
		if (count == 0) {
			System.out.println("��ٱ��Ͽ� ��� �������� �������� �ʽ��ϴ�");
			return false;
		}
		return true;

	}

	public void resetCartList(String memberLoginID) {
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getMemberID().equals(memberLoginID)) {
				cartList.remove(i);
				i--;
			}
		}
	}
	public void addReceiveList(String memberLoginID,int num) {
		int idx=-1;
		for(int i=0;i<orderList.size();i++) {
			if(orderList.get(i).getMemberID().equals(memberLoginID)) {
				if(orderList.get(i).getNumber()==num) {
					idx=i;
					
				}
			}
		}
		if(idx==-1) {System.out.println("���������ʴ� �ֹ���ȣ�Դϴ�");return;}
		System.out.println("����Ȯ�� �Ϸ�");
		receiveList.add(orderList.get(idx));
		orderList.remove(idx);
		
		
		
	}
	public void memberOrderListPrint(String memberLoginID) {
		for(int i=0;i<orderList.size();i++) {
			if(orderList.get(i).getMemberID().equals(memberLoginID)) {
				System.out.println(orderList.get(i));
			}
		}
		
	}

	public void printOrderList() {
		if(orderList.size()==0) {System.out.println("����� �ֹ������� �������� �ʽ��ϴ�.");return;}
		System.out.println(orderList);
	}
	public void printReceiveList() {
		if(receiveList.size()==0) {System.out.println("����� ����Ȯ�γ����� �������� �ʽ��ϴ�.");return;}
		System.out.println(receiveList);
	}

	public void addOrderList(String memberLoginID) {
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getMemberID().equals(memberLoginID)) {
				orderList.add(cartList.get(i));
			}
		}
	}

	public ArrayList<Cart> getOneCartList(String memberLoginID) {
		ArrayList<Cart> oneCartList = new ArrayList<Cart>();

		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getMemberID().equals(memberLoginID)) {
				oneCartList.add(cartList.get(i));
			}
		}

		return oneCartList;
	}

	public void removeCartList(String itemName, String memberLoginID, int num) {
		int cnt = 0;
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getItemName().equals(itemName) && cartList.get(i).getMemberID().equals(memberLoginID)) {
				cartList.remove(i);
				cnt++;
				i--;
				if (cnt == num) {
					System.out.println("�����Ϸ�");
					return;
				}
			}
		}
	}

	public void removeCartListOne(String itemName, String memberLoginID) {

		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getItemName().equals(itemName) && cartList.get(i).getMemberID().equals(memberLoginID)) {
				cartList.remove(i);

			}

		}
		System.out.println("���� �Ϸ�");
	}

	public void showErrorCart() {
		System.out.println("��ٱ��� ����� �������� �ʽ��ϴ�");
	}

	public void insertItemName() {
		System.out.println("��ٱ��Ͽ��� ������ ������ �̸� �Է�");
	}

	public void rangeError(int count) {
		System.out.println("1���� " + count + "���� ������ �Է����ּ���");
	}

	public void insertNum() {
		System.out.println("�ش� �������� ��ٱ��Ͽ� 2���̻� �����մϴ�. ������ ������ �Է� �� �ּ���");
	}

	public boolean checkCartListSize() {
		if (cartList.size() == 0) {System.out.println("�������� �������� �ʽ��ϴ�.");
			return false;
		}
		return true;
	}

	public int countCartList(String itemName, String memberLoginID) {
		int count = 0;
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getItemName().equals(itemName) && cartList.get(i).getMemberID().equals(memberLoginID)) {
				count++;
			}
		}

		return count;
	}

	public void CartListZero() {
		System.out.println("���� �������Դϴ�");
	}

	public void printOneCartList(ArrayList<Cart> oneCartList) {
		if (oneCartList.size() == 0) {
			System.out.println("��ٱ��Ͽ� ��� �������� �������� �ʽ��ϴ�");
			return;
		}
		for (int i = 0; i < oneCartList.size(); i++) {
			System.out.println(i + 1 + ")" + oneCartList.get(i));
		}
	}

	public void printAllCartList() {
		for (int i = 0; i < cartList.size(); i++) {
			System.out.println(i + 1 + ")" + cartList.get(i));
		}
	}

}
