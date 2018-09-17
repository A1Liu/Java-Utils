package test;


import java.io.IOException;

import sort.Generator;
import sort.Generator.OrderInt;
import sort.MergeSorter;
import sort.Sorter;

import static debug.Debug.*;

public class TestHarness {
	public static void main(String...strings) throws IOException {
//		PrimeGenerator pgen = new PrimeGenerator();
//		double running_filter = 1;
//		for (int i = 0; i < 10000; i++) {
//			int prime = pgen.nextPrime();
//			running_filter = running_filter * (prime-1) / prime;
//			sp(prime + " - " + 100*running_filter + "%");
//		}

		
		
		
		Sorter sort = new MergeSorter();
		int length = 10;
		OrderInt[] data;
		data = sort.insertionSort(Generator.randDataOrder(length, 10, 20), 0, length);
		pArr(data);
		sp(sort.sortSummary());
		
		data = sort.insertionSort(Generator.randDataOrder(length, 10, 20), 0, length);
		pArr(data);
		sp(sort.sortSummary());
		

	}
	
	
}
