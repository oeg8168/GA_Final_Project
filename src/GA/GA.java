package GA;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

import javaxt.io.Image;
import Component.Car;
import Component.Tools;
import Main.GlobalParam;

public class GA {

	static int maxGeneration;
	static int maxPopulationSize;

	static double mutationRate;
	static double elementMutationRate;
	static double crossoverRate;

	static ArrayList<Individual> population;

	static ArrayList<Integer> averageFitness;
	static ArrayList<Individual> bestIndividuals;
	static ArrayList<Individual> worstIndividuals;

	public static void run() {

		setParam();
		initialize();

		for (int i = 0; i < maxGeneration; i++) {
			crossover();
			mutation();

			for (Individual individual : population) {
				individual.forceFeasible();
			}
			
			sortPopulation();
			keepBest();

			
			record();

			if (i % GlobalParam.IMAGE_RECORD_INTERVAL == 0) {
				Tools.outputImgLog(population, i, "TOP");
			}
		}
		outputRecord();

		population.get(0).forceFeasible();
		population.get(0).outputImg(GlobalParam.OUTPUT_FOLDER_PATH + "Final_" + population.get(0).carList.size() + ".jpg");

	} // end of run()

	private static void setParam() {
		maxGeneration = 2000;
		maxPopulationSize = 100;

		crossoverRate = 0.7;

		mutationRate = 0.5;
		elementMutationRate = 0.5;

	} // end of setParam()

	private static void initialize() {
		averageFitness = new ArrayList<Integer>();
		bestIndividuals = new ArrayList<Individual>();
		worstIndividuals = new ArrayList<Individual>();

		population = new ArrayList<Individual>();
		Image map = new Image(GlobalParam.INPUT_FOLDER_PATH + "test5_1.bmp");

		for (int i = 0; i < maxPopulationSize; i++) {
			int carNumber = (int) (Math.random() * GlobalParam.MAX_CARS);
			ArrayList<Car> cars = new ArrayList<Car>();

			while (cars.size() < carNumber) {
				cars.add(Car.getNewRandomCar(map));
			}

			Individual individual = new Individual(map, cars);

			population.add(individual);
			System.out.print("Generated individual with " + individual.carList.size() + " cars.\t");
			System.out.println("Fitness = " + individual.getFitness());
		}
	} // end of initialize()

	private static void crossover() {
		while (Math.random() < crossoverRate) {
			HashSet<Car> carPool = new HashSet<Car>();
			carPool.addAll(population.get((int) (Math.random() * maxPopulationSize)).carList);
			carPool.addAll(population.get((int) (Math.random() * maxPopulationSize)).carList);

			// population.add(new Individual(population.get(0).map, carPool));

			ArrayList<Car> cars_1 = new ArrayList<Car>();
			ArrayList<Car> cars_2 = new ArrayList<Car>();

			for (Car car : carPool) {
				if (Math.random() < 0.5) {
					cars_1.add(car);
				} else {
					cars_2.add(car);
				}
			}

			population.add(new Individual(population.get(0).map, cars_1));
			population.add(new Individual(population.get(0).map, cars_2));
		}
	} // end of crossover()

	private static void mutation() {
		while (Math.random() < mutationRate) {
			Individual individual = population.get((int) (Math.random() * population.size()));
			Image map = individual.map;
			ArrayList<Car> cars = new ArrayList<Car>(individual.carList);

			if (cars.size() == 0) {
				return;
			}

			while (Math.random() < elementMutationRate) {
				int index = (int) (Math.random() * cars.size());

				cars.set(index, Car.getNewRandomCar(map));
			}

			population.add(new Individual(map, cars));
		}
	} // end of mutation()

	/**
	 * Sort population by fitness of each individual
	 */
	private static void sortPopulation() {
		population.sort(new Comparator<Individual>() {
			public int compare(Individual ind1, Individual ind2) {
				return Double.compare(ind2.getFitness(), ind1.getFitness());
			}
		});
	} // end of sortPopulation()

	/**
	 * Keep top N individuals
	 */
	private static void keepBest() {
		while (population.size() > maxPopulationSize) {
			population.remove(population.size() - 1);
		}
	}// end of keepBest()

	/**
	 * Record fitness values
	 */
	private static void record() {
		bestIndividuals.add(population.get(0));
		worstIndividuals.add(population.get(population.size() - 1));

		int totalFitness = 0;
		for (Individual individual : population) {
			totalFitness += individual.getFitness();
		}
		averageFitness.add(totalFitness / population.size());
	} // end of record()

	/**
	 * Output fitness values
	 */
	private static void outputRecord() {
		try {
			FileWriter fw = new FileWriter(GlobalParam.OUTPUT_FOLDER_PATH + "Fitness.csv");
			fw.write("Best,Worst,Average" + "\r\n");
			for (int i = 0; i < maxGeneration; i++) {
				fw.write(bestIndividuals.get(i).getFitness() + ",");
				fw.write(worstIndividuals.get(i).getFitness() + ",");
				fw.write(averageFitness.get(i) + "\r\n");
			}
			fw.close();
		} catch (IOException e) {
			System.err.println("Error while outputing fitness records");
			e.printStackTrace();
		}
	} // end of outputRecord()

} // end of class GA
