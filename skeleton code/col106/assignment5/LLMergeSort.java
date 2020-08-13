package col106.assignment5;

import java.util.Comparator;

/*
Implementation of MergeSort Algorithm :
    1. you get linked list of size <=1 you just return the list as it is already sorted
    2. Find mid node using findSplit method(Don't forget to add mid element to globalList before returning it)
    3. Create two LinkedList lt (with head = head and tail = mid) and rt (with head = mid.next and tail = tail)
    4. Now recursively MergSort lt and rt Linked lists(Maintain this order)
    5. Now merge these two lists that we got from recursive calls using given crieteria for ordering
    6. Return merged Linked list
*/

public class LLMergeSort <T extends Comparable>  {

  LinkedList<T>  globalList = new LinkedList<T>();

  //CALL THIS METHOD AFTER EVERY CALL OF findSplit and DO NOT MODIFY THIS METHOD
  public void adjustGlobalPointer(T node){
      globalList.add(node);
  }

  // Utility function to get the middle of the linked list
  public Node<T> findSplit(LinkedList<T>  lst) {
    //find middle node of LL :
    Node<T> middle = lst.getHead();
    //Enter your code here
    int listSize = lst.getSize();
    int middleIndex = listSize/2;
    if(listSize==0 || listSize==1) {
    	return middle;
    }
//    Node<T> traversenode;
    int counter = 1;
    while(counter < middleIndex) {
    	middle = middle.next;
    	counter++;
    }
    if(listSize==(2*middleIndex+1)) {
    	middle = middle.next;
    }
    //!!!!!*****DO NOT REMOVE THIS METHOD CALL (change the argument apprpriately)*****!!!!!
    adjustGlobalPointer(middle.getData()); //Add object of ItemNode after finding mid in each call
    return middle;
  }

//  public LinkedList<T> CustomMergeSort(LinkedList<T> lst){
//	  if(lst.getHead() == null || lst.getHead().next==null) {
//		  return lst;
//	  }
//	  Node<T> middle = findSplit(lst);
//	  Node<T> nextOfMiddle = middle.next;
//	  middle.next = null;
//	  LinkedList<T> leftLinkedList = makeLinkedList(lst.getHead());
//	  LinkedList<T> rightLinkedList = makeLinkedList(nextOfMiddle);
//	  LinkedList<T> leftLinkedListMerge = CustomMergeSort(leftLinkedList);
//	  LinkedList<T> rightLinkedListMerge = CustomMergeSort(rightLinkedList);
//	  LinkedList<T> finalList = arrangeSortedMergeLL(leftLinkedListMerge,rightLinkedListMerge);
//	  
//    return finalList;
//  }
  
  public LinkedList<T>  MergeSort(LinkedList<T>  lst, int compare, DateNode dateObj1, DateNode dateObj2) {
    //Recursively Apply MergeSort, by calling function findSplit(..) to find middle node to split
    //Enter your code here
	  if(lst.getHead() == null || lst.getHead().next==null) {
		  return lst;
	  }
	  Node<T> middle = findSplit(lst);
	  Node<T> nextOfMiddle = middle.next;
	  middle.next = null;
	  LinkedList<T> leftLinkedList = makeLinkedList(lst.getHead());
	  LinkedList<T> rightLinkedList = makeLinkedList(nextOfMiddle);
	  LinkedList<T> leftLinkedListMerge = MergeSort(leftLinkedList,compare,dateObj1,dateObj2);
	  LinkedList<T> rightLinkedListMerge = MergeSort(rightLinkedList,compare,dateObj1,dateObj2);
	  LinkedList<T> finalList = arrangeSortedMergeLL(leftLinkedListMerge,rightLinkedListMerge,compare,dateObj1,dateObj2);
	  
    return finalList;
  }
  
  public LinkedList<T> arrangeSortedMergeLL(LinkedList<T> left,LinkedList<T> right,int compare, DateNode dateObj1, DateNode dateObj2){
	  Node<T> sortedMergeHead = sortedMerge(left.getHead(),right.getHead(),compare,dateObj1,dateObj2);
	  LinkedList<T> mergedList = makeLinkedList(sortedMergeHead);
	  return mergedList;
  }
  
  public Node<T> sortedMerge(Node<T> left, Node<T> right,int compare, DateNode dateObj1, DateNode dateObj2) {
	  Node<T> result = null;
	  if(left==null) {
		  return right;
	  }
	  if(right==null) {
		  return left;
	  }
	  if(compare==1) {
		  if(findLastDateOfPurchase(((ItemNode)left.data).purchaseTransaction.getHead()).compareTo(findLastDateOfPurchase(((ItemNode)right.data).purchaseTransaction.getHead()))==1) {
			  result = right;
			  result.next = sortedMerge(left,right.next,compare,dateObj1,dateObj2);
		  }
		  else if(findLastDateOfPurchase(((ItemNode)left.data).purchaseTransaction.getHead()).compareTo(findLastDateOfPurchase(((ItemNode)right.data).purchaseTransaction.getHead()))==-1) {
			  result = left;
			  result.next = sortedMerge(left.next,right,compare,dateObj1,dateObj2);
		  }
		  else if(findLastDateOfPurchase(((ItemNode)left.data).purchaseTransaction.getHead()).compareTo(findLastDateOfPurchase(((ItemNode)right.data).purchaseTransaction.getHead()))==0) {
			  if((((ItemNode)left.data).itemName).compareTo(((ItemNode)right.data).itemName)>0) {
				  result = left;
//				  result.next = right;
				  result.next = sortedMerge(left.next,right,compare,dateObj1,dateObj2);
			  }
			  else if((((ItemNode)left.data).itemName).compareTo(((ItemNode)right.data).itemName)<0) {
				  result = right;
//				  result.next = left;
				  result.next = sortedMerge(left,right.next,compare,dateObj1,dateObj2);
			  }
			  else if((((ItemNode)left.data).itemName).compareTo(((ItemNode)right.data).itemName)==0) {
				  result = right;
//				  result.next = left;
				  result.next = sortedMerge(left,right.next,compare,dateObj1,dateObj2);
			  }
			  
		  }
	  }
	  if(compare==2) {
		  // code for purchase period
		  if((findValueToCompare((ItemNode)left.data,dateObj1,dateObj2)) > findValueToCompare((ItemNode)right.data,dateObj1,dateObj2)){
			  result = right;
			  result.next = sortedMerge(left,right.next,compare,dateObj1,dateObj2);
		  }
		  else if((findValueToCompare((ItemNode)left.data,dateObj1,dateObj2)) < findValueToCompare((ItemNode)right.data,dateObj1,dateObj2)){
			  result = left;
			  result.next = sortedMerge(left.next,right,compare,dateObj1,dateObj2);
		  }
		  else if((findValueToCompare((ItemNode)left.data,dateObj1,dateObj2)) == findValueToCompare((ItemNode)right.data,dateObj1,dateObj2)){
			  if((((ItemNode)left.data).itemName).compareTo(((ItemNode)right.data).itemName)>0) {
				  result = left;
//				  result.next = right;
				  result.next = sortedMerge(left.next,right,compare,dateObj1,dateObj2);
			  }
			  else if((((ItemNode)left.data).itemName).compareTo(((ItemNode)right.data).itemName)<0) {
				  result = right;
//				  result.next = left;
				  result.next = sortedMerge(left,right.next,compare,dateObj1,dateObj2);
			  }
		  }
	  }
	  if(compare==3) {
		  if(((ItemNode)left.data).stock>((ItemNode)right.data).stock){
			  result = left;
			  result.next = sortedMerge(left.next,right,compare,dateObj1,dateObj2);
		  }
		  else if(((ItemNode)left.data).stock<((ItemNode)right.data).stock){
			  result = right;
			  result.next = sortedMerge(left,right.next,compare,dateObj1,dateObj2);
		  }
		  else if(((ItemNode)left.data).stock==((ItemNode)right.data).stock){
			  if((((ItemNode)left.data).itemName).compareTo(((ItemNode)right.data).itemName)>0){
				  result = left;
//				  result.next = right;
				  result.next = sortedMerge(left.next,right,compare,dateObj1,dateObj2);
			  }
			  else if((((ItemNode)left.data).itemName).compareTo(((ItemNode)right.data).itemName)<0){
				  result = right;
//				  result.next = left;
				  result.next = sortedMerge(left,right.next,compare,dateObj1,dateObj2);
			  }
		  }
	  }
	  if(compare==4) {
		  if((((PurchaseNode)((ItemNode)left.data).purchaseTransaction.getTail().data).dateobj).compareTo(((PurchaseNode)((ItemNode)right.data).purchaseTransaction.getTail().data).dateobj)==1) {
			  result = left;
			  result.next = sortedMerge(left.next,right,compare,dateObj1,dateObj2);
		  }
		  else if((((PurchaseNode)((ItemNode)left.data).purchaseTransaction.getTail().data).dateobj).compareTo(((PurchaseNode)((ItemNode)right.data).purchaseTransaction.getTail().data).dateobj)==-1) {
			  result = right;
			  result.next = sortedMerge(left,right.next,compare,dateObj1,dateObj2);
		  }
		  else if((((PurchaseNode)((ItemNode)left.data).purchaseTransaction.getTail().data).dateobj).compareTo(((PurchaseNode)((ItemNode)right.data).purchaseTransaction.getTail().data).dateobj)==0) {
			  if((((ItemNode)left.data).itemName).compareTo(((ItemNode)right.data).itemName)>0) {
				  result = left;
//				  result.next = right;
				  result.next = sortedMerge(left.next,right,compare,dateObj1,dateObj2);
			  }
			  else if((((ItemNode)left.data).itemName).compareTo(((ItemNode)right.data).itemName)<0) {
				  result = right;
//				  result.next = left;
				  result.next = sortedMerge(left,right.next,compare,dateObj1,dateObj2);
			  }
		  }
	  }
	  return result;
  }
  
  public DateNode findLastDateOfPurchase(Node<PurchaseNode> node){
	  DateNode dateobj = ((PurchaseNode)node.data).dateobj;
	  while(node!=null) {
		  if(((PurchaseNode)node.data).dateobj.compareTo(dateobj)==1){
			  dateobj = ((PurchaseNode)node.data).dateobj;
		  }
		  node = node.next;
	  }
	  return dateobj;
  }
  
  public float findValueToCompare(ItemNode node,DateNode dateObj1, DateNode dateObj2) {
	  Node<PurchaseNode> purchaseNode = node.getPurchaseHead();
	  LinkedList<PurchaseNode> list = new LinkedList<PurchaseNode>();
	  int itemPurchased = 0;
	  while(purchaseNode!=null) {
		  if((((((PurchaseNode)purchaseNode.data).dateobj.compareTo(dateObj1)==1) && (((PurchaseNode)purchaseNode.data).dateobj.compareTo(dateObj2)==-1))) || (((PurchaseNode)purchaseNode.data).dateobj.compareTo(dateObj1)==0) || (((PurchaseNode)purchaseNode.data).dateobj.compareTo(dateObj2)==0)) {
			  itemPurchased += ((PurchaseNode)purchaseNode.data).numItemPurchased;
			  list.add(purchaseNode.data);
		  }
		  purchaseNode = purchaseNode.next;
	  }
	  if(list.getSize()==0) {
		  PurchaseNode purNode = new PurchaseNode(0,1,8,1970);
		  list.add(purNode);
	  }
	  DateNode diff1 = list.getHead().data.dateobj;
	  DateNode diff2 = list.getHead().data.dateobj;
	  Node<PurchaseNode> n = list.getHead();
	  while(n!=null) {
		  if(((PurchaseNode)n.data).dateobj.compareTo(diff1)==-1){
			  diff1 = ((PurchaseNode)n.data).dateobj;
		  }
		  if(((PurchaseNode)n.data).dateobj.compareTo(diff2)==1){
			  diff2 = ((PurchaseNode)n.data).dateobj;
		  }
		  n = n.next;
	  }
	  float diffYear = (diff2.year - diff1.year)+1;
	  return (float) (itemPurchased/diffYear);
  }
  
  public LinkedList<T> makeLinkedList(Node<T> node){
	  LinkedList<T> list = new LinkedList<T>();
	  while(node!=null) {
		  list.add(node.data);
		  node = node.next;
	  }
	  return list;
  }

  //DO NOT CALL OR MODIFY THESE METHODS IN YOUR CALL THIS IS FOR USE IN DRIVER CODE
  public LinkedList<T> getGlobalList() {
    return this.globalList;
  }

  public void clearGlobalList(){
    globalList  = new LinkedList<>();
  }

}
