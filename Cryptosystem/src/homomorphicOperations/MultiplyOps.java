package homomorphicOperations;

import java.math.BigInteger;

import adapters.BigIntegerMatrixOperations;
import adapters.JAMAAdapter;
import adapters.JBLASAdapter;
import adapters.MatrixAdapter;

/**
 * @author Aaron
 * Created to defer the all operations related to multiplication away from HomomorphicOps class.
 * A little redundant currently, but this modularisation may help later on.
 */

public class MultiplyOps {
	
	/**
	 * 
	 * @param c1 the left ciphertext matrix in the multiplication
	 * @param c2 the right ciphertexts matrix in the multiplication
	 * @return a matrix product of the multiplication of the two ciphertexts
	 */
	public static double[][] multiply(double[][] c1, double[][] c2){
		JAMAAdapter ma = new JAMAAdapter();
		double[][] c3 = ma.multiply(c1, ma.transpose(c2));
		return c3;
		
	}
}
