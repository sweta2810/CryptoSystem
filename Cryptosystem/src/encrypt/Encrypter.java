package encrypt;

import java.math.BigInteger;

public interface Encrypter {

	public BigInteger[][] encrypt(BigInteger[][] plaintext ,BigInteger[][] publicKey);
	
}
