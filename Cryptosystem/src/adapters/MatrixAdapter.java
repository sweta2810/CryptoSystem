package adapters;

/**
 * Meant to allow for interchangeable classes and adapters to allow for easy replacement of matrix operation implementations and external matrix operation packages.
 * @author Aaron
 *
 */

import java.math.BigInteger;

public interface MatrixAdapter {
	public BigInteger[][] add(BigInteger[][] m1, BigInteger[][] m2);
	public BigInteger[][] multiply(BigInteger[][] m1, BigInteger[][] m2);
	public BigInteger[][] transpose(BigInteger[][] m);
	public BigInteger[][] inverse(BigInteger[][] m);
	/**
	 * Generates a new matrix by performing the mod operation on all components of the inputed matrix.
	 */
	public BigInteger[][] mod(BigInteger[][] m, BigInteger mod);
	}
