import java.text.DecimalFormat;

/**
* BlueDiamond child of DiamondCardholder. Inherits everything from 
* DiamondCardholder and sets the category. Awards 5 purchase points for each 
* dollar spent and additional bonus if total is greater than 2500. Awards 10%
* discount. toString must be overwritten always.
*
* @author Alex Bonner
* @version 2/25/21
*/
public class BlueDiamondCardholder extends DiamondCardholder {

   //fields
   private int bonusPurchasePoints = 2500;
   
   // constructor
   /** 
   * instantiates BlueDiamondCardholder by number, name and sets category
   * and sets discount rate.
   * @param acctNumber for client account number
   * @param name for client name
   */
   public BlueDiamondCardholder(String acctNumber, String name) {
      super(acctNumber, name);
      category = "Blue Diamond Cardholder";
      discountRate = 0.10;
   }
   
   //methods
   /**
   * setter method for additional bonus point.
   * @param point for bonus points awarded
   */
   public void setBonusPoints(int point) {
      bonusPurchasePoints = point;
   }
   
   /**
   * getter method for additional bonus point.
   * @return bonus points awarded
   */
   public int getBonusPoints() {
      return bonusPurchasePoints;
   }

   /**
   * calculates purchase points for blue diamond member. If total of purchases
   * exceeds 2500, the additional points are rewarded.
   * @return amount of points
   */
   public int purchasePoints() {
      int temp = (int) totalPurchases();
      int output = temp * 5;
      if (temp > 2500) {
         return output + bonusPurchasePoints;
      }
      return output;
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
      DecimalFormat bonus = new DecimalFormat("#,##0");
      
      output = category + "\nAcctNo/Name: " + acctNumber + " " + name 
         + "\nPrevious Balance: " + df.format(prevBalance) 
         + "\nPayment: (" + df.format(payment) + ")"
         + "\nInterest: " + df.format(interest()) 
         + "\nNew Purchases: " + df.format(totalPurchases()) 
         + "\nCurrent Balance: " + df.format(currentBalance())
         + "\nMinimum Payment: " + df.format(minPayment()) 
         + "\nPurchase Points: "  + pf.format(purchasePoints()) 
         + "\n(includes " + disc.format(discountRate) 
         + " discount rate applied to New Purchases)\n";
         
      if (totalPurchases() <= 2500) {
         return output + "\n";
      }  
      else {
         output += "(includes " + bonus.format(bonusPurchasePoints) 
            + " bonus " + "points added to Purchase Points)\n\n";
      }
       
      return output;
   }
}