import java.io.FileNotFoundException;
/**
* Create a driver class for CardholderProcessor class which holds arrays of
* Cardholder objects.
* @author Alex Bonner
*/
public class CardholdersApp {

   /**
   * Main accepts file name as command line arg, creates CardholderProcessor 
   * object and invokes methods to read file, process cardholder records
   * and then generate and print reports. 
   * @param args for command line arguments
   * @throws FileNotFoundException if file can't be found
   */
   public static void main(String[] args) {
   
      if (args.length == 0) {
         System.out.println("File name expected as command line argument\n"
            + "Program Ending.");
      }
      else {
         String fileName = args[0];
         try {
            CardholderProcessor proc = new CardholderProcessor();
            proc.readCardholderFile(fileName);
            
            String reg = proc.generateReport();
            String name = proc.generateReportByName();
            String bal = proc.generateReportByCurrentBalance();
            String inv = proc.generateInvalidRecordsReport();
            
            System.out.println(reg);
            System.out.println(name);
            System.out.println(bal);
            System.out.println(inv);
         }
         catch (FileNotFoundException e) {
            System.out.println("*** Attempted to read file: " + e.getMessage());
         }
      }
   }
}