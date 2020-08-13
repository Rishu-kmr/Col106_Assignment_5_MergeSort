import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import col106.assignment5.*;

public class DriverCode {
  static PrintStream out;

  private String toStringLLItemNode(LinkedList<ItemNode> lst){
    Node<ItemNode> node = lst.getHead();
    String ret = "[";
    while(node!=null){
      ret+=("("+node.getData().getItemId()+","+node.getData().getItemName()+")");
      node = node.getNext();
    }
    ret+="]";
    return ret;
  }

  public void test(String[] paramArrayOfString) throws IOException {
    File file = new File(paramArrayOfString[0]);
    out = new PrintStream(new FileOutputStream(paramArrayOfString[1], false), true);
    System.setOut(out);
    BufferedReader bufferedReader = null;
    try {
      bufferedReader = new BufferedReader(new FileReader(file));
      StockMgmt stockMgmt = new StockMgmt();
      String str;
      while ((str = bufferedReader.readLine()) != null) {
        String[] arrayOfString = str.split(" ");
        if (arrayOfString.length == 0) {
          System.err.println("Error parsing: " + str);
          return;
        }

        int categoryId, itemId, stock, day, month, year, numItemPurchased, category, day1, day2, month1, month2, year1, year2;
        String itemName;
        LinkedList<ItemNode> retItemList;
        LinkedList<CategoryNode> retCategoryList;
        switch (arrayOfString[0].strip()) {
          case "addItem":
            categoryId = Integer.parseInt(arrayOfString[1].strip());
            itemId = Integer.parseInt(arrayOfString[2].strip());
            itemName = arrayOfString[3].strip();
            stock = Integer.parseInt(arrayOfString[4].strip());
            stockMgmt.addItem(categoryId, itemId, itemName, stock);
            continue;
          case "addItemTransaction":
            categoryId = Integer.parseInt(arrayOfString[1].strip());
            itemId = Integer.parseInt(arrayOfString[2].strip());
            itemName = arrayOfString[3].strip();
            numItemPurchased = Integer.parseInt(arrayOfString[4].strip());
            day = Integer.parseInt(arrayOfString[5].strip());
            month = Integer.parseInt(arrayOfString[6].strip());
            year = Integer.parseInt(arrayOfString[7].strip());
            stockMgmt.addItemTransaction(categoryId, itemId, itemName, numItemPurchased, day, month, year);
            continue;
          case "sortByLastDate":
            retItemList = stockMgmt.sortByLastDate();
            System.out.println(this.toStringLLItemNode(retItemList));
            continue;
          case "sortByPurchasePeriod":
            day1 = Integer.parseInt(arrayOfString[1].trim());
            month1 = Integer.parseInt(arrayOfString[2].trim());
            year1 = Integer.parseInt(arrayOfString[3].trim());
            day2 = Integer.parseInt(arrayOfString[4].trim());
            month2 = Integer.parseInt(arrayOfString[5].trim());
            year2 = Integer.parseInt(arrayOfString[6].trim());
            retItemList = stockMgmt.sortByPurchasePeriod(day1, month1, year1, day2, month2, year2);
            System.out.println(this.toStringLLItemNode(retItemList));
            continue;
          case "sortByStockForCategory":
            category = Integer.parseInt(arrayOfString[1].strip());
            retItemList = stockMgmt.sortByStockForCategory(category);
            System.out.println(this.toStringLLItemNode(retItemList));
            continue;
          case "filterByCategorySortByDate":
            category = Integer.parseInt(arrayOfString[1].strip());
            retItemList = stockMgmt.filterByCategorySortByDate(category);
            System.out.println(this.toStringLLItemNode(retItemList));
            continue;
          case "checkMergeSort":
            retItemList = stockMgmt.checkMergeSort();
            System.out.println(this.toStringLLItemNode(retItemList));
            break;
          default:
            System.err.println("Unknown command: ");
            break;
        }
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}
