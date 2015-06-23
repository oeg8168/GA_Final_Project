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
	}

	// Getter of originPoint
	public Point getOriginPoint() {
		return this.originPoint;
	}

	// Getter of orientation
	public int getOrientation() {
		return this.orientation;
	}

	public Image getCarImage() {
		Image temp = new Image(GlobalParam.INPUT_FOLDER_PATH + "car.bmp");
		temp.rotate(this.orientation);
		return temp;
	} // end of getCarImage()

	public static int getOverlapping(Car c1, Car c2) {
		return 0;
	} // end of getOverlapping

} // end of class Car
