package homomorphicOperations;

import java.math.BigInteger;

public interface HomomorphicOperations {
	/**
	 * 
	 * adds two ciphertexts such that the output is a ciphertext that, when decoded, will reveal the sum of the plaintexts which the operand ciphertexts encoded
	 * 
	 */
	public double[][] add(double[][] c1, double[][] c2);
	/**
	 * 
	 * multiplies two ciphertexts such that the output is a ciphertext that, when decoded, will reveal the product of the plaintexts which the operand ciphertexts encoded
	 * 
	 */
	public double[][] multiply(double[][] c1,double[][] c2);

}
