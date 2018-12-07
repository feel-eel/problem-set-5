/**
 * Just like last time, the BankAccount class is primarily responsible
 * for depositing and withdrawing money. In the enhanced version, there
 * will be the added requirement of transfering funds between accounts.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

public class BankAccount {
	
		//private static long generatedAccountNumber = 100000001;
		
		private long accountNumber;
		private double balance;
		private User user;
		
		/**
		 * Constructor for BankAccount class.
		 * 
		 * @param balance
		 * @param user
		 */

		public BankAccount(String account) {
			this.accountNumber = Long.parseLong(account.substring(0, 9));
			
			this.user = new User(
				Integer.parseInt(account.substring(9, 13)),
				account.substring(28, 48),
				account.substring(48, 63),
				account.substring(63, 71),
				Long.parseLong(account.substring(71, 81)),
				account.substring(81, 111),
				account.substring(111, 141),
				account.substring(141, 143),
				account.substring(143, 148),
				account.substring(148, 149));
			this.balance = Double.parseDouble(account.substring(13, 28));
		}
		
		/////////////////////////////////// GETTERS AND SETTERS ///////////////////////////////////
		

		/**
		 * Retrieves the account number.
		 * 
		 * @return accountNumber
		 */
		
		public long getAccountNumber() {
			return accountNumber;
				
		}
		
		/**
		 * Retrieves the balance.
		 * 
		 * @return balance
		 */
		
		public double getBalance() {
			return balance;
		}
		
		/**
		 * Retrieves the user.
		 * 
		 * @return user
		 */
		
		public User getUser() {
			return user;
		}
		
		/**
		 * Sets the value of accountNumber.
		 * 
		 * @param accountNumber the new account number
		 */
		
		public void setAccountNumber(long accountNumber) {
			this.accountNumber = accountNumber;
		}
		
		/**
		 * Sets the value of balance.
		 * 
		 * @param balance the new balance
		 */
		
		public void setBalance(double balance) {
			this.balance = balance;
		}
		
		/**
		 * Sets the value of user.
		 * 
		 * @param user the new user
		 */
		
		public void setUser(User user) {
			this.user = user;
		}
		
		/////////////////////////////////// INSTANCE METHODS ///////////////////////////////////
		
		/**
		 * Deposits money into this account.
		 * 
		 * @param amount the money to deposit
		 * @return a status code (0: invalid amount, 1: success)
		 */
		
		public int deposit(double amount) {
			if (amount <= 0) {
				return 0;
			} else {
				setBalance(getBalance()+amount);
				
				return 1;
			}
		}
		
		/**
		 * Withdraws money from this account.
		 * 
		 * @param amount the money to withdraw
		 * @return a status code (0: insufficient funds, 1: invalid amount, 2: success)
		 */
		
		public int withdraw(double amount) {
			if (amount > balance) {
				return 0;
			} else if (amount <= 0) {
				return 1;
			} else {
				setBalance(getBalance()-amount);
				
				return 2;
			}
		}
		
		
		public int transfer(double amount, BankAccount account2) {
			if (amount > balance) {
				return 0;
			} else if (amount <= 0) {
				return -1;
			}  else {
				this.withdraw(amount);
				account2.deposit(amount);
				return 1;
			}
		}
		
		@Override
		public String toString() {			
			return String.format("%09d%04d%-15.2f%-20s%-15s%-8s%10d%-30s%-30s%-2s%-5s%s", accountNumber, user.getPIN(), balance, user.getLname(),  user.getFname(), user.getDOB(), user.getPhone(), user.getAddress(), user.getCity(), user.getState(), user.getZip(), user.getOpen());
		}
}