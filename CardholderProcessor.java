import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
/**
* Write a class that provides methods for reading in the data file and
* generating the monthly reports of Cardholder objects.
*
* @author Alex Bonner
* @version 2/25/21
*/
public class CardholderProcessor {

   // fields
   private Cardholder[] card;
   private String[] inval; // holds invalid records from file
   
   // constructor
   /**
   * instantiates CardholderProcesser object.
   */
   public CardholderProcessor() {
      card = new Cardholder[0];
      inval = new String[0];
   }
   
   // methods
   
   /** 
   * returns Cardholder array. 
   * @return Cardholder[] field
   */ 
   public Cardholder[] getCardholderArray() {
      return card;
   }
   
   /** 
   * returns Cardholder array of invalid records.
   * @return String[] field
   */ 
   public String[] getInvalidRecords() {
      return inval;
   }

   /** 
   * increases capacity of Cardholder[] by one and adds Cardholder object to 
   * array.
   * @param car for Cardholder object
   */ 
   public void addCardholder(Cardholder car) {    
      card = Arrays.copyOf(card, card.length + 1);
      card[card.length - 1] = car;
   }
   
   /** 
   * increases capacity of String[] by one and adds String object to 
   * array.
   * @param bad for invalid object represented as string
   */ 
   public void addInvalidRecords(String bad) {
      inval = Arrays.copyOf(inval, inval.length + 1);
      inval[inval.length - 1] = bad;
   }
   
   /** 
   * processes Cardholder[] using original order from file and produces  
   * Monthly Rewards Club Report.
   * @return processed report as String
   */ 
   public String generateReport() {
      String temp = "-----------------------------\n"
         + "Monthly Cardholder Report\n"
         + "-----------------------------\n";
      for (Cardholder item : card) {
         temp += item + "";
      }  
      return temp;
   }

   /** 
   * processes Cardholder[] using natural order defined by Comparable interface
   * and produces Monthly Rewards Club Report.
   * @return processed report as String ordered by name
   */ 
   public String generateReportByName() {
      String temp = "-------------------------------------"
         + "\n Monthly Cardholder Report (by Name)\n"
         + "-------------------------------------\n";
         
      Arrays.sort(card);
      for (Cardholder item : card) {
         temp += item + "";
      }  
      return temp;   
   }
   
   /** 
   * processes Cardholder[] using alternate order defined by Comparator 
   * interface and produces Monthly Rewards Club Report.
   * @return processed report as String ordered by current balance
   */ 
   public String generateReportByCurrentBalance() {
      String temp = "----------------------------------------"
         + "\n Monthly Cardholder Report (by Current Balance)\n"
         + "----------------------------------------\n";
         
      Arrays.sort(card, new CurrentBalanceComparator());
      for (Cardholder item : card) {
         temp += item + "";
      }  
      return temp;  
   }
   
   /** 
   * processes inval String[] and produces Monthly Rewards Club Report.
   * @return processed report as String ordered by current balance
   */ 
   public String generateInvalidRecordsReport() {
      String temp = "-----------------------\n"
         + "Invalid Records Report\n"
         + "-----------------------\n";
         
      for (String item : inval) {
         temp += item + "\n";
      }  
      return temp;  
   }
   
   /**
   * accepts data file name as String, and goes through file line by line. Each 
   * line defines category, account number, name, previous balance, payment, 
   * and an undefined amount of purchases. This data is used to create a
   * Cardholder object for each valid line in the file. Category defines valid 
   * entries by: 1=Sapphire, 2=Diamond, 3=BlueDiamond. Checks for two 
   * exceptions of an invalid line: Invalid category and Wrong Number Format.
   * If a line is invalid, then that line is passed as String to invalid 
   * records array. 
   * @param fileName representing name of file to be read
   * @throws FileNotFoundException if file can't be found
   */
   public void readCardholderFile(String fileName) 
      throws FileNotFoundException {
      
      String cat = "", acctNum = "", name = "";
      int i = 0, count = 0;
      double prevBal = 0, payment = 0;
      double[] purchases = new double[0];
      
      Scanner scanFile = new Scanner(new File(fileName));
      while (scanFile.hasNextLine()) {
         String line = scanFile.nextLine();
         Scanner scanLine = new Scanner(line);
         scanLine.useDelimiter(";");
         try {
            while (scanLine.hasNext()) {
               cat = scanLine.next();
               acctNum = scanLine.next();
               name = scanLine.next();
               prevBal = Double.parseDouble(scanLine.next());
               payment = Double.parseDouble(scanLine.next());
               while (scanLine.hasNext()) {
                  purchases = Arrays.copyOf(purchases, purchases.length + 1);
                  purchases[i] = Double.parseDouble(scanLine.next());
                  i++;
               }  
               switch(cat) {
                  case "1":  
                     SapphireCardholder sap = 
                        new SapphireCardholder(acctNum, name); 
                     sap.setPayment(payment);
                     sap.setPrevBalance(prevBal);
                     sap.addPurchases(purchases);
                     card = Arrays.copyOf(card, card.length + 1);
                     card[count] = sap;
                     count++;
                     break;
                     
                  case "2":
                     DiamondCardholder diam = 
                        new DiamondCardholder(acctNum, name); 
                     diam.setPayment(payment);
                     diam.setPrevBalance(prevBal);
                     diam.addPurchases(purchases);
                     card = Arrays.copyOf(card, card.length + 1);
                     card[count] = diam;
                     count++;                 
                     break;
                     
                  case "3":
                     BlueDiamondCardholder blue = 
                        new BlueDiamondCardholder(acctNum, name); 
                     blue.setPayment(payment);
                     blue.setPrevBalance(prevBal);
                     blue.addPurchases(purchases);
                     card = Arrays.copyOf(card, card.length + 1);
                     card[count] = blue;
                     count++;
                     break;
                    
                  default:
                     throw new InvalidCategoryException();                  
               }
            }
         }           
         catch (NumberFormatException e) {
            String output = line + " *** invalid numeric value ***";
            inval = Arrays.copyOf(inval, inval.length + 1);
            inval[inval.length - 1] = output;
         }
         catch (InvalidCategoryException e) {
            String output = line + e.getMessage();
            inval = Arrays.copyOf(inval, inval.length + 1);
            inval[inval.length - 1] = output;
         }
      }
   }
}