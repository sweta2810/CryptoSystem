package test;

import decrypt.CiphertextDecrypter;
import encrypt.PlaintextEncrypter;
import keyGen.KeyTuple;
import keyGen.TrapdoorSampler;
import homomorphicOperations.HomomorphicOps;

/**
 * 
 * @author Aaron
 * Created to informally test the entire system before more formal tests are performed
 * This can be modularised to allow for tests of individual subsystems.
 * 
 */

public class CryptosystemTest {

	public static void main(String[] args) {
		overallTest();
	}
	/**
	 * tests all of the functionality of a typical success scenario for the entire system. Informal. Missing tests include tests for exceeding the parameters given in the paper.
	 */
	private static void overallTest(){
		double testSecurityParameter = 5000;
		double testModulus = 7;
		
		//needs to me modified to fit trapsamp...
		KeyTuple outputTuple = TrapdoorSampler.trapSamp(testSecurityParameter, testModulus);

		double[][] privateKey = outputTuple.getPrivateKey();
		double[][] publicKey = outputTuple.getPublicKey();
		
		//Initialise plaintext matrix. Should make random eventually, so that we don't keep using the same test values
		double[][] plainText = new double[10][10];
		for (int x=0; x<10;x++){
			for (int y=0;y<10;y++){
				plainText[x][y] = x+y;
			}			
		}
		
		System.out.println("Plaintext:");
		System.out.println(plainText);
		System.out.println();
		
		PlaintextEncrypter pe = new PlaintextEncrypter();
		double[][] cipherText = pe.encrypt(plainText, publicKey);
		
		System.out.println("Ciphertext:");
		System.out.println(cipherText);
		System.out.println();
		
		HomomorphicOps ho = new HomomorphicOps();
		
		double[][] addedCipherText = ho.add(cipherText, cipherText);
		
		System.out.println("Added Ciphertext:");
		System.out.println(addedCipherText);
		System.out.println();
		
		double[][] multipliedCipherText = ho.multiply(cipherText, cipherText);
		
		System.out.println("Multiplied Ciphertext:");
		System.out.println(multipliedCipherText);
		System.out.println();
		
		CiphertextDecrypter cd = new CiphertextDecrypter();
		
		double[][] decryptedCiphertext = cd.decrypt(cipherText, privateKey);
		double[][] decryptedAddedCiphertext = cd.decrypt(addedCipherText, privateKey);
		double[][] decryptedMultipliedCiphertext = cd.decrypt(multipliedCipherText, privateKey);
		
		System.out.println("Decrypted Plaintext:");
		System.out.println(decryptedCiphertext);
		System.out.println();
		
		System.out.println("Decrypted Multiplied Plaintext:");
		System.out.println(decryptedAddedCiphertext);
		System.out.println();
		
		System.out.println("Decrypted Added Plaintext:");
		System.out.println(decryptedMultipliedCiphertext);
		System.out.println();
	}

}
