package sort;

public class Generator {

	
	public static Integer[] randomData(int length, int avg, int std) {
		Integer[] array = new Integer[length];
		for (int i = 0; i < length; i++) {
			array[i] = (int) (std*(Math.random() - .5)) + avg;
		}
		return array;
	}
}
