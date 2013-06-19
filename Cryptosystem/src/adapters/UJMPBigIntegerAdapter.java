package adapters;

import java.math.BigInteger;

import org.ujmp.core.bigintegermatrix.BigIntegerMatrix;
import org.ujmp.core.bigintegermatrix.factory.DefaultBigIntegerMatrix2DFactory;
import org.ujmp.core.bigintegermatrix.impl.ArrayDenseBigIntegerMatrix2D;

public class UJMPBigIntegerAdapter implements MatrixAdapter {

	@Override
	public BigInteger[][] add(BigInteger[][] m1, BigInteger[][] m2) {

		ArrayDenseBigIntegerMatrix2D bim1 = new ArrayDenseBigIntegerMatrix2D(m1);
		ArrayDenseBigIntegerMatrix2D bim2 = new ArrayDenseBigIntegerMatrix2D(m2);
		
		ArrayDenseBigIntegerMatrix2D bim3 = (ArrayDenseBigIntegerMatrix2D) bim1.plus(bim2);
		
		BigInteger[][] m3 = bim3.toBigIntegerArray();
		
		return m3;
	}

	@Override
	public BigInteger[][] multiply(BigInteger[][] m1, BigInteger[][] m2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigInteger[][] multiply(BigInteger[][] m, BigInteger b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigInteger[][] transpose(BigInteger[][] m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigInteger[][] inverse(BigInteger[][] m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigInteger[][] mod(BigInteger[][] m, BigInteger mod) {
		// TODO Auto-generated method stub
		return null;
	}

}
