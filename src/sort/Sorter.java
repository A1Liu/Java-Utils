package sort;

import java.util.List;
//import static debug.Debug.*;

/**
 * 
 * General Sorter for any object type that implements the Comparable Interface
 * 
 * @author Albert Liu
 *
 */
public abstract class Sorter {
		
	Accessor<?> accessor;
	
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
		accessor = new ListAccessor<T>(list);
		return (List<T>) sort0(accessor, startIndex, endIndex).getData();
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
		accessor = new ArrayAccessor<T>(array);
		return (T[]) sort0(accessor, startIndex, endIndex).getData();
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
		accessor = new ListAccessor<T>(list);
		return (List<T>) accessor.insertionSort(startIndex, endIndex).getData();
	}
	
	 /** Insertion sort for arrays
	 * @param array array to sort
	 * @param startIndex Start index for sort. Sorts all elements with indices greater than or equal to startIndex
	 * @param endIndex End index of the sort. Sorts all elements with indices less than endIndex
	 * @return the sorted array
	 */
	@SuppressWarnings("unchecked")
	public <T extends Comparable<T>> T[] insertionSort(T[] array, int startIndex, int endIndex) {
		accessor = new ArrayAccessor<T>(array);
		return (T[]) accessor.insertionSort(startIndex, endIndex).getData();
	}
	
	public String sortSummary() {
		return 	String.format("Swaps: %d\n Comparisons: %d\n Reads: %d\n Writes: %d\n", accessor.getSwaps(), accessor.getComps(), accessor.getReads(),accessor.getWrites());
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
	protected abstract static class Accessor<E extends Comparable<E>> {// TODO Make accessor extend List
		
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
		
		/**
		 * Getter for the data stored in the accessor
		 * @return the data
		 */
		protected abstract Object getData();
		
		/**
		 * Getter for element at an index
		 * @param index index of the element
		 * @return the element at the index
		 */
		public abstract E get(int index);
		
		/**
		 * Setter for a location in the data
		 * @param element the element to put at the location
		 * @param index the index to put the element
		 * @return the element that was previously at that location
		 */
		public abstract E set(E element, int index);
		
		/**
		 * Swaps two elements in the data
		 * @param index1 index of first element to be swapped
		 * @param index2 index of second element to be swapped
		 */
		public final void swap(int index1, int index2) {
			set(set(get(index2), index1),index2);
			swaps++;
		}
		
		/**
		 * Compares data in 2 indices using get(index1).compareTo(get(index2))
		 * @param index1 index of first element
		 * @param index2 index of element to compare the first to
		 * @return the result of the comparison
		 */
		public final int compare(int index1, int index2) {
			int i = get(index1).compareTo(get(index2));
			comparisons++;
			return i;
		}
		
//		protected Accessor<E> insertionSort(int startIndex, int endIndex) {
//			for (int current=startIndex+1; current<endIndex; ++current) {
//	            int currentCheck = current-1;
//	            while (currentCheck>=startIndex && compare(currentCheck,currentCheck+1) > 0) {
//	            	swap(currentCheck+1,currentCheck--);
//	            }
//	        }
//			return this;
//		}
		
		/**
		 * Insertion sort implementation
		 * @param startIndex Start index for sort. Sorts all elements with indices greater than or equal to startIndex
		 * @param endIndex End index of the sort. Sorts all elements with indices less than endIndex
		 * @return the sorted data
		 */
		protected final Accessor<E> insertionSort(int startIndex, int endIndex) {
			for (int current=startIndex+1; current<endIndex; ++current) {
	            int currentCheck = current-1;
	            E element = get(current);
	            while (currentCheck>=startIndex) {
	            	comparisons++;
	            	if (get(currentCheck).compareTo(element) > 0) {
	            		set(get(currentCheck),currentCheck+1);
		            	currentCheck--;
	            	} else break;
	            }
	            set(element,currentCheck+1);
	        }
			return this;
		}
		
		/**
		 * Getter for number of swaps
		 * @return number of swaps
		 */
		public final int getSwaps() {return swaps;}
		
		/**
		 * getter for number of comparisons
		 * @return number of comparisons
		 */
		public final int getComps() {return comparisons;}
		
		/**
		 * Getter for number of reads
		 * @return number of reads
		 */
		public final int getReads() {return reads;}
		
		/**
		 * Getter for number of writes
		 * @return number of writes
		 */
		public final int getWrites() {return writes;}
		
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
		protected Object getData() {
			return array;
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
		protected Object getData() {
			return list;
		}
	}
}