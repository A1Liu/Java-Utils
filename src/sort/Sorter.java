package sort;

import java.util.List;

/**
 * 
 * General Sorter for any object type that implements the Comparable Interface
 * 
 * @author Albert Liu
 *
 */
public abstract class Sorter {
		
	/**
	 * Sorts a list of values
	 * @param list list of values
	 * @return the sorted list
	 */
	public <T extends Comparable<T>> List<T> sort(List<T> list) {
		return sort(list, 0, list.size());
	}
	
	/**
	 * Sorts a list of values
	 * @param list list of values
	 * @param startIndex Start index of the sort. Sorts all elements with indices greater than or equal to startIndex
	 * @param endIndex End index of the sort. Sorts all elements with indices less than endIndex
	 * @return the sorted list
	 */
	@SuppressWarnings("unchecked")
	public <T extends Comparable<T>> List<T> sort(List<T> list, int startIndex, int endIndex) {
		return (List<T>) sort0(new ListAccessor<T>(list), startIndex, endIndex).getData();
	}
	
	/**
	 * Sorts an array of values
	 * @param array array of values
	 * @return the sorted array
	 */
	public <T extends Comparable<T>> T[] sort(T[] array) {
		return sort(array, 0, array.length);
	}
	
	/**
	 * Sorts an array of values
	 * @param array array of values
	 * @param startIndex Start index of the sort. Sorts all elements with indices greater than or equal to startIndex
	 * @param endIndex End index of the sort. Sorts all elements with indices less than endIndex
	 * @return the sorted array
	 */
	@SuppressWarnings("unchecked")
	public <T extends Comparable<T>> T[] sort(T[] array, int startIndex, int endIndex) {
		return (T[]) sort0(new ArrayAccessor<T>(array), startIndex, endIndex).getData();
	}
	
	/**
	 * Sort method to override
	 * @param accessor Accessor object that handles dataType of container for data being sorted.
	 * @param startIndex Start index for sort. Sorts all elements with indices greater than or equal to startIndex
	 * @param endIndex End index of the sort. Sorts all elements with indices less than endIndex
	 * @return the sorted data
	 */
	protected abstract <T extends Comparable<T>> Accessor<T> sort0(Accessor<T> accessor, int startIndex, int endIndex);
	
	/**
	 * Insertion sort for lists
	 * @param list list to sort
	 * @param startIndex Start index for sort. Sorts all elements with indices greater than or equal to startIndex
	 * @param endIndex End index of the sort. Sorts all elements with indices less than endIndex
	 * @return the sorted list
	 */
	@SuppressWarnings("unchecked")
	public <T extends Comparable<T>> List<T> insertionSort(List<T> list, int startIndex, int endIndex) {
		return (List<T>) new ListAccessor<T>(list).insertionSort(startIndex, endIndex).getData();
	}
	
	 /** Insertion sort for arrays
	 * @param array array to sort
	 * @param startIndex Start index for sort. Sorts all elements with indices greater than or equal to startIndex
	 * @param endIndex End index of the sort. Sorts all elements with indices less than endIndex
	 * @return the sorted array
	 */
	@SuppressWarnings("unchecked")
	public <T extends Comparable<T>> T[] insertionSort(T[] array, int startIndex, int endIndex) {
		return (T[]) new ArrayAccessor<T>(array).insertionSort(startIndex, endIndex).getData();
	}
	
	/**
	 * 
	 * Class that handles data access. Has methods for getting, setting, swapping, and comparing elements. Also 
	 * implements a version of insertion sort.
	 * 
	 * @author Albert Liu
	 *
	 * @param <E>
	 */
	protected abstract static class Accessor<E extends Comparable<E>> {
		
		protected int swaps;
		protected int comparisons;
		protected int reads;
		protected int writes;
		
		private Accessor() {
			swaps = 0;
			comparisons = 0;
			reads = 0;
			writes = 0;
		}
		
		protected abstract Object getData();
		
		public abstract E get(int index);
		
		public abstract E set(E element, int index);
		
		public abstract void swap(int index1, int index2);
		
		public abstract int compare(int index1, int index2);
		
		protected abstract Accessor<E> insertionSort(int startIndex, int endIndex);
		
		/**
		 * Getter for number of swaps
		 * @return number of swaps
		 */
		public int getSwaps() {return swaps;}
		
		/**
		 * getter for number of comparisons
		 * @return number of comparisons
		 */
		public int getComps() {return comparisons;}
		
		/**
		 * Getter for number of reads
		 * @return number of reads
		 */
		public int getReads() {return reads;}
		
		/**
		 * Getter for number of writes
		 * @return number of writes
		 */
		public int getWrites() {return writes;}
	}
	
	protected final static class ArrayAccessor<T extends Comparable<T>> extends Accessor<T> {
		
		private T[] array;
		
		private ArrayAccessor(T[] array) {
			this.array = array;
		}

		@Override
		public T get(int index) {
			reads++;
			return array[index];
		}

		@Override
		public T set(T element, int index) {
			reads++;
			writes++;
			T temp = array[index];
			array[index] = element;
			return temp;
		}

		@Override
		public void swap(int index1, int index2) {
			T temp = array[index1];
			array[index1] = array[index2];
			array[index2] = temp;
			swaps++;
		}

		@Override
		public int compare(int index1, int index2) {
			int i = array[index1].compareTo(array[index2]);
			comparisons++;
			return i;
		}

		@Override
		protected Object getData() {
			return array;
		}

		@Override
		protected Accessor<T> insertionSort(int startIndex, int endIndex) {
			for (int current=startIndex+1; current<endIndex; ++current) {
	            int currentCheck = current-1;
	            while (currentCheck>=startIndex && compare(currentCheck,current) > 0) {
	            	swap(currentCheck+1,currentCheck--);
	            }
	        }
			return this;
		}
	}
	
	protected final static class ListAccessor <T extends Comparable<T>> extends Accessor<T> {
		
		private List<T> list;
		
		private ListAccessor(List<T> list) {
			this.list = list;
		}

		@Override
		public T get(int index) {
			reads++;
			return list.get(index);
		}

		@Override
		public T set(T element, int index) {
			reads++;
			writes++;
			return list.set(index, element);
		}

		@Override
		public void swap(int index1, int index2) {
			list.set(index2, list.set(index1, list.get(index2)));
			swaps++;
		}

		@Override
		public int compare(int index1, int index2) {
			int i = list.get(index1).compareTo(list.get(index2));
			comparisons++;
			return i;
		}

		@Override
		protected Object getData() {
			return list;
		}

		@Override
		protected Accessor<T> insertionSort(int startIndex, int endIndex) {
			for (int current=startIndex+1; current<endIndex; ++current) {
	            int currentCheck = current-1;
	            while (currentCheck>=startIndex && compare(currentCheck,current) > 0) {
	            	swap(currentCheck+1,currentCheck--);
	            }
	        }
			return this;
		}
	}
//	
//	protected final <T> T set(List<T> list,T element, int index) {
//		reads++;
//		writes++;
//		return list.set(index, element);
//	}
//	
//	protected final <T> T set(T[] array, T element, int index) {
//		reads++;
//		writes++;
//		T temp = array[index];
//		array[index] = element;
//		return temp;
//	}
//	
//	protected final <T> T get(T[] array, int index) {
//		reads++;
//		return array[index];
//	}
//	
//	protected final <T> T get(List<T> list, int index) {
//		reads++;
//		return list.get(index);
//	}
//	
//	/**
//	 * Swaps 2 elements of a list
//	 * @param list list of elements
//	 * @param index1 index of first element to swap
//	 * @param index2 index of second element to swap
//	 */
//	protected final <T> void swap(List<T> list, int index1, int index2) {
//		list.set(index2, list.set(index1, list.get(index2)));
//		swaps++;
//	}
//	
//	/**
//	 * Swaps 2 elements of an array
//	 * @param array array of elements
//	 * @param index1 index of first element to swap
//	 * @param index2 index of second element to swap
//	 */
//	protected final <T> void swap(T[] array, int index1, int index2) {
//		T temp = array[index1];
//		array[index1] = array[index2];
//		array[index2] = temp;
//		swaps++;
//	}
//	
//	/**
//	 * Compares 2 elements in a list using the compareTo method (<code>element1.compareTo(element2)</code>)
//	 * @param list list of elements
//	 * @param index1 index of first element to swap
//	 * @param index2 index of second element to swap
//	 * @return the value of <code>element1.compareTo(element2)</code>
//	 */
//	protected final <T extends Comparable<T>> int compare(List<T> list, int index1, int index2) {
//		int i = list.get(index1).compareTo(list.get(index2));
//		comparisons++;
//		return i;
//	}
//	
//	/**
//	 * Compares 2 elements in an array using the compareTo method (<code>element1.compareTo(element2)</code>)
//	 * @param array array of elements
//	 * @param index1 index of first element to swap
//	 * @param index2 index of second element to swap
//	 * @return the value of <code>element1.compareTo(element2)</code>
//	 */
//	protected final <T extends Comparable<T>> int compare(T[] array, int index1, int index2) {
//		int i = array[index1].compareTo(array[index2]);
//		comparisons++;
//		return i;
//	}
}