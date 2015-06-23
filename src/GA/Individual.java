package GA;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.HashMap;

import javaxt.io.Image;
import Component.Car;
import Component.Pair;
import Main.GlobalParam;

public class Individual {

	public Image map;
	public ArrayList<Car> carList;
	public HashMap<Pair<Car, Car>, Integer> overlapping;

	public Individual(String mapFilePath) {
		this(new Image(mapFilePath));
	}

	/**
	 * 
	 * @param inputMap
	 * @see <a
	 *      href="http://stackoverflow.com/questions/3514158/how-do-you-clone-a-bufferedimage">
	 *      Reference link</a>
	 */
	public Individual(Image inputMap) {
		ColorModel cm = inputMap.getBufferedImage().getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = inputMap.getBufferedImage().copyData(null);
		this.map = new Image(new BufferedImage(cm, raster, isAlphaPremultiplied, null));

		carList = new ArrayList<Car>();
		overlapping = new HashMap<Pair<Car, Car>, Integer>();
	}

	public double getFitness() {
		return 0;
	} // end of getFitness()

	public void outputImg() {

		Image output = map.copy();

		for (Car car : carList) {
			output.addImage(car.getCarImage(), car.getOriginPoint().x, car.getOriginPoint().y, false);
			output.addText(String.valueOf(carList.indexOf(car)), car.getOriginPoint().x, car.getOriginPoint().y);
		}

		output.saveAs(GlobalParam.OUTPUT_FOLDER_PATH + this.toString() + ".jpg");

		// output.saveAs(GlobalParam.OUTPUT_FOLDER_PATH + this.toString() +
		// ".png");
		// output.saveAs(GlobalParam.OUTPUT_FOLDER_PATH + this.toString() +
		// ".bmp");

	} // end of outputImg()

	public void showCars() {
		System.out.println("Individual: \t" + this.toString());
		System.out.println("Car count: \t" + carList.size());
		System.out.println("X\tY\tOrientation");
		for (Car car : carList) {
			System.out.println(car.getOriginPoint().x + "\t" + car.getOriginPoint().y + "\t" + car.getOrientation());
		}
		System.out.println();
	} // end of showCars()

	public void countOverlapping() {

		// for (int i = 0; i < carList.size(); i++) {
		// for (int j = 0; j < carList.size(); j++) {
		// Car c1 = carList.get(i);
		// Car c2 = carList.get(j);
		//
		// if (i > j) {
		// System.out.print("-\t");
		// } else {
		// Car.getOverlapping(c1, c2);
		// System.out.print(Car.getOverlapping(c1, c2) + "\t");
		// }
		// }
		// System.out.println();
		// }

		for (int i = 0; i < carList.size(); i++) {

			for (int j = i; j < carList.size(); j++) {
				Car c1 = carList.get(i);
				Car c2 = carList.get(j);

				Pair<Car, Car> carPair = new Pair<Car, Car>(c1, c2);

				if (!overlapping.containsKey(carPair)) {
					overlapping.put(carPair, Car.getOverlapping(c1, c2));
				}
			}

		}

	}// end of countOverlapping()
} // end of class Individual
