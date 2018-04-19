package hw5Bank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class Bank {
	private final Transaction nullTrans = new Transaction(-1, 0, 0);
	private final static int NUMBER_OF_ACCOUNTS = 20;
	
	private ArrayList<Account> accounts;
	private BlockingQueue<Transaction> transactions;
	private int numThreads;
	static CountDownLatch latch;
	
	public Bank(int numThreads) {
		this.numThreads = numThreads;
		transactions = new ArrayBlockingQueue<Transaction>(numThreads);
		accounts = new ArrayList<Account>();
		latch = new CountDownLatch(numThreads);
		
		for (int i = 0; i < NUMBER_OF_ACCOUNTS; i++) {
			accounts.add(new Account(i));
		}
		
		for (int i = 0; i < numThreads; i++) {
			new Worker().start();
		}
	}
	
	public class Worker extends Thread{
		@Override
		public void run() {
			while(true) {
				try {
					Transaction currentTran = transactions.take();
					
					if (currentTran == nullTrans) {
						break;
					}
					
					Account fromAcct = accounts.get(currentTran.getFromAcct());
					Account toAcct = accounts.get(currentTran.getToAcct());
					
					fromAcct.withdraw(currentTran.getAmount());
					toAcct.deposit(currentTran.getAmount());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			latch.countDown();
		}
	}
	
	public void readFile(String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			while (true) {
				String line = reader.readLine();
				if(line == null) {
					for (int i = 0; i < numThreads; i++) {
						transactions.put(nullTrans);
					}
					break;
				}
				transactions.put(new Transaction(line));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			Bank myBank = new Bank (Integer.parseInt(args[1]));
			myBank.readFile(args[0]);
			
			try {
				latch.await();
			} catch (InterruptedException e) {
				
			}
			myBank.printResult();
			
		} catch (Exception e) {
			System.out.println("Error in args");
		}
	}
	
	public void printResult() {
		for (int i = 0; i < accounts.size(); i++) {
			System.out.println(accounts.get(i).toString());
		}
	}
}
