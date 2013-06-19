package homomorphicOperations;

import java.math.BigInteger;

public interface HomomorphicOperations {
	/**
	 * adds two ciphertexts
	 */
	public double[][] add(double[][] c1, double[][] c2);
	/**
	 * multiplies two ciphertexts
	 */
	public double[][] multiply(double[][] c1,double[][] c2);

}
