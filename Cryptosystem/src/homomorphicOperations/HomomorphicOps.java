package homomorphicOperations;
/**
 * 
 * @author Aaron
 *Used to access the homomorphic operations of the system.
 *Actual implementation deferred to AddOps and MultiplyOps classes
 *
 */
public class HomomorphicOps implements HomomorphicOperations {

	@Override
	public long[][] add(long[][] c1, long[][] c2) {
		long[][] c3 = AddOps.add(c1, c2);
		return c3;
	}

	@Override
	public long[][] multiply(long[][] c1, long[][] c2) {
		long[][] c3 = MultiplyOps.multiply(c1, c2);
		return null;
	}

}
