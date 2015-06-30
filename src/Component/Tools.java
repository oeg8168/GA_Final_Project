package Component;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import GA.Individual;
import Main.GlobalParam;
import javaxt.io.Image;

public class Tools {

	public static void createOutputFolder() {
		if (!new File(GlobalParam.OUTPUT_FOLDER_ROOT).exists())
			new File(GlobalParam.OUTPUT_FOLDER_ROOT).mkdirs();

		// Create output folder
		Date date = new Date();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		GlobalParam.OUTPUT_FOLDER_PATH = new String(GlobalParam.OUTPUT_FOLDER_ROOT + sdFormat.format(date) + "/");
		new File(GlobalParam.OUTPUT_FOLDER_PATH).mkdir();

		System.out.println("Output folder: " + GlobalParam.OUTPUT_FOLDER_PATH);

	} // end of createOutputFolder()

	public static int sumArray(int[][] input) {
		int result = 0;

		for (int[] is : input) {
			for (int i : is) {
				result += i;
			}
		}

		return result;
	} // end of sumArray()

	public static int countBlack(Image img) {
		int result = 0;

		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				if (img.getColor(x, y).getRGB() == Color.BLACK.getRGB()) {
					result += 1;
				}
			}
		}

		return result;
	} // end of countBlack()

	public static boolean containBlack(BufferedImage img, Image carImg) {
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				if (img.getRGB(x, y) == Color.BLACK.getRGB() && carImg.getColor(x, y).getRGB() == Color.BLACK.getRGB()) {
					return true;
				}
			}
		}
		return false;
	} // end of containBlack()

	/**
	 * 
	 * 
	 * @see <a href=
	 *      "http://stackoverflow.com/questions/3514158/how-do-you-clone-a-bufferedimage">
	 *      Reference link</a>
	 */
	public static Image getCopyImg(Image input) {
		ColorModel cm = input.getBufferedImage().getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = input.getBufferedImage().copyData(null);
		return new Image(new BufferedImage(cm, raster, isAlphaPremultiplied, null));
	} // end of getCopyImg()

	public static void outputImgLog(ArrayList<Individual> population, int generation, String option) {

		String outputPath = new String();
		outputPath += GlobalParam.OUTPUT_FOLDER_PATH;

		if (option.equalsIgnoreCase("ALL")) {
			for (Individual individual : population) {
				outputPath += "i" + population.indexOf(individual) + "_G" + generation + ".jpg";
				individual.outputImg(outputPath);
			}
		} else if (option.equalsIgnoreCase("TOP")) {
			outputPath += "topIndividual_G" + generation + "_" + population.get(0).carList.size() + ".jpg";
			population.get(0).outputImg(outputPath);
		}

	} // end of outputImgLog()

	public static boolean isCollision(Image map, Car car) {
		int carX = car.getOriginPoint().x;
		int carY = car.getOriginPoint().y;
		int carW = car.getCarImage().getWidth();
		int carH = car.getCarImage().getHeight();

		if (carX + carW > map.getWidth() || carY + carH > map.getHeight()) {
			return true;
		} else if (Tools.containBlack(map.getBufferedImage().getSubimage(carX, carY, carW, carH), car.getCarImage())) {
			return true;
		}

		return false;
	} // end of isCollision()

} // end of class Tools
