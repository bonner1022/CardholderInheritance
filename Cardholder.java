import java.util.Arrays;
import java.text.DecimalFormat;
/**
* Create abstract cardholder parent class from which other cardholder classes
* can derive info from here and also add their own data. This class directly 
* stores account number and name of customer. It keeps track of an array of
* purchases made by customer, previous balance of their account and their
* last payment to the account. Calculates current balance, interest on the 
* account, purchase points and a minimum payment to be made next time. 
*
* @author Alex Bonner
* @version 2/25/2021
*/

public abstract class Cardholder implements Comparable<Cardholder> {

   // fields
   protected double prevBalance = 0, payment = 0;
   protected double[] purchases; 
   protected String category = "", acctNumber = "", name = "";
   
   /** sets interest rate of bank to 1%. **/
   public static final double INTEREST_RATE = 0.01;
   
   // constructor
   /** 
   * Instantiates cardholder object with acct number and name.
   * @param acctNumberIn for client account number
   * @param nameIn for client name
   */
   public Cardholder(String acctNumberIn, String nameIn) {
      acctNumber = acctNumberIn;
      name = nameIn;
      purchases = new double[0];
   }
   
   // methods
   /**
   * getter method for account number.
   * @return acct number of client
   */
   public String getAcctNumber() {
      return acctNumber;
   }
   
   // methods
   /**
   * getter method for name.
   * @return name of client
   */
   public String getName() {
      return name;
   }
   
   // methods
   /**
   * getter method for previous balance.
   * @return previous balance of client
   */
   public double getPrevBalance() {
      return prevBalance;
   }
   
   // methods
   /**
   * getter method for payment.
   * @return payment amount of client
   */
   public double getPayment() {
      return payment;
   }
   
   // methods
   /**
   * getter method for array of purchases.
   * @return purchases array of client
   */
   public double[] getPurchases() {
      return purchases;
   }
   
   // methods
   /**
   * setter method for account number.
   * @param num for client account number
   */
   public void setAcctNumber(String num) {
      acctNumber = num;
   }
   
   /**
   * setter method for name.
   * @param input for client name
   */
   public void setName(String input) {
      name = input;
   }
   
   /**
   * setter method for previous balance.
   * @param bal for client balance
   */
   public void setPrevBalance(double bal) {
      prevBalance = bal;
   }
   
   /**
   * setter method for payment amount.
   * @param pay for client payment made
   */
   public void setPayment(double pay) {
      payment = pay;
   }
   
   /**
   * setter method for array of purchases.
   * @param purch for client purchases array
   */
   public void setPurchases(double[] purch) {
      purchases = purch;
   }
   
   /**
   * able to add an unsignified amount of purchases to the purchases array.
   * @param list for variable length parameter list of possible purchases made
   */
   public void addPurchases(double... list) {
      purchases = Arrays.copyOf(purchases, list.length);
      purchases = list;
   } 
   
   /**
   * able to delete an unsignified amount of purchases to the purchases array.
   * if the amount from the list is present in the array, then that value is 
   * deleted. If not, then nothing happens.
   * @param list for variable length parameter list of possible purchases made
   */
   public void deletePurchases(double... list) {
      double[] temp = purchases;
      for (int i = 0; i < temp.length; i++) {
         for (double per : list) {
            if (temp[i] == per) {
               temp = delete(temp, i);
               if (temp.length < (i + 1)) {
                  break;
               }
            }
         }
      }
   }
   
   /**
   * calculates amount of interest due to account.
   * @return interest amount
   */
   public double interest() {
      return (prevBalance - payment) * INTEREST_RATE;
   }
   
   /**
   * calculates total values in the purchases array.
   * @return total amount 
   */
   public double totalPurchases() {
      double sum = 0;
      for (double purch : purchases) {
         sum += purch;
      }
      return sum;
   }
   
   /**
   * calculates balance on the account without considering payment.
   * @return balance
   */
   public double balance() {
      double inter = interest();
      return (prevBalance + inter) + totalPurchases();
   }
   
   /**
   * calculates balance on the account considering payment.
   * @return current balance
   */
   public double currentBalance() {
      return prevBalance - payment + interest() + totalPurchases();
   }
   
   /**
   * calculates 3% of current balance which must be paid next.
   * @return minimum payment
   */
   public double minPayment() {
      return currentBalance() * .03;
   }
   
   /**
   * abstract method that categories use to reward members.
   * @return clients points
   */
   public abstract int purchasePoints(); 
   
   /**
   * Comparable interface. Compares a Cardholder object to another
   * based on name.
   * @param other for Cardholder object to compare to
   * @return 0 if two objects are same, not 0 if not the same
   */
   public int compareTo(Cardholder other) {
      return name.compareToIgnoreCase(other.getName());
   }
   
   /**
   * prints information detailed above for each cardholder object that is 
   * created. 
   * @return string of info
   */
   public String toString() {
      String output = "";
      DecimalFormat df = new DecimalFormat("#,##0.00");
      DecimalFormat pf = new DecimalFormat("#,##0");
      
      output = category + "\nAcctNo/Name: " + acctNumber + " " + name 
         + "\nPrevious Balance: " + df.format(prevBalance) 
         + "\nPayment: (" + df.format(payment) + ")"
         + "\nInterest: " + df.format(interest()) 
         + "\nNew Purchases: " + df.format(totalPurchases()) 
         + "\nCurrent Balance: " + df.format(currentBalance()) 
         + "\nMinimum Payment: " + df.format(minPayment()) 
         + "\nPurchase Points: " + purchasePoints() + "\n\n";
       
      return output;
   }
   
   /**
   * private class called by deletePurchases() method. deletes the purchase
   * denoted at index and alters size of array. 
   * @param purch - array of purchases
   * @param index - which item needs to be deleted
   * @return new array with proper length.
   */ 
   private double[] delete(double[] purch, int index) {
      double[] output = new double[0];
      for (int i = index; i < purch.length - 1; i++) {
         purch[i] = purch[i + 1];
      }
      output = purch;
      output = Arrays.copyOf(output, output.length - 1);
      return output;
   }
}