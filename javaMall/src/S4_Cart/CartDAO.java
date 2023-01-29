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
		System.out.println("장바구니 내 해당 아이템 삭제 완료");
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
	System.out.println("주문내역이 존재하지 않습니다");return false;}
	

	public boolean checkOrderList(String itemName) {
		for (int i = 0; i < orderList.size(); i++) {
			if (orderList.get(i).getItemName().equals(itemName)) {
				System.out.println("이미 해당아이템을 주문한 고객이 존재해 아이템 삭제 불가능");
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
		System.out.println("총 금액:"+sum);
		
		
	}

	

	public void purchaseProduct(String memberLoginID) {
		
		addOrderList(memberLoginID);
		resetCartList(memberLoginID);
		System.out.println("주문이 완료되었습니다 장바구니 목록은 초기화됩니다");
	}

	public boolean checkCartList(String memberLoginID) {
		int count = 0;
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getMemberID().equals(memberLoginID)) {
				count++;
			}
		}
		if (count == 0) {
			System.out.println("장바구니에 담긴 아이템이 존재하지 않습니다");
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
		if(idx==-1) {System.out.println("존재하지않는 주문번호입니다");return;}
		System.out.println("수령확인 완료");
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
		if(orderList.size()==0) {System.out.println("출력할 주문내역이 존재하지 않습니다.");return;}
		System.out.println(orderList);
	}
	public void printReceiveList() {
		if(receiveList.size()==0) {System.out.println("출력할 수령확인내역이 존재하지 않습니다.");return;}
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
					System.out.println("삭제완료");
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
		System.out.println("삭제 완료");
	}

	public void showErrorCart() {
		System.out.println("장바구니 목록이 존재하지 않습니다");
	}

	public void insertItemName() {
		System.out.println("장바구니에서 삭제할 아이템 이름 입력");
	}

	public void rangeError(int count) {
		System.out.println("1에서 " + count + "사이 값으로 입력해주세요");
	}

	public void insertNum() {
		System.out.println("해당 아이템이 장바구니에 2개이상 존재합니다. 삭제할 개수를 입력 해 주세요");
	}

	public boolean checkCartListSize() {
		if (cartList.size() == 0) {System.out.println("아이템이 존재하지 않습니다.");
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
		System.out.println("없는 아이템입니다");
	}

	public void printOneCartList(ArrayList<Cart> oneCartList) {
		if (oneCartList.size() == 0) {
			System.out.println("장바구니에 담긴 아이템이 존재하지 않습니다");
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
