package encrypt;

import java.math.BigInteger;

import keyGen.ParamGen;

import adapters.ApacheCommonsMathAdapter;
import adapters.BigIntegerMatrixOperations;
import adapters.JBLASAdapter;
import adapters.MatrixAdapter;
import adapters.StatisticsAdapter;

public class PlaintextEncrypter implements Encrypter{

	/**
	 * Encrypts a BigInteger matrix with a BigInteger public key as generated by the KeyGen class.
	 * @param plaintext a BigInteger matrix containing the plaintext to be encrypted
	 * @param publickey a public key generated by the KeyGen class.
	 * @return a BigInteger matrix containing the ciphertext.
	 */
	
	public double[][] encrypt(double[][] plaintext, double[][] publicKey) {
		JBLASAdapter ma = new JBLASAdapter();
		StatisticsAdapter sa = new ApacheCommonsMathAdapter();
		MatrixGen gen = new MatrixGen(); 
		//need to get the correct parameters in here
		double[][] ranMatrix = gen.generateRandomMatrix();

		double[][] errorMatrix = sa.generateErrorMatrix();
		ParamGen pGen = new ParamGen();
		double q = pGen.getModulus();
		
		double[][] outMatrix = ma.mod(ma.add(ma.add(ma.multiply(publicKey, ranMatrix), ma.multiply(errorMatrix, 2)), plaintext), q); //need to access q...
		return outMatrix;

	}
}