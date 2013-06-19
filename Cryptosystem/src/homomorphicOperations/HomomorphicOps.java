package homomorphicOperations;

import java.math.BigInteger;

/**
 * 
 * @author Aaron
 *Used to access the homomorphic operations of the system.
 *Actual implementation deferred to AddOps and MultiplyOps classes
 *
 */
public class HomomorphicOps implements HomomorphicOperations {

	@Override
	public double[][] add(double[][] c1, double[][] c2) {
		double[][] c3 = AddOps.add(c1, c2);
		return c3;
	}

	@Override
	public double[][] multiply(double[][] c1, double[][] c2) {
		double[][] c3 = MultiplyOps.multiply(c1, c2);
		return c3;
	}

}
