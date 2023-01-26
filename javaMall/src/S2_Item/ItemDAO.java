package S2_Item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class ItemDAO {

	
	private ArrayList<Item> itemList;
	private int itemNumber;
	public ItemDAO() {
		itemList = new ArrayList<Item>();
		itemNumber = 1000;
		setSampleData();
	}
	
	public void setSampleData() {
		
		String categoryData[] = {"����" , "�����" , "����" , "�����"};
		String itemNameData[] = {"�����" , "�ݶ�" , "���ڱ�" , "���̴�"};
		int priceData [] = {1000, 2000, 1500, 2500};
		for(int i = 0; i < categoryData.length; i++) {
			Item item = new Item(getNextItemNumber() , categoryData[i] ,itemNameData[i] , priceData[i]);
			itemList.add(item);
		}
	}
	
	public int getNextItemNumber() {
		itemNumber++;
		return itemNumber;
	}
	
	
	public boolean checkItemName(String itemName) {
		for(int i = 0; i < itemList.size(); i++) {
			if(itemList.get(i).getItemName().equals(itemName)) {
				return true;
			}
		}
		return false;
	}
	public String checkRemoveItemList(String itemName) {
		
		for(int i=0;i<itemList.size();i++) {
			if(itemList.get(i).getItemName().equals(itemName)) {
				return itemName;
			}
		}
		System.out.println("�������� �ʴ� �������Դϴ�");
		return null;
	}
	public boolean checkItemListSize() {
		if(itemList.size()==0) {
			System.out.println("�������� �������� �ʽ��ϴ�");
			return false;}
		return true;
	}
	public void removeItem(String itemName) {
		for(int i=0;i<itemList.size();i++) {
			if(itemList.get(i).getItemName().equals(itemName)) {
				itemList.remove(i);break;
			}
		}
		System.out.println("������ ���� �Ϸ�");
	}
	public void addItem(Item item) {
		itemList.add(item);
		
	}
	
	public void printItemList() {
		for(int i = 0; i < itemList.size();i++) {
			System.out.println(itemList.get(i));
		}
	}
	
	public void printItemList(ArrayList<Item>itemList  ) {
		for(int i = 0; i < itemList.size();i++) {
			System.out.println(i + 1 + ") " + itemList.get(i));
		}
	}
	
	public ArrayList<String> getCategoryList(){
		
		TreeSet<String> categorySet = new TreeSet<String>();
		for(int i = 0; i < itemList.size(); i++) {
			categorySet.add(itemList.get(i).getCategoryName());
		}
		ArrayList<String> categoryList = new ArrayList<String>();		
		Iterator<String> iter = categorySet.iterator();
		while(iter.hasNext()) {
			categoryList.add(iter.next());			
		}
		return categoryList;
	}
	
	public ArrayList<Item> getCategoryItemList(String category){
		ArrayList<Item> categoryItemList = new ArrayList<Item>();
		for(int i = 0; i < itemList.size(); i++) {
			Item item = itemList.get(i);
			if(category.equals(item.getCategoryName())){
				categoryItemList.add(item);
			}
		}	
		return categoryItemList;
	}
	
	
	
}
