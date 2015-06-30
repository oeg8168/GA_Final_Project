package Main;

import javaxt.io.Image;

public class GlobalParam {
	public static final int CAR_WIDTH = 30;
	public static final int CAR_HEIGHT = 65;

	public static final String INPUT_FOLDER_PATH = "./input_img/";
	public static final String OUTPUT_FOLDER_ROOT = "./output/";
	public static String OUTPUT_FOLDER_PATH = "./output/";

	public static final Image ORIGIN_CAR_IMG = new Image(GlobalParam.INPUT_FOLDER_PATH + "carS.bmp");;
	
	public static final int IMAGE_RECORD_INTERVAL = 1;
	public static final int MAX_CARS = 30;

} // end of class GlobalParam
