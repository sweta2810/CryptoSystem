package adapters;

import java.math.BigInteger;
import jama.Matrix;

/**
 * Meant to allow for interchangeable classes and adapters to allow for easy replacement of statistics operation implementations and external statistics operation packages.
 * @author Aaron
 *
 */

public interface StatisticsAdapter {

	public double sample();
	double[][] generateErrorMatrix();
	double[][] generateRandomMatrix();
	
}
