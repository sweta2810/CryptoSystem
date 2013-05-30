package encrypt;

import java.util.Random;

public class MatrixGen {

	/**
	 * Generates a error matrix sampled from the distribution in the lwe-bgn paper.
	 * @return
	 */
	public long[][] generateErrorMatrix(){
		return null;		
	}
	
	/**
	 * Generates a uniformly random securityParameterXdimentionality matrix of the given modulus.
	 * @return
	 */
	public long[][] generateRandomMatrix(long modulus, long securityParameter, long dimentionality){
		Random ran = new Random();
		//potential loss of accuracy here...
		long[][] ranMatrix =  new long[(int) securityParameter][(int) dimentionality];
		for (int x=0;x<securityParameter;x++){
			for(int y=0;y<dimentionality;y++){
				ranMatrix[x][y] = ran.nextInt()%modulus;
			}
		}
		return ranMatrix;		
	}
	
}
