package Component;

import java.awt.Color;

import javaxt.io.Image;

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

	public static int countBlack(Image img) {
		int result = 0;

		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				if (img.getColor(x, y).getRGB() == Color.BLACK.getRGB()) {
					result += 1;
				}
			}
		}

		return result;
	} // end of countBlack()

} // end of class Tools
