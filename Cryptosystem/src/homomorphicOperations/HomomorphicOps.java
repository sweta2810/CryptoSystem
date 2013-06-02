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
	public BigInteger[][] add(BigInteger[][] c1, BigInteger[][] c2) {
		BigInteger[][] c3 = AddOps.add(c1, c2);
		return c3;
	}

	@Override
	public BigInteger[][] multiply(BigInteger[][] c1, BigInteger[][] c2) {
		BigInteger[][] c3 = MultiplyOps.multiply(c1, c2);
		return c3;
	}

}
