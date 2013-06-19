package decrypt;

import java.math.BigInteger;

import adapters.BigIntegerMatrixOperations;
import adapters.JBLASAdapter;
import adapters.MatrixAdapter;

public class CiphertextDecrypter {
	
	public double[][] decrypt(double[][] ciphertext, double[][] privateKey){
		JBLASAdapter ma = new JBLASAdapter();
		double[][] E = ma.multiply(ma.multiply(privateKey, ciphertext), ma.transpose(privateKey));
		double[][] B = ma.mod(ma.multiply(ma.multiply(ma.inverse(privateKey),E), ma.inverse(ma.transpose(privateKey))), 2);
		
		return B;
	}
}