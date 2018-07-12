package test;


import java.io.IOException;
import data.Numbers.PrimeGenerator;
import static debug.Debug.*;

public class TestHarness {
	public static void main(String...strings) throws IOException {
		PrimeGenerator.fillPrimes(100);
		int index = 1;
		for (int prime : PrimeGenerator.primes) {
			sp(index++ + ". " + prime);
		}
	}
	
	
	
}
