package Component;

public class Tools {

	public static int sumArray(int[][] input) {
		int result = 0;

		for (int[] is : input) {
			for (int i : is) {
				result += i;
			}
		}

		return result;
	} // end of sumArray()

} // end of class Tools
