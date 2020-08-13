package col106.assignment5;

public class StockMgmt implements StockMgmtInterface {
	//!!!!!!!*********DON'T CREATE YOUR OWN OBJECTS OF LLMergeSort*********!!!!!!!
	//use these only this object to sort
	LLMergeSort mergeSortobj;

	LinkedList<CategoryNode> categories;

	//DO NOT MNODIFY THIS CONSTRUCTOR
	public StockMgmt() {

		mergeSortobj = new LLMergeSort();
		categories = new LinkedList<CategoryNode>();

		categories.add(new CategoryNode(1, "mobile"));
		categories.add(new CategoryNode(2, "utensils"));
		categories.add(new CategoryNode(3, "sanitary"));
		categories.add(new CategoryNode(4, "medicalEquipment"));
		categories.add(new CategoryNode(5, "clothes"));

	}
	

	public void addItem(int categoryId, int itemId, String itemName, int stock) {
		//Your code goes here
		Node<CategoryNode> node = categories.getHead();
		while(node!=null) {
			if(node.data.categoryId==categoryId) {
				break;
			}
			node=node.next;
		}
		ItemNode itemNode = new ItemNode(itemId,itemName,stock);
		node.data.itemList.add(itemNode);
		
	}

	public void addItemTransaction(int categoryId, int itemId, String itemName, int numItemPurchased, int day, int month, int year) {
		//Your code goes here
		Node<CategoryNode> categoryNode = categories.getHead();
		while(categoryNode!=null) {
			if(categoryNode.data.categoryId==categoryId) {
				break;
			}
			categoryNode=categoryNode.next;
		}
		Node<ItemNode> itemNode =categoryNode.data.itemList.getHead();
		while(itemNode!=null) {
			if(itemNode.data.itemId==itemId) {
				break;
			}
			itemNode = itemNode.next;
		}
		PurchaseNode purchaseNode = new PurchaseNode(numItemPurchased,day,month,year);
		itemNode.data.addTransaction(purchaseNode);
//		itemNode.data.purchaseTransaction.add(purchaseNode);
	}

	//Query 1
	public LinkedList<ItemNode> sortByLastDate() {
		//Your code goes here
		LinkedList<ItemNode> sortByLastDateList = new LinkedList<ItemNode>();
		Node<CategoryNode> categoryNodeHead = categories.getHead();
		while(categoryNodeHead!=null) {
			Node<ItemNode> itemNodeHead = categoryNodeHead.data.itemList.getHead();
			while(itemNodeHead!=null) {
				if(itemNodeHead.data.purchaseTransaction.getSize()==0) {
					PurchaseNode purchaseNode = new PurchaseNode(0,1,8,1970);
					itemNodeHead.data.purchaseTransaction.add(purchaseNode);
				}
				sortByLastDateList.add(itemNodeHead.data);
				itemNodeHead = itemNodeHead.next;
			}
			categoryNodeHead = categoryNodeHead.next;
		}
		LLMergeSort llmergeSort = new LLMergeSort();
		LinkedList<ItemNode> finalList = mergeSortobj.MergeSort(sortByLastDateList,1,null,null);
		return finalList;
	}

	//Query 2
	public LinkedList<ItemNode> sortByPurchasePeriod(int day1, int month1, int year1, int day2, int month2, int year2) {
		//Your code goes here
		LinkedList<ItemNode> sortByPurchasePeriodList = new LinkedList<ItemNode>();
		Node<CategoryNode> categoryNodeHead = categories.getHead();
		while(categoryNodeHead!=null) {
			Node<ItemNode> itemNodeHead = categoryNodeHead.data.itemList.getHead();
			while(itemNodeHead!=null) {
				if(itemNodeHead.data.purchaseTransaction.getSize()==0) {
					PurchaseNode purchaseNode = new PurchaseNode(0,1,8,1970);
					itemNodeHead.data.purchaseTransaction.add(purchaseNode);
				}
				sortByPurchasePeriodList.add(itemNodeHead.data);
				itemNodeHead = itemNodeHead.next;
			}
			categoryNodeHead = categoryNodeHead.next;
		}
		DateNode dateObj1 = new DateNode(day1,month1,year1);
		DateNode dateObj2 = new DateNode(day2,month2,year2);
		LLMergeSort llmergeSort = new LLMergeSort();
		LinkedList<ItemNode> finalList = mergeSortobj.MergeSort(sortByPurchasePeriodList,2,dateObj1,dateObj2);
		return finalList;
	}

	//Query 3
	public LinkedList<ItemNode> sortByStockForCategory(int category) {
		//Your code goes here
		LinkedList<ItemNode> sortByStockForCategoryList = new LinkedList<ItemNode>();
		Node<CategoryNode> categoryNodeHead = categories.getHead();
		while(categoryNodeHead!=null) {
			if(categoryNodeHead.data.categoryId==category) {
				break;
			}
			categoryNodeHead = categoryNodeHead.next;
		}
		Node<ItemNode> itemNodeHead = categoryNodeHead.data.itemList.getHead();
		while(itemNodeHead!=null) {
			sortByStockForCategoryList.add(itemNodeHead.data);
			itemNodeHead = itemNodeHead.next;
		}
		LLMergeSort llmergeSort = new LLMergeSort();
		LinkedList<ItemNode> finalList = mergeSortobj.MergeSort(sortByStockForCategoryList,3,null,null);
		return finalList;
	}

	//Query 4
	public LinkedList<ItemNode> filterByCategorySortByDate(int category) {
		//Your code goes here
		LinkedList<ItemNode> filterByCategorySortByDateList = new LinkedList<ItemNode>();
		Node<CategoryNode> categoryNodeHead = categories.getHead();
		while(categoryNodeHead!=null) {
			if(categoryNodeHead.data.categoryId==category) {
				break;
			}
			categoryNodeHead = categoryNodeHead.next;
		}
		Node<ItemNode> itemNodeHead = categoryNodeHead.data.itemList.getHead();
		while(itemNodeHead!=null) {
			if(itemNodeHead.data.purchaseTransaction.getSize()==0) {
				PurchaseNode purchaseNode = new PurchaseNode(0,1,8,1970);
				itemNodeHead.data.purchaseTransaction.add(purchaseNode);
			}
			filterByCategorySortByDateList.add(itemNodeHead.data);
			itemNodeHead = itemNodeHead.next;
		}
		LLMergeSort llmergeSort = new LLMergeSort();
		LinkedList<ItemNode> finalList = mergeSortobj.MergeSort(filterByCategorySortByDateList,4,null,null);
		return finalList;
	}

	//!!!!!*****DO NOT MODIFY THIS METHOD*****!!!!!//
	public LinkedList<ItemNode> checkMergeSort() {
		LinkedList<ItemNode> retLst = mergeSortobj.getGlobalList();
		mergeSortobj.clearGlobalList();
		return retLst;
	}
}
