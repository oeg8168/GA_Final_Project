package GA;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.HashMap;

import javaxt.io.Image;
import Component.Car;
import Component.Pair;

public class Individual {

	public Image map;
	public ArrayList<Car> carList;
	public HashMap<Pair<Car, Car>, Integer> overlapping;

	public Individual(String mapFilePath) {
		this(new Image(mapFilePath));
	}

	public Individual(Image inputMap) {
		map = inputMap.copy();

		carList = new ArrayList<Car>();
		overlapping = new HashMap<Pair<Car, Car>, Integer>();
	}

	public double getFitness() {
		return 0;
	} // end of getFitness()

	/**
	 * Output this individual as image file
	 * 
	 * @param path
	 *            - Output file path
	 * @see <a
	 *      href="http://stackoverflow.com/questions/3514158/how-do-you-clone-a-bufferedimage">
	 *      Reference link</a>
	 */
	public void outputImg(String path) {

		ColorModel cm = map.getBufferedImage().getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = map.getBufferedImage().copyData(null);
		Image output = new Image(new BufferedImage(cm, raster, isAlphaPremultiplied, null));

		for (Car car : carList) {
			output.addImage(car.getCarImage(), car.getOriginPoint().x, car.getOriginPoint().y, false);
			output.addText(String.valueOf(carList.indexOf(car)), car.getOriginPoint().x, car.getOriginPoint().y);
		}

		output.saveAs(path);
	} // end of outputImg()

	/**
	 * Print car data in console
	 */
	public void showCars() {
		System.out.println("Individual: \t" + this.toString());
		System.out.println("Car count: \t" + carList.size());
		System.out.println("X\tY\tOrientation");
		for (Car car : carList) {
			System.out.println(car.getOriginPoint().x + "\t" + car.getOriginPoint().y + "\t" + car.getOrientation());
		}
		System.out.println();
	} // end of showCars()

	/**
	 * Count overlapping each 2 cars and save it
	 */
	public void countOverlapping() {
		for (int i = 0; i < carList.size(); i++) {
			for (int j = i; j < carList.size(); j++) {
				Car c1 = carList.get(i);
				Car c2 = carList.get(j);

				Pair<Car, Car> carPair = new Pair<Car, Car>(c1, c2);

				if (!overlapping.containsKey(carPair)) {
					if (i == j) {
						overlapping.put(carPair, 0);
					} else {
						overlapping.put(carPair, Car.getOverlapping(c1, c2));
					}
				}
			}
		}
	}// end of countOverlapping()

	/**
	 * Show overlapping in matrix format
	 */
	public void showOverlapping() {

		System.out.print("X\t");
		for (int i = 0; i < carList.size(); i++) {
			System.out.print(carList.get(i).getOriginPoint().x + "\t");
		}
		System.out.println();

		System.out.print("Y\t");
		for (int i = 0; i < carList.size(); i++) {
			System.out.print(carList.get(i).getOriginPoint().y + "\t");
		}
		System.out.println();

		System.out.print("NO:\t");
		for (int i = 0; i < carList.size(); i++) {
			System.out.print("Car " + i + "\t");
		}
		System.out.print("Sum");
		System.out.println();

		for (int i = 0; i < carList.size(); i++) {
			int sum = 0;
			System.out.print("Car " + i + "\t");
			for (int j = 0; j < carList.size(); j++) {
				Car c1 = carList.get(i);
				Car c2 = carList.get(j);
				sum += overlapping.get(new Pair<Car, Car>(c1, c2));
				System.out.print(overlapping.get(new Pair<Car, Car>(c1, c2)) + "\t");
			}
			System.out.print(sum);
			System.out.println();
		}
		System.out.println();

	} // end of showOverlapping()

	public void forceFeasible() {

		int totalOverlapping;

		do {
			countOverlapping();
			totalOverlapping = 0;

			int maxOverlapping = 0;
			int maxOverlappingIndex = -1;

			for (int i = 0; i < carList.size(); i++) {
				int carOverlapping = 0;
				for (int j = 0; j < carList.size(); j++) {
					Car c1 = carList.get(i);
					Car c2 = carList.get(j);
					carOverlapping += overlapping.get(new Pair<Car, Car>(c1, c2));
				}

				totalOverlapping += carOverlapping;

				if (carOverlapping > maxOverlapping) {
					maxOverlapping = carOverlapping;
					maxOverlappingIndex = i;
				}
			}

			showOverlapping();

			if (totalOverlapping != 0) {
				carList.remove(maxOverlappingIndex);
				System.out.println("Take off car " + maxOverlappingIndex);
			}
		} while (totalOverlapping > 0);

	} // end of forceFeasible()
} // end of class Individual
