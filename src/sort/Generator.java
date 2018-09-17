package sort;


public class Generator {

	public static Integer[] randData(int length, int avg, int std) {
		Integer[] array = new Integer[length];
		for (int i = 0; i < length; i++) {
			array[i] = (int) (std*(Math.random() - .5)) + avg;
		}
		return array;
	}
	
	public static OrderInt[] randDataOrder(int length, int avg, int std) {
		OrderInt[] array = new OrderInt[length];
		for (int i = 0; i < length; i++) {
			array[i] = new OrderInt((int) (std*(Math.random() - .5)) + avg,i);
		}
		return array;
	}
	
	public static class OrderInt implements Comparable<OrderInt> {
		
		private Integer value;
		private int order;
		
		public OrderInt(int value, int order) {
			this.value = value;
			this.order = order;
		}
		
		@Override
		public int compareTo(OrderInt o) {
			return this.getValue().compareTo(o.getValue());
		}
		
		public Integer getValue() {
			return value;
		}
		
		public String toString() {
			return this.value + "." + this.order;
		}
		
	}
	
	
}
