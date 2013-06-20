package homomorphicOperations;

import java.math.BigInteger;

import adapters.BigIntegerMatrixOperations;
import adapters.JAMAAdapter;
import adapters.JBLASAdapter;
import adapters.MatrixAdapter;

/**
 * @author Aaron
 * Created to defer the all operations related to addition away from HomomorphicOps class.
 * A little redundant currently, but this modularisation may help later on. (such as extra methods which may be required for "overflow" handling code)
 */

public class AddOps {
	public static double[][] add(double[][] c1, double[][] c2){
		//this should be a straightforward addition of the matricies, but we need to account for "overflow", which hasn't been done yet
		JAMAAdapter ma = new JAMAAdapter();
		double[][] c3 = ma.add(c1, c2);
		return c3;
	}
}
