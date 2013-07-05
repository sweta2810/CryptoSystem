package adapters;

/**
 * Meant to allow for interchangeable classes and adapters to allow for easy replacement of matrix operation implementations and external matrix operation packages.
 * @author Aaron
 *
 */

import java.math.BigInteger;

public interface MatrixAdapter {
	public double[][] add(double[][] m1, double[][] m2);
	public double[][] multiply(double[][] m1, double[][] m2);
	public double[][] multiply(double[][] m, double b);
	public double[][] transpose(double[][] m);
	public double[][] inverse(double[][] m);
	/**
	 * Generates a new matrix by performing the mod operation on all components of the inputed matrix.
	 */
	public double[][] mod(double[][] m, double mod);
	}
