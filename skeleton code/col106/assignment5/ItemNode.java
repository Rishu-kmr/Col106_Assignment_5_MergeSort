package col106.assignment5;

public class ItemNode implements Comparable<ItemNode>,ItemInterface{

	int itemId;
	String itemName;
	int stock;
	LinkedList<PurchaseNode> purchaseTransaction;

	public ItemNode(int itemId, String itemName, int stock){
		this.itemId = itemId;
		this.itemName = itemName;
		this.stock = stock;
		this.purchaseTransaction = new LinkedList<PurchaseNode>();
	}

	public int getItemId(){
		//Enter your code here
		return this.itemId;
	}

	public  String getItemName(){
		//Enter your code here
		return this.itemName;
	}

	public int getStockLeft(){
		//Enter your code here
		return this.stock;
	}

	public void addTransaction(PurchaseNode purchaseT){
		//Enter your code here
		this.stock = this.stock - purchaseT.numItemPurchased;
		purchaseTransaction.add(purchaseT);
	}

	public Node<PurchaseNode> getPurchaseHead(){
		//Enter your code here
		return purchaseTransaction.getHead();
	}

	@Override
	public int compareTo(ItemNode o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
