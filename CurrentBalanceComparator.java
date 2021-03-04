import java.util.Comparator;
/**
* Implements Comparator interface to define alternate ordering of Cardholder
* objects. Defines order from highest to lowest current balance. 
*/
public class CurrentBalanceComparator implements Comparator<Cardholder> {
   
   /** compare method of Comparator interface.
   * @param c1 for first Cardholder object
   * @param c2 for second Cardholder object
   * @return int result of comparison **/
   public int compare(Cardholder c1, Cardholder c2) {
      if (c1.currentBalance() < c2.currentBalance()) {
         return 1;
      }
      else if (c1.currentBalance() > c2.currentBalance()) {
         return -1;
      }
      else {
         return 0;
      }
   }
}