package adapters;

import java.math.BigInteger;

public class BigIntegerMatrixOperations implements MatrixAdapter{

	@Override
	/**
	 * 
	 * Add two BigInteger values, returning a new BigInteger value which represents the sum of the inputs.
	 * @param m1 an operand matrix for the addition operation 
	 * @param m2 another operand matrix for the addition operation
	 * @return m3 a new matrix representing the sum of the two input matrices
	 */
	public BigInteger[][] add(BigInteger[][] m1, BigInteger[][] m2) {
		//test that addition is legal between the two matrices
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
/**
 * 
 * Multiply two BigInteger values in ~O(n^3) time. Note that more efficient matrix multiplication is possible.
 * @param m1 an operand matrix for the multiplication operation 
 * @param m2 another operand matrix for the multiplication operation
 * @return m3 a new matrix representing the product of the two input matricies
 */
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
/**
 * 
 * Generates the transpose of a BigInteger matrix
 * @param m a BigInteger matrix
 * @return m2 a BigInteger matrix where the rows of the input matrix have been swapped with the columns of the input matrix
 * 
 */
	@Override
	public BigInteger[][] transpose(BigInteger[][] m) {
		BigInteger[][] m2 = new BigInteger[m.length][m[0].length];
		for (int x =0;x<m.length;x++){
			for(int y=0;y<m[0].length;y++){
				m2[x][y] = m[y][x];
			}
		}
		return m2;
	}

	public BigInteger[][] inverse(BigInteger[][] m){
		if (m.length == m[0].length){
			
		}
		else{
			//TODO throw exception
		}
		return null;
	}
	@Override
	public BigInteger[][] mod(BigInteger[][] m, BigInteger mod) {
		BigInteger[][] m2 = new BigInteger[m.length][m[0].length];
		for (int x=0;x<m.length;x++){
			for(int y=0;y<m[0].length;y++){
				m2[x][y] = m[x][y].mod(mod);
			}
		}
		return m2;
	}
	@Override
	public BigInteger[][] multiply(BigInteger[][] m, BigInteger b) {
		BigInteger[][] m2 = new BigInteger[m.length][m[0].length];
		for (int x=0;x<m.length;x++){
			for(int y=0;y<m[0].length;y++){
				m2[x][y] = m[x][y].multiply(b);
			}
		}
		return null;
	}
	
	private BigInteger determinant(BigInteger[][] m){
		return null;
		
	}

}
