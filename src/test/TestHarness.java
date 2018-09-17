package test;


import java.io.IOException;
import java.util.List;

import data.Numbers.PrimeGenerator;
import sort.Generator;
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

		Integer[] asdf = Generator.randomData(20, 10, 20);
		pArr(asdf);
		Sorter sort = new MergeSorter();
		
		sort.insertionSort(asdf, 0, asdf.length);
		pArr(asdf);

	}
//	
//	static void sort(Integer arr[])
//    {
//        int n = arr.length;
//        for (int i=1; i<n; ++i)
//        {
//            int key = arr[i];
//            int j = i-1;
// 
//            /* Move elements of arr[0..i-1], that are
//               greater than key, to one position ahead
//               of their current position */
//            while (j>=0 && arr[j].compareTo(key) > 0)
//            {
//                arr[j+1] = arr[j];
//                j = j-1;
//            }
//            arr[j+1] = key;
//        }
//    }
 
	
	
	
}
