package hw5Cracker;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;


public class Cracker {
	// Array of chars used to produce strings
	public static final char[] CHARS = "abcdefghijklmnopqrstuvwxyz0123456789.,-!".toCharArray();	
	
	static CountDownLatch latch;
	private static String hash;
	private static ArrayList<String> result = new ArrayList<String>();
	
	/*
	 Given a byte[] array, produces a hex String,
	 such as "234a6f". with 2 chars for each byte in the array.
	 (provided code)
	*/
	public static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i=0; i<bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff;  // remove higher bits, sign
			if (val<16) buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}
		
	/*
	 Given a string of hex byte values such as "24a26f", creates
	 a byte[] array of those values, one byte value -128..127
	 for each 2 chars.
	 (provided code)
	*/
	public static byte[] hexToArray(String hex) {
		byte[] result = new byte[hex.length()/2];
		for (int i=0; i<hex.length(); i+=2) {
			result[i/2] = (byte) Integer.parseInt(hex.substring(i, i+2), 16);
		}
		return result;
	}
		
	private final static int GENERATION_MODE = 1;
	private final static int CRACKING_MODE = 3;
	
	public static void main(String[] args) {
		Cracker cracker = new Cracker();
		
		if (args.length == GENERATION_MODE) {
			System.out.println(cracker.generateHashValue(args[0]));
		} else if (args.length == CRACKING_MODE) {
			latch = new CountDownLatch(Integer.parseInt(args[2]));
			String hashInput = args[0];
			int maxWordLength = Integer.parseInt(args[1]);
			int numThreads = Integer.parseInt(args[2]);
			
			cracker.crackHashValue(hashInput, maxWordLength, numThreads);
			
			try {
				latch.await();
			} catch (InterruptedException e) {
				
			}
			
			for (String s: result) {
				System.out.println(s);
			}
			System.out.println("all done");	
		} else {
			System.out.println("Error in args");
		}
		
	}

	public String generateHashValue(String password) {
		String passwordInHash = "";
		try {		
			MessageDigest method = MessageDigest.getInstance("SHA-256");
			method.update(password.getBytes());
			passwordInHash = hexToString(method.digest());
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			
		}
		
		return passwordInHash;
		
	}
	

			
	public void crackHashValue(String hashInput, int maxWordLength, int numThreads) {
		//assgin workers	
		hash = hashInput;
		
		int len = CHARS.length / numThreads;
		
		for (int i = 0; i < numThreads; i++) {
			if (i == numThreads - 1) {
				new Worker(i * len, CHARS.length - 1, maxWordLength).start();	
			} else {
				new Worker(i * len, (i + 1) * len - 1, maxWordLength).start();	
			}			
		
		}		   
	
	}
	
	public class Worker extends Thread {
		private int start;
		private int stop;
		private int maxWordLength;

		
		public Worker(int start, int stop, int maxWordLength) {
			this.start = start;
			this.stop = stop;
			this.maxWordLength = maxWordLength;
				
		}
		
		@Override
		public void run() {
			for (int i = start; i <= stop; i++) {
				findTargetWords(maxWordLength,""+ CHARS[i]);
			}
			latch.countDown();
		}
		
		public void findTargetWords(int maxWordLength, String current) {
			
			if (current.length() > maxWordLength) {
				return;
			}
			
			String hashOfCurrent = generateHashValue(current);
			if (hashOfCurrent.equals(hash)) {
				result.add(current);	
			} 
			
			for (int i = 0; i < CHARS.length; i++) {
				current = current + CHARS[i];
				findTargetWords(maxWordLength, current);
				current = current.substring(0, current.length() - 1);					
			}
				
		}
		
	}
	
	// possible test values:
	// a ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb
	// fm 440f3041c89adee0f2ad780704bcc0efae1bdb30f8d77dc455a2f6c823b87ca0
	// a! 242ed53862c43c5be5f2c5213586d50724138dea7ae1d8760752c91f315dcd31
	// xyz 3608bca1e44ea6c4d268eb6db02260269892c0b42b86bbf1e77a6fa16c3c9282

}
