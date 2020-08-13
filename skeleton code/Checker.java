import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import static org.junit.Assert.*;
import java.io.IOException;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import col106.assignment5.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Checker {

  private boolean testCode(String testName) throws IOException{

    String outFile = "out/"+testName;
		String inFile = "in/"+testName;
		String ansFile = "ans/"+testName;

    String[] input = {inFile, outFile};

    PrintStream stdout = System.out;
    DriverCode driver=new DriverCode();
    try{
      driver.test(input);
    } catch(Exception e){
      e.printStackTrace();
    }

    System.setOut(stdout);
    return compare(ansFile, outFile);
  }

  boolean compare(String file1, String file2) throws IOException{

    File f1 = new File(file1);
    BufferedReader b1 = new BufferedReader(new FileReader(f1));
    String readLine1 = b1.readLine();

    File f2 = new File(file2);
    BufferedReader b2 = new BufferedReader(new FileReader(f2));
    String readLine2 = b2.readLine();

    while(readLine1!=null || readLine2!=null){
      if(readLine1==null || readLine2==null)
        return false;
      else if(!readLine1.equals(readLine2))
        return false;
      readLine1 = b1.readLine();
      readLine2 = b2.readLine();
    }

    return true;
  }

  @Test(expected = Test.None.class)
  public void test00_0P(){
  	try{
      assertTrue(testCode("test0"));
  	}	catch(Exception e){
  		System.out.println(e);
    }
  }

  @Test(expected = Test.None.class)
  public void test01_0P(){
  	try{
      assertTrue(testCode("test1"));
  	}	catch(Exception e){
  		System.out.println(e);
    }
  }

  @Test(expected = Test.None.class)
  public void test02_0P(){
  	try{
      assertTrue(testCode("test2"));
  	}	catch(Exception e){
  		System.out.println(e);
    }
  }

  @Test(expected = Test.None.class)
  public void test03_0P(){
  	try{
      assertTrue(testCode("test3"));
  	}	catch(Exception e){
  		System.out.println(e);
    }
  }

  @Test(expected = Test.None.class)
  public void test04_0P(){
  	try{
      assertTrue(testCode("test4"));
  	}	catch(Exception e){
  		System.out.println(e);
    }
  }

  @Test(expected = Test.None.class)
  public void test05_0P(){
  	try{
      assertTrue(testCode("test5"));
  	}	catch(Exception e){
  		System.out.println(e);
    }
  }

  @Test(expected = Test.None.class)
  public void test06_0P(){
  	try{
      assertTrue(testCode("test6"));
  	}	catch(Exception e){
  		System.out.println(e);
    }
  }

  @Test(expected = Test.None.class)
  public void test07_0P(){
  	try{
      assertTrue(testCode("test7"));
  	}	catch(Exception e){
  		System.out.println(e);
    }
  }

  @Test(expected = Test.None.class)
  public void test08_0P(){
  	try{
      assertTrue(testCode("test8"));
  	}	catch(Exception e){
  		System.out.println(e);
    }
  }

  @Test(expected = Test.None.class)
  public void test09_0P(){
  	try{
      assertTrue(testCode("test9"));
  	}	catch(Exception e){
  		System.out.println(e);
    }
  }

  @Test(expected = Test.None.class)
  public void test10_0P(){
  	try{
      assertTrue(testCode("test10"));
  	}	catch(Exception e){
  		System.out.println(e);
    }
  }

  @Test(expected = Test.None.class)
  public void test11_0P(){
  	try{
      assertTrue(testCode("test11"));
  	}	catch(Exception e){
  		System.out.println(e);
    }
  }

  @Test(expected = Test.None.class)
  public void test12_0P(){
  	try{
      assertTrue(testCode("test12"));
  	}	catch(Exception e){
  		System.out.println(e);
    }
  }

  @Test(expected = Test.None.class)
  public void test13_0P(){
  	try{
      assertTrue(testCode("test13"));
  	}	catch(Exception e){
  		System.out.println(e);
    }
  }

  @Test(expected = Test.None.class)
  public void test14_0P(){
  	try{
      assertTrue(testCode("test14"));
  	}	catch(Exception e){
  		System.out.println(e);
    }
  }

  @Test(expected = Test.None.class)
  public void test15_0P(){
  	try{
      assertTrue(testCode("test15"));
  	}	catch(Exception e){
  		System.out.println(e);
    }
  }

  @Test(expected = Test.None.class)
  public void test16_0P(){
  	try{
      assertTrue(testCode("test16"));
  	}	catch(Exception e){
  		System.out.println(e);
    }
  }

  @Test(expected = Test.None.class)
  public void test17_0P(){
  	try{
      assertTrue(testCode("test17"));
  	}	catch(Exception e){
  		System.out.println(e);
    }
  }
}
