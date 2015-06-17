package Component;

import java.awt.Point;

/**
 * Data structure of one single car
 * 
 * @author LISM_OEG
 *
 */
public class Car {

	private Point centerPoint;
	private int orientation;

	public Car() {
		centerPoint = new Point();
		orientation = 0;
	}

	public Car(int x, int y) {
		centerPoint = new Point(x, y);
		orientation = 0;
	}

	public Car(int x, int y, int o) {
		centerPoint = new Point(x, y);
		orientation = o;
	}

	// Getter of centerPoint
	public Point getCenterPoint() {
		return this.centerPoint;
	}

	// Getter of orientation
	public int getOrientation() {
		return this.orientation;
	}

} // end of class Car
