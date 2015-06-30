package Main;

import Component.Tools;
import GA.GA;

/**
 * 
 * @author LISM_OEG
 *
 */
public class GA_Final_Project_Main {

	public static void main(String[] args) {

		Tools.createOutputFolder();

		long start = System.currentTimeMillis();
		GA.run();
		long end = System.currentTimeMillis();

		System.out.println((end - start) / 1000d + " sec");

		System.out.println("===== End of program =====");

	} // end of main()

} // end of class GA_Final_Project_Main
