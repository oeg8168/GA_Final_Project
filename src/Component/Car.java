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
		int offsetX = c2.getOriginPoint().x - c1.getOriginPoint().x;
		int offsetY = c2.getOriginPoint().y - c1.getOriginPoint().y;

		Image temp = c1.getCarImage();
		temp.addImage(c2.getCarImage(), offsetX, offsetY, true);
		temp.saveAs(GlobalParam.OUTPUT_FOLDER_PATH + "2car.png");

		return Tools.countBlack(c1.getCarImage()) + Tools.countBlack(c2.getCarImage()) - Tools.countBlack(temp);
	} // end of getOverlapping()
} // end of class Car
