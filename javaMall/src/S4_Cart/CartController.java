package S4_Cart;

import java.util.ArrayList;
import java.util.Scanner;

import S0_MALL.MallController;
import S2_Item.Item;
import S_MyUtil.Util;

public class CartController {
	private CartController() {
	}

	static private CartController instance = new CartController();

	static public CartController getInstance() {
		return instance;
	}

	private CartDAO cartDAO;
	private Scanner scan;
	private MallController mallController;

	public void showErrorCart() {
		cartDAO.showErrorCart();

	}
	public void addReceiveList(String memberLoginID,int num) {
		cartDAO.addReceiveList(memberLoginID,num);
		
	}

	public boolean checkCartListSize() {
		return cartDAO.checkCartListSize();
	}

	public void init(CartDAO cartDAO) {
		this.cartDAO = cartDAO;
		scan = Util.scan;
		mallController = MallController.getInstance();
	}
	public void memberOrderListPrint(String memberLoginID) {
		cartDAO.memberOrderListPrint( memberLoginID);
		
	}
	public void printReceiveList() {
		cartDAO.printReceiveList();
	}

	public void printMemberOrderList() {
		cartDAO.printOrderList();
	}

	public void insertCart(Item item) {
		Cart cart = new Cart();
		cart.setNumber(cartDAO.getCartNumber());
		cart.setMemberID(mallController.getMemberLoginID());
		cart.setItemName(item.getItemName());
		cart.setItemPrice(item.getPrice());
		cartDAO.insertCart(cart);
	}

	public void menuCart() {

		while (true) {
			System.out.println("===[장바구니관리]===");
			System.out.println("1)장바구니출력 2)구입 3)삭제 0)뒤로가기");
			int sel = scan.nextInt();
			if (sel == 0) {
				break;
			} else if (sel == 1) {
				
				ArrayList<Cart> oneCartList = cartDAO.getOneCartList(mallController.getMemberLoginID());
				cartDAO.printOneCartList(oneCartList);
			} else if (sel == 2) {
				boolean check = cartDAO.checkCartList(mallController.getMemberLoginID());
				if (check == true) {
					cartDAO.sumPrice(mallController.getMemberLoginID());
					

					cartDAO.purchaseProduct(mallController.getMemberLoginID());
				}

			} else if (sel == 3) {
				boolean check = cartDAO.checkCartList(mallController.getMemberLoginID());
				if (check == false) {
					continue;
				}

				cartDAO.insertItemName();
				String itemName = scan.next();

				int count = cartDAO.countCartList(itemName, mallController.getMemberLoginID());
				if (count == 0) {
					cartDAO.CartListZero();
				}
				if (count == 1) {
					cartDAO.removeCartListOne(itemName, mallController.getMemberLoginID());

				} else if (count > 1) {
					cartDAO.insertNum();
					int num = scan.nextInt();
					if (num < 1 || num > count) {
						cartDAO.rangeError(count);
						continue;
					}
					cartDAO.removeCartList(itemName, mallController.getMemberLoginID(), num);

				}

			}
		}
	}
	public boolean checkOrderListMember() {
		return cartDAO.checkOrderListMember(mallController.getMemberLoginID());
			
		
	}

	public boolean checkOrderList(String itemName) {

		return cartDAO.checkOrderList(itemName);

	}
	public void removeCartListMember(String memberLoginID) {
		cartDAO.removeCartListMember(memberLoginID);
	}

	public void removeCartList(String itemName) {
		cartDAO.removeCartListItem(itemName);
	}
	public boolean orderListCheck(String memberLoginID) {
		return cartDAO.orderListCheck(memberLoginID);
		
	}
	

	public void printAllCartList() {
		cartDAO.printAllCartList();
	}

}
