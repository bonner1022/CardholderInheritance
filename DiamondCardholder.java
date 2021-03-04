import java.text.DecimalFormat;
/**
* Diamond child of Cardholder. Inherits everything from Cardholder and sets 
* the category. Awards 3 purchase point for each dollar spent. Awards discount
* of 5% to total purchases. toString must be overwritten always.
*
* @author Alex Bonner
* @version 2/25/21
*/

public class DiamondCardholder extends Cardholder {
   
   //fields
   protected double discountRate = 0.05;
   
   //constructor
   /** 
   * instantiates DiamondCardholder by number, name and sets category.
   * @param acctNumber for client account number
   * @param name for client name
   */
   public DiamondCardholder(String acctNumber, String name) {
      super(acctNumber, name);
      category = "Diamond Cardholder";
   }
   
   //methods
   /**
   * setter method for discount rate.
   * @param rate for discount
   */
   public void setDiscountRate(double rate) {
      discountRate = rate;
   }
   
   //methods
   /**
   * getter method for discount rate.
   * @return discount rate for purchases
   */
   public double getDiscountRate() {
      return discountRate;
   }
   
   /**
   * calculates total purchases including discount.
   * @return discounted total
   */
   public double totalPurchases() {
      double sum = 0;
      for (double purch : purchases) {
         sum += purch;
      }
      return sum - (sum * discountRate);
   }
   
   /**
   * calculates purchase points for diamond member.
   * @return amount of points
   */
   public int purchasePoints() {
      int temp = (int) totalPurchases();
      return temp * 3;
   }
   
   /** 
   * overwritten toString method.
   * @return string containing info of client
   */
   public String toString() {
      String output = "";
      DecimalFormat df = new DecimalFormat("$#,##0.00");
      DecimalFormat pf = new DecimalFormat("#,##0");
      DecimalFormat disc = new DecimalFormat("0.0%");
      
      output = category + "\nAcctNo/Name: " + acctNumber + " " + name 
         + "\nPrevious Balance: " + df.format(prevBalance) 
         + "\nPayment: (" + df.format(payment) + ")"
         + "\nInterest: " + df.format(interest()) 
         + "\nNew Purchases: " + df.format(totalPurchases()) 
         + "\nCurrent Balance: " + df.format(currentBalance()) 
         + "\nMinimum Payment: " + df.format(minPayment()) 
         + "\nPurchase Points: "  + pf.format(purchasePoints()) 
         + "\n(includes " + disc.format(discountRate) 
         + " discount rate applied to New Purchases)\n\n";
       
      return output;
   }
}