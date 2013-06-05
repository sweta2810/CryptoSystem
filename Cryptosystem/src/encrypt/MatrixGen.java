package encrypt;

import java.math.BigInteger;
import java.util.Random;

public class MatrixGen {

	/**
	 * Generates a error matrix sampled from the distribution in the lwe-bgn paper.
	 * @return
	 */
	public BigInteger[][] generateErrorMatrix(){
		return null;		
	}
	
	/**
	 * Generates a uniformly random securityParameterXdimentionality matrix of the given modulus.
	 * @param modulus the modulus of the interger number system from which the random values will be drawn.
	 * @param securityParameter the security parameter chosen by the user for the system.
	 * @param dimentionality the size of the columns of the output matrix. Generated by the ParamGen class.
	 * @return ranMatrix matrix with securityParameter rows of dimentionality size and random BigInteger components.
	 */
	public BigInteger[][] generateRandomMatrix(BigInteger modulus, BigInteger securityParameter, BigInteger dimentionality){
		Random ran = new Random();
		//potential loss of accuracy here, due to converting from BigInteger but matrix dimensions are small compared to dimensionality size, so as long as the security parameter is small... we'll need to put in proper size checking later
		BigInteger[][] ranMatrix =  new BigInteger[securityParameter.intValue()][dimentionality.intValue()];
		for (int x=0;x<securityParameter.longValue();x++){
			for(int y=0;y<dimentionality.longValue();y++){
				//is 256 the number of bits we want??? Do we need more?
				BigInteger b = new BigInteger(256, new Random());
				ranMatrix[x][y] = b.mod(modulus);
			}
		}
		return ranMatrix;		
	}
	
}
