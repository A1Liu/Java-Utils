package test;


import java.io.IOException;
import java.util.Arrays;

import sort.SortingTest.Generator;
import sort.SortingTest.Generator.OrderInt;
import sort.MergeSorter;
import sort.Sorter;

import static debug.Debug.*;

public class TestHarness {
	public static void main(String...strings) throws IOException {
		
		Sorter sort = new MergeSorter();
		int length = 10;
		OrderInt[] data = Generator.randDataOrder(length, 10, 20);
		OrderInt[] data_copy = Arrays.copyOf(data, data.length);
		pArr(data);
		data = sort.sort(data, 0, length);
		pArr(data);
		sp(sort.sortSummary());
		data = Generator.randDataOrder(length, 10, 20);
		data = sort.insertionSort(data_copy, 0, length);
		pArr(data_copy);
		sp(sort.sortSummary());
		

	}
	
//	PrimeGenerator pgen = new PrimeGenerator();
//	double running_filter = 1;
//	for (int i = 0; i < 10000; i++) {
//		int prime = pgen.nextPrime();
//		running_filter = running_filter * (prime-1) / prime;
//		sp(prime + " - " + 100*running_filter + "%");
//	}
}
