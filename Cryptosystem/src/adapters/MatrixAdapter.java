package adapters;

import java.math.BigInteger;

public interface MatrixAdapter {
	public BigInteger[][] add(BigInteger[][] m1, BigInteger[][] m2);
	public BigInteger[][] multiply(BigInteger[][] m1, BigInteger[][] m2);
	public BigInteger[][] transpose(BigInteger[][] m);
}
