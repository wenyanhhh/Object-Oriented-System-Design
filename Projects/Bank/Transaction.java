package hw5Bank;

public final class Transaction {
	private final int fromAcct;
	private final int toAcct;
	private final int transAmount;
	
	public Transaction(int fromAcct, int toAcct, int transAmount) {
		this.fromAcct = fromAcct;
		this.toAcct = toAcct;
		this.transAmount = transAmount;
	}
	
	public Transaction(String s) {
		String[] info = s.split(" ");
		this.fromAcct = Integer.parseInt(info[0]);
		this.toAcct = Integer.parseInt(info[1]);
		this.transAmount = Integer.parseInt(info[2]);
	}
	
	public int getFromAcct() {
		return fromAcct;
	}
	
	public int getToAcct() {
		return toAcct;
	}
	
	public int getAmount() {
		return transAmount;
	}
	
	
}
