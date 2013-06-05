package decrypt;

import java.math.BigInteger;

/**
 * 
 * @author Aaron
 * Contains method to decrypt ciphertext and related helper-methods.
 *
 */

public class CiphertextDecrypter {
	
	public BigInteger[][] decrypt(BigInteger[][] ciphertext, BigInteger[][] privateKey){
		MatrixAdapter ma = new BigIntegerMatrixOperations();
		BigInteger[][] E = ma.multiply(ma.multiply(privateKey, ciphertext), ma.transpose(privateKey));
		BigInteger[][] B = ma.mod(ma.multiply(ma.multiply(ma.inverse(privateKey),E), ma.inverse(ma.transpose(privateKey)), 2);
		
		return B;
		
	}

}
