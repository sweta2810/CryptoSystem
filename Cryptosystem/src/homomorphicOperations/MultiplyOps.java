package homomorphicOperations;

import java.math.BigInteger;

import adapters.MatrixAdapter;
import adapters.JBLASAdapter;

/**
 * @author Aaron
 * Created to defer the all operations related to multiplication away from HomomorphicOps class.
 * A little redundant currently, but this modularisation may help later on.
 */

public class MultiplyOps {
	public static BigInteger[][] multiply(BigInteger[][] c1, BigInteger[][] c2){
		MatrixAdapter ma = new JBLASAdapter();
		BigInteger[][] c3 = ma.multiply(c1, ma.transpose(c2));
		return c3;
		
	}
}
