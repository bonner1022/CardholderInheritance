# CardholderInheritance
Create an abstract Cardholder class that creates a Cardholder user and keeps track of certain bank information of each user. Like name, account number, balances, last payment made, interest and awards given. 
This abstract class is used to define 3 categories(Sapphire, Diamond, BlueDiamond of Cardholder objects, which each being a subclass of Cardholder. Each category gives different awards and discounts to members of the class.
A CardholderProcessor class is used to read a file and parse through each line of the file to create Cardholder objects. If a line displays a valid user, then an object is created and added to a Cardholder array. If it is invalid, then is passed to a String array which denotes Invalid Records. 4 methods are used to process these arrays and generate monthly reports sorted by file ordering, name ordering, current balance ordering and the invalid records.
A driver class is used to input the file to be read and then prints the records.
