package Component;

import java.awt.Point;

import Main.GlobalParam;
import javaxt.io.Image;

/**
 * Data structure of one single car
 * 
 * @author LISM_OEG
 *
 */
public class Car {

	private Point originPoint;
	private int orientation;
	private Image carImage;

	public Car() {
		originPoint = new Point();
		orientation = 0;
	}

	public Car(int x, int y) {
		originPoint = new Point(x, y);
		orientation = 0;
	}

	public Car(int x, int y, int o) {
		originPoint = new Point(x, y);
		orientation = o;
		carImage = Tools.getCopyImg(GlobalParam.ORIGIN_CAR_IMG);
		carImage.rotate(o);
	}

	// Getter of originPoint
	public Point getOriginPoint() {
		return this.originPoint;
	}

	// Getter of orientation
	public int getOrientation() {
		return this.orientation;
	}

	// Setter of orientation
	public void setOrientation(int input) {
		this.orientation = input;
	}

	// Get orientated car image
	public Image getCarImage() {
		return Tools.getCopyImg(carImage);
	} // end of getCarImage()

	// Count overlapping for 2 cars
	public static int getOverlapping(Car c1, Car c2) {
		int offsetX = c2.getOriginPoint().x - c1.getOriginPoint().x;
		int offsetY = c2.getOriginPoint().y - c1.getOriginPoint().y;

		// For speed up
		if (Math.abs(offsetX) > 75 || Math.abs(offsetY) > 75) {
			return 0;
		}

		Image temp = c1.getCarImage();
		temp.addImage(c2.getCarImage(), offsetX, offsetY, true);

		// return Tools.countBlack(c1.getCarImage()) +
		// Tools.countBlack(c2.getCarImage()) - Tools.countBlack(temp);
		int overapping = Tools.countBlack(c1.getCarImage()) + Tools.countBlack(c2.getCarImage()) - Tools.countBlack(temp);
		
		if (overapping > 0) {
			return 1;
		} else {
			return 0;
		}

	} // end of getOverlapping()

	public static Car getNewRandomCar(Image map) {
		Car car;

		do {
			int carX = (int) (Math.random() * map.getWidth());
			int carY = (int) (Math.random() * map.getHeight());
			int carO = (int) (Math.random() * 360);
			//carO -= carO % 15;

			car = new Car(carX, carY, carO);

		} while (Tools.isCollision(map, car));

		return car;
	} // end of getNewRandomCar()

} // end of class Car
