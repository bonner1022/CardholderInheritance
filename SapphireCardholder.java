import java.text.DecimalFormat;
/**
* Sapphire child of Cardholder. Inherits everything from Cardholder and sets 
* the category. Awards 1 purchase point for each dollar spent. toString must 
* be overwritten always.
*
* @author Alex Bonner
* @version 2/25/21
*/

public class SapphireCardholder extends Cardholder {
   
   // constructor
   /** 
   * instantiates SapphireCardholder by number, name and sets category.
   * @param acctNumber for client account number
   * @param name for client name
   */
   public SapphireCardholder(String acctNumber, String name) {
      super(acctNumber, name);
      category = "Sapphire Cardholder";
   }

   /**
   * calculates purchases points for sapphire member.
   * @return amount of points
   */
   public int purchasePoints() {
      double temp = totalPurchases();
      return (int) temp;
   }
   
   /** 
   * overwritten toString method.
   * @return string containing info of client
   */
   public String toString() {
      String output = "";
      DecimalFormat df = new DecimalFormat("$#,##0.00");
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

}