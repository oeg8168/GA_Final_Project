package GA;

import java.util.ArrayList;

import javaxt.io.Image;

import Component.Car;

public class Individual {

	Image map;
	ArrayList<Car> carList;

	public Individual(String mapFilePath) {
		map = new Image(mapFilePath);
		carList = new ArrayList<Car>();
	}

	public double getFitness() {

	} // end of getFitness()

} // end of class Individual
