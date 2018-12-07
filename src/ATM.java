/**
 * Just like last time, the ATM class is responsible for managing all
 * of the user interaction. This means login procedures, displaying the
 * menu, and responding to menu selections. In the enhanced version, the
 * ATM class will have the added responsibility of interfacing with the
 * Database class to write and read information to and from the database.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ATM {
	private BankAccount bankAccount;
	private BankAccount destination;
	private Database database;
	
	public ATM() throws FileNotFoundException, IOException {
		this.destination = null;
		this.database = new Database("accounts-db.txt");
	}
	
	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	public double round(double number) {
		return (double) Math.round(number * 100)/100.00;
	}
	
	public static String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}
	
	public void Menu() throws IOException {
		Scanner in = new Scanner(System.in);
		int pin = 0;
		long num = 0;
		int begin = 0;
		
		while (begin != 3) {
			System.out.println("Welcome to the bank. Press 1 to login to your account. Press 2 to open a new account. Press 3 to quit.");
			begin = in.nextInt();
			
			
			if (begin == 1) {
			//	while (pin != bankAccount.getUser().getPIN() || num != bankAccount.getAccountNumber()) {
					
						System.out.println("Enter account number:");
						num = in.nextLong();
						
						bankAccount = database.getAccount(num);
						while (bankAccount == null) {
							System.out.println("Bank Account not valid");
							System.out.println("Enter account number:");
							num = in.nextLong();
							bankAccount = database.getAccount(num);
						}
						//} else if (bankAccount.getUser() == null) {
						//	System.out.println("User not valid");
						//}
						
						if (num == bankAccount.getAccountNumber()) {
						
							System.out.println("Enter pin:");
							pin = in.nextInt();
							if (pin == bankAccount.getUser().getPIN()) {
								
							int option = 0;
							while (option != 8) {
								System.out.println("Press 1 to deposit, 2 to withdraw, 3 show balance, 4 to transfer funds, \n"
										 + "5 to view personal info, 6 to update personal info, 7 to close account, and 8 to exit");
								 option = in.nextInt();
								 
							if (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7 && option != 8) {
								System.out.println("Enter a valid option");
							} 
							
							else if (option == 1) {
								System.out.println("How much would you like to deposit?");
								int money = in.nextInt();
								int returnV = bankAccount.deposit(money);
									if (returnV == 1) {
										System.out.println("Now there is " + round(bankAccount.getBalance()) + " dollars in your account");
									} else {
										System.out.println("Enter amount greater than 0");
									}
							} 
							
							else if (option == 2) {
								if (bankAccount.getBalance() == 0) {
									System.out.println("There is no money to take out :(");
								} else {
									System.out.println("How much would you like to withdraw?");
									int money = in.nextInt();
									int returnV = bankAccount.withdraw(money);
									if (returnV == 2) {
										System.out.println("Now there is " + round(bankAccount.getBalance()) + " dollars in your account");
									} 
									else if (returnV == 1){
										System.out.println("Enter amount greater than 0");
									} else {
										System.out.println("Sorry, you're too broke for that :(");
									}				
								} 
							}
							
							else if (option == 3) {
								System.out.println("There is " + round(bankAccount.getBalance()) + " dollars in your account");	
							} 
							
							else if(option == 4) {
								System.out.println("How much would you like to transfer?");
								int money = in.nextInt();
								System.out.println("What account would you like to transfer it to? Enter the account number");
								long accNum = in.nextLong();
								
								destination = database.getAccount(accNum);
								if (destination != null) {
									int returnV = bankAccount.transfer(money, destination);	
									if (returnV == 1) {
										System.out.println("All set! You now have " + round(bankAccount.getBalance()) + " dollars in your account");
									} else {
										System.out.println("Enter valid amount");
									}
									
								} else {
									System.out.println("Account not found");
								}
								
							}
							
							else if (option == 5) {
								System.out.println("First Name: " + bankAccount.getUser().getFname());
								System.out.println("Last Name: " + bankAccount.getUser().getLname());
								System.out.println("Address: " + bankAccount.getUser().getAddress());
								System.out.println("City: " + bankAccount.getUser().getCity());
								System.out.println("State: " + bankAccount.getUser().getState());
								System.out.println("Zip: " + bankAccount.getUser().getZip());
							} 
							else if (option == 6) {
								System.out.println("Press 1 to change your phone number, 2 to change PIN, 3 to change address, 4 to change city, \n"
										+ "5 to change state, 6 to change zip, and 7 to cancel!");
								int choice = in.nextInt();
								if (choice == 1) {
									System.out.println("What is your new number?");
									long num1 = in.nextLong();
									bankAccount.getUser().setPhone(num1);
									System.out.println("All set! Thanks");

								}
								else if (choice == 2) {
									System.out.println("What is your PIN?");
									int pin1 = in.nextInt();
									if (pin1 == bankAccount.getUser().getPIN()) {
										System.out.println("What is your new PIN?");
										int pin2 = in.nextInt();
										bankAccount.getUser().setPIN(pin2);
										System.out.println("All set! Thanks"); } 
									else {
										System.out.println("Wrong pin. Change canceled.");
									}
									
								} else if (choice == 3) {
									
									String add;
									do {
										System.out.println("Address:");
										in.nextLine();
										add = in.nextLine();
										
									} while (add.length() > 30);
									bankAccount.getUser().setAddress(add);
									System.out.println("All set! Thanks");

								}
								else if (choice == 4) {
									String city;
									do {
										System.out.println("City:");
										in.nextLine();
										city = in.nextLine();
										
									} while (city.length() > 30);
									bankAccount.getUser().setCity(city);
									System.out.println("All set! Thanks");

								} else if (choice == 5) {
									String state;
									do {
										System.out.println("State:");
										in.nextLine();
										state = in.nextLine();
										
									} while (state.length() > 2);
									bankAccount.getUser().setState(state);
									System.out.println("All set! Thanks");

								}
								else if (choice == 6) {
									String zip;
									do {
										System.out.println("Zip:");
										in.nextLine();
										zip = in.nextLine();
										
									} while (zip.length() > 5);
									bankAccount.getUser().setZip(zip);
									System.out.println("All set! Thanks");

								}
								else if (choice == 7) {
									System.out.println("Canceled.");
								}

							} 
							else if (option == 7) {
								System.out.println("Sorry you want to cancel your account with us. Press 1 if you're sure! Press 2 to keep your account open");
								int choice = in.nextInt();
								if (choice == 1) {
									bankAccount.getUser().setOpen("N");
									System.out.println("Your account is closed. Sorry!");
									break;
								} 
							} 
							else if (option == 8) {
								System.out.println("Have a good day!");
								database.updateAccount(bankAccount, destination);
								break;
							} 
						}
						}
						else {
							System.out.println("Credentials invalid");
						}
					}
					else {
						System.out.println("Credentials invalid");
					}
				// }
				
				
			}else if (begin == 2) {
				System.out.println("I'm excited you're starting an account with us! Can you enter the following information?");
				String fname;
				String lname;
				String phone;
				String address;
				String city;
				String state;
				String zip;
				String dob;
				String pinnew;
				
				do {
					
					System.out.println("First Name:");
					in.nextLine();
					fname = in.nextLine();
					
				} while (fname.length() > 15);
				fname = padRight(fname, 15);
				
				do {
					System.out.println("Last Name:");
					lname = in.nextLine();
					
				} while (lname.length() > 20);
				lname = padRight(lname, 20);
				
				do {
					System.out.println("Phone:");
					phone = in.nextLine();
					
				} while (phone.length() > 10);
				phone = padRight(phone, 10);
				
				do {
					System.out.println("Address:");
					address = in.nextLine();
					
				} while (address.length() > 30);
				address = padRight(address, 30);
				
				do {
					System.out.println("City:");
					city = in.nextLine();
					
				} while (city.length() > 30);
				city = padRight(city, 30);
				
				do {
					System.out.println("State:");
					state = in.nextLine();
					
				} while (state.length() > 2);
				state = padRight(state, 2);
				
				do {
					System.out.println("Zip:");
					zip = in.nextLine();
					
				} while (zip.length() > 5);
				zip = padRight(zip, 5);
	
				do {
					System.out.println("DOB (YYYYMMDD):");
					dob = in.nextLine();
					
				} while (dob.length() > 8);
				dob = padRight(dob, 8);
				
				do {
					System.out.println("Pin:");
					pinnew = in.nextLine();
					
				} while (pinnew.length() > 4);
				pinnew = padRight(pinnew, 4);
				
				String balance = "0";
				balance = padRight(balance, 15);
				
				long accountnumber = database.getMaxAccountNumber() + 1;

				//System.out.println(accountnumber + pinnew + balance + lname + fname + dob + phone + address + city + state + zip + "Y");
				BankAccount newone = new BankAccount(accountnumber + pinnew + balance + lname + fname + dob + phone + address + city + state + zip + "Y");
				database.updateAccount(newone, destination);
				
				System.out.println("Glad you're joining us! Your account number is " + accountnumber + ".");
				
			} else if (begin == 3) {
				System.out.println("Have a good day!");
				break;
			}
		}
		
		
		in.close();
	}
		
}
