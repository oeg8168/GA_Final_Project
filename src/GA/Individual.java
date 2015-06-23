package GA;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

import javaxt.io.Image;
import Component.Car;
import Main.GlobalParam;

public class Individual {

	public Image map;
	public ArrayList<Car> carList;

	public Individual(String mapFilePath) {
		this(new Image(mapFilePath));
	}

	public Individual(Image inputMap) {
		ColorModel cm = inputMap.getBufferedImage().getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = inputMap.getBufferedImage().copyData(null);
		this.map = new Image(new BufferedImage(cm, raster, isAlphaPremultiplied, null));

		carList = new ArrayList<Car>();
	}

	public double getFitness() {
		return 0;
	} // end of getFitness()

	public void outputImg() {

		Image output = map.copy();

		for (Car car : carList) {
			output.addImage(car.getCarImage(), car.getOriginPoint().x, car.getOriginPoint().y, false);
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

} // end of class Individual
