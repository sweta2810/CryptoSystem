package homomorphicOperations;

import java.math.BigInteger;

public interface HomomorphicOperations {
	/**
	 * adds two ciphertexts
	 */
	public BigInteger[][] add(BigInteger[][] c1, BigInteger[][] c2);
	/**
	 * multiplies two ciphertexts
	 */
	public BigInteger[][] multiply(BigInteger[][] c1,BigInteger[][] c2);
}
