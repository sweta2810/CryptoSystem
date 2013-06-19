package encrypt;


import java.math.BigInteger;

/**
 * 
 * @author Aaron
 * Contains method to encrypt plaintext and related helper-methods.
 *
 */



public interface Encrypter {

	public double[][] encrypt(double[][] plaintext ,double[][] publicKey);
	
}
