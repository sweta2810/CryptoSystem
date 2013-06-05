package encrypt;


import java.math.BigInteger;

/**
 * 
 * @author Aaron
 * Contains method to encrypt plaintext and related helper-methods.
 *
 */



public interface Encrypter {

	public BigInteger[][] encrypt(BigInteger[][] plaintext ,BigInteger[][] publicKey);
	
}
