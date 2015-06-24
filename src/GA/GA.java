package GA;

import java.util.ArrayList;
import javaxt.io.Image;
import Component.Car;
import Main.GlobalParam;

public class GA {

	static int maxGeneration;
	static int populationSize;
	static ArrayList<Individual> population;

	static double mutationRate;
	static double crossoverRate;

	public static void run() {

		setParam();
		initialize();

		for (Individual individual : population) {
			individual.outputImg(GlobalParam.OUTPUT_FOLDER_PATH + individual.toString() + "_1.jpg");
			
			individual.countOverlapping();
			individual.forceFeasible();
			
			individual.outputImg(GlobalParam.OUTPUT_FOLDER_PATH + individual.toString() + "_2.jpg");
		}

	} // end of run()

	public static void setParam() {
		maxGeneration = 1000;
		populationSize = 1;
		mutationRate = 0.5;
		crossoverRate = 0.8;
	} // end of setParam()

	public static void initialize() {
		population = new ArrayList<Individual>();
		Image map = new Image(GlobalParam.INPUT_FOLDER_PATH + "4.bmp");

		for (int i = 0; i < populationSize; i++) {
			Individual individual = new Individual(map);
			int carNumber = 10;
			// int carNumber = (int) (Math.random() * GlobalParam.MAX_CARS);

			for (int j = 0; j < carNumber; j++) {
				int carX = (int) (Math.random() * individual.map.getWidth());
				int carY = (int) (Math.random() * individual.map.getHeight());
				int carO = (int) (Math.random() * 360);

				Car c = new Car(carX, carY, carO);

				individual.carList.add(c);
			}

			population.add(individual);
			System.out.println("Generated individual with " + individual.carList.size() + " cars.");
		}

	} // end of initialize()

	public static void crossover() {

	} // end of crossover()

	public static void mutation() {

	} // end of mutation()

} // end of class GA
