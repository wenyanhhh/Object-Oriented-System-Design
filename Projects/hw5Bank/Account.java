package hw5Bank;

public class Account {
	private int IDNumber;
	private int curBalance;
	private int numOfTransaction;
	
	public Account (int IDNumber) {
		this.IDNumber = IDNumber;
		this.curBalance = 1000;
		this.numOfTransaction = 0;
	}
	
	public int getID() {
		return IDNumber;
	}
	
	public int getBalance() {
		return curBalance;
	}
	
	public int getTransaction() {
		return numOfTransaction;
	}
	
	public synchronized void deposit(int amount) {
		curBalance += amount;
		numOfTransaction++;
	}
	
	public synchronized void withdraw(int amount) {
		curBalance -= amount;
		numOfTransaction++;
	}
	
	@Override
	public  String toString() {
		String accountInfo = "acct: " + IDNumber + " "
				+ "bal: " + curBalance + " "
				+ "trans: " + numOfTransaction;
		return accountInfo;
	}
}
