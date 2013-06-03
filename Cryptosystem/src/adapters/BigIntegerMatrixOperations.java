package adapters;

import java.math.BigInteger;

public class BigIntegerMatrixOperations implements MatrixAdapter{

	@Override
	public BigInteger[][] add(BigInteger[][] m1, BigInteger[][] m2) {
		//test that addition is legal between the two matricies
		if (m1.length != m2.length && m1[0].length != m2[0].length){
			//throw an exception
		}
		else{
			BigInteger[][] m3 = new BigInteger[m1.length][m1[0].length];
			
			for(int x=0; x<m1.length;x++){
				for(int y=0;y<m1[0].length;y++){
					m3[x][y] = m1[x][y].add(m2[x][y]);
				}
			}
			
			return m3;
		}
		return null;
		
	}

	@Override
	public BigInteger[][] multiply(BigInteger[][] m1, BigInteger[][] m2) {
		BigInteger[][] m3 = new BigInteger[m1[0].length][m2.length];
		//for each row, multiply it by each column...
		for(int x = 0;x<m1.length;x++){
			BigInteger tempval = BigInteger.valueOf(0);
			for (int y=0;y<m2[0].length;y++){
				for (int loc=0; loc<m1.length;loc++){
					tempval = tempval.add(m1[loc][y].multiply(m2[x][loc]));
				}
				m3[x][y] = tempval;
			}
		}
		return m3;
	}

	@Override
	public BigInteger[][] transpose(BigInteger[][] m) {
		BigInteger[][] m2 = new BigInteger[m.length][m[0].length];
		for (int x =0;x<m.length;x++){
			for(int y=0;y<m[0].length;y++){
				m2[x][y] = m[y][x];
			}
		}
		return null;
	}
	

}
