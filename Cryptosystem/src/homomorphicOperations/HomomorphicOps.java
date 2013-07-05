package homomorphicOperations;

import java.math.BigInteger;

/**
 * 
 * @author Aaron and Nishant
 *Used to access the homomorphic operations of the system.
 *Actual implementation deferred to AddOps and MultiplyOps classes
 *
 */
public class HomomorphicOps implements HomomorphicOperations {

	/**
	 * 
	 * adds two ciphertexts such that the output is a ciphertext that, when decoded, will reveal the sum of the plaintexts which the operand ciphertexts encoded
	 * 
	 */
	@Override
	public double[][] add(double[][] c1, double[][] c2) {
		double[][] c3 = AddOps.add(c1, c2);
		return c3;
	}
	
	/**
	 * 
	 * multiplies two ciphertexts such that the output is a ciphertext that, when decoded, will reveal the product of the plaintexts which the operand ciphertexts encoded
	 * 
	 */

	@Override
	public double[][] multiply(double[][] c1, double[][] c2) {
		double[][] c3 = MultiplyOps.multiply(c1, c2);
		return c3;
	}

}
