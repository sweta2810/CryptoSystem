package decrypt;

public class CiphertextDecrypter {
	
<<<<<<< HEAD
	public BigInteger[][] decrypt(BigInteger[][] ciphertext, BigInteger[][] privateKey){
		MatrixAdapter ma = new BigIntegerMatrixOperations();
		BigInteger[][] E = ma.multiply(ma.multiply(privateKey, ciphertext), ma.transpose(privateKey));
		BigInteger[][] B = ma.mod(ma.multiply(ma.multiply(ma.inverse(privateKey),E), ma.inverse(ma.transpose(privateKey)), 2);
=======
	public long[][] decrypt(long[][] ciphertext, long[][] privateKey){
>>>>>>> branch 'master' of https://aacurtis@bitbucket.org/aacurtis/shcryptosystem.git
		
		return B;
		
	}

}
